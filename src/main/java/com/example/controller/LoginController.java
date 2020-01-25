package com.example.controller;


import javax.validation.Valid;


import com.example.model.Item;
import com.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.User;
import com.example.service.UserService;

import java.util.*;

@Controller
public class LoginController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/home")
    public String defaultAfterLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        if (role.contains("ADMIN")) {
            return "redirect:/admin/home";
        } else if (role.contains("USER")) {
            return "redirect:/user/home";
        }else {
            return "redirect:/access-denied";
        }
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Istnieje już użytkownik zarejestrowany na podany email");
        }
        if (bindingResult.hasErrors()) {
            if(user.getRoleId()==0)
            {
                user.setRoleId(5); //jeżeli rola nie wpisana do dajemy 5 czyli USER

            }
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Użytkownik został poprawnie zarejestrowany");
            modelAndView.addObject("user", new User());
            return new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }


    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView home_user() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List <User> users = userService.getAll();

        List <Item> listOfItems = itemService.getAll();
        listOfItems.removeIf(item -> item.getI_user_1() != user.getId() && item.getI_user_2() != user.getId());
        listOfItems.removeIf(item -> (item.getI_paid() == 1));

        List <User> listOfFriends = userService.getAll();
        listOfFriends.removeIf(friend -> (friend.getId() == user.getId()));

        Map<Integer, Double> friendsDebt = new HashMap<>();
        Map<Integer, Double> usersDebt = new HashMap<>();
        List <Integer> listOfFriendsWithDeal = new LinkedList<>();

        double friendAmount = 0;

        for(User friend : listOfFriends) {
            double amount = 0;
            for (Item item : listOfItems){
                if(item.getI_user_2()==user.getId() && item.getI_user_1()==friend.getId()){
                    amount = amount + item.getI_price();
                    friendAmount = friendAmount + item.getI_price();
                }
                friendsDebt.put(friend.getId(), amount);
            }
        }

        double userAmount = 0;

        for(User friend : listOfFriends) {
            double amount = 0;
            for (Item item : listOfItems){
                if(item.getI_user_1()==user.getId() && item.getI_user_2()==friend.getId()){
                    amount = amount + item.getI_price();
                    userAmount = userAmount + item.getI_price();
                }
                usersDebt.put(friend.getId(), amount);
            }
        }

        for (Item item : listOfItems){
            if(item.getI_user_2()==user.getId()) {
                if(!listOfFriendsWithDeal.contains(item.getI_user_1())) {
                    listOfFriendsWithDeal.add(item.getI_user_1());
                }
            }else if(item.getI_user_1()==user.getId()) {
                if(!listOfFriendsWithDeal.contains(item.getI_user_2())) {
                    listOfFriendsWithDeal.add(item.getI_user_2());
                }
            }
        }

        modelAndView.addObject("listOfFriendsWithDeal", listOfFriendsWithDeal);
        modelAndView.addObject("userAmount", userAmount);
        modelAndView.addObject("friendAmount", friendAmount);
        modelAndView.addObject("friendsDebt", friendsDebt);
        modelAndView.addObject("usersDebt", usersDebt);
        modelAndView.addObject("userId", user.getId());
        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("userService", userService);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Accountant Role");
        modelAndView.setViewName("user/home");
        return modelAndView;
    }

}
