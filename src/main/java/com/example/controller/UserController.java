package com.example.controller;

import com.example.model.Item;
import com.example.model.User;
import com.example.service.ItemService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @RequestMapping(value="/add_spends", method = RequestMethod.GET)
    public ModelAndView add_item(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();
        Item item = new Item();
        view.addObject("item", item);
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.setViewName("/user/add_spends");
        return view;
    }

    @RequestMapping(value="/help", method = RequestMethod.GET)
    public ModelAndView get_help(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.setViewName("/user/help");
        return view;
    }
    @RequestMapping(value = "/add_spends", method = RequestMethod.POST)
    public ModelAndView createNewItem(@Valid Item item, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();
        if (bindingResult.hasErrors()) {
            view.addObject("userName", user.getName() + " " + user.getLastName());
            view.setViewName("user/add_spends");
        } else {
            item.setI_paid(0);
            itemService.saveItem(item);
//            view.addObject("item", new Item());
//            view.addObject("userName", user.getName() + " " + user.getLastName());
//            view.setViewName("user/add_spends");
            return new ModelAndView("redirect:/user/list_spendings");
        }
        return view;
    }

    @RequestMapping(value ="/list_spendings",method = RequestMethod.GET)
        public ModelAndView list_items(){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            ModelAndView view = new ModelAndView();

            List<Item> listOfItems = itemService.getAll();
            listOfItems.removeIf(item -> item.getI_user_1() != user.getId() && item.getI_user_2() != user.getId());
            listOfItems.removeIf(item -> item.getI_paid()==1);
            List<Item> listOfItemspaid = itemService.getAll();
            listOfItemspaid.removeIf(item -> item.getI_user_1() != user.getId() && item.getI_user_2() != user.getId());
            listOfItemspaid.removeIf(item -> item.getI_paid()==0);
            //listOfItems.forEach(item -> item.setI_name_of_friend((userService.findById(item.getI_user_2())).getName() + ' ' + (userService.findById(item.getI_user_2())).getLastName()));
            view.addObject("listOfItems", listOfItems);
            view.addObject("listOfItemspaid", listOfItemspaid);
            view.addObject("userservice", userService);
            view.addObject("itemservice", itemService);
            view.addObject("userId", user.getId());
            view.addObject("userName", user.getName() + " " + user.getLastName());
            view.setViewName("/user/list_spendings");
            return view;
    }


    @RequestMapping(value="/edit_item/{id}", method = RequestMethod.GET)
    public ModelAndView update_item(@PathVariable Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        User friend=userService.findById(itemService.findById(id).getI_user_2());
        //itemService.findById(id).setI_name_of_friend(friend.getName()+ ' '+friend.getLastName());
        ModelAndView view = new ModelAndView();
        view.addObject("item", itemService.findById(id));
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.addObject("myId", user.getId());
        view.addObject("friendson",friend.getName() + ' '+ friend.getLastName());
        view.setViewName("user/edit_item");
        return view;
    }

    @RequestMapping(value="/edit_item/{id}", method = RequestMethod.POST)
    public ModelAndView do_update_item(@Valid Item item, BindingResult bindingResult)
    {
        ModelAndView view = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if (bindingResult.hasErrors()) {

            view.addObject("item", item);
            view.addObject("userName", user.getName() + " " + user.getLastName());

            view.setViewName("/user/edit_item");
            return view;
        } else {
            itemService.saveItem(item);
            return new ModelAndView("redirect:/user/list_spendings");
        }
    }

    @RequestMapping(value ="/list_users",method = RequestMethod.GET)
    public ModelAndView list_users(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();

        List<User> listOfUsers = userService.getAll();
        listOfUsers.removeIf(obj -> obj.getId() == user.getId() || obj.getRoleId() == 1);


        view.addObject("listOfUsers", listOfUsers);
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.setViewName("/user/list_users");
        return view;
    }


    @GetMapping("/add_spends/{id}")
    public ModelAndView CreateNewSpends(@PathVariable int id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();

        User friend = userService.findById(id);
        //Item myItem = itemService.findById(id);
        Item myItem = new Item();
        myItem.setI_user_2(friend.getId());

        view.addObject("item", myItem);
        view.addObject("friendson", friend.getName() + " " + friend.getLastName());;
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.addObject("myId", user.getId());
        view.addObject("friendId", friend.getId());
        view.setViewName("/user/add_spends");
        return view;
    }

    @RequestMapping(value ="/list_debt",method = RequestMethod.GET)
    public ModelAndView list_debt(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView view = new ModelAndView();

        List<Item> listOfItems = itemService.getAll();
        listOfItems.removeIf(item -> (item.getI_user_2() != user.getId()));
        listOfItems.removeIf(item -> (item.getI_paid() == 1));
        listOfItems.removeIf(item -> (item.getI_user_1() == user.getId()));
        listOfItems.removeIf(item -> (item.getI_paid() == 1));
        view.addObject("listOfItems", listOfItems);
        view.addObject("userservice", userService);
        view.addObject("itemservice", itemService);

        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.addObject("userId", user.getId());
        view.setViewName("/user/list_debt");
        return view;
    }

    @RequestMapping(value="/list_debt", method = RequestMethod.POST)
    public ModelAndView do_update_debt(@Valid Integer id)
    {
        ModelAndView view = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Item item=itemService.findById(id);
        item.setI_paid(1);
        itemService.updateItem(item);
        return new ModelAndView("redirect:/user/list_spendings");
    }

    @RequestMapping(value="/delete_item/{id}", method = RequestMethod.GET)
    public ModelAndView delete_confirm(@PathVariable Integer id){
        ModelAndView view = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Item item = itemService.findById(id);
        User friend = userService.findById(item.getI_user_2());

        view.addObject("itemId", item.getId());
        view.addObject("info", "Informacja o wydatku " + item.getId());
        view.addObject("nazwaWydatku", "Nazwa wydatku: " + item.getI_name());
        view.addObject("data", "Data: " + item.getI_date());
        view.addObject("kwota", "Kwota " + item.getI_price() + "zł");

        view.addObject("friend", friend.getName() + " " + friend.getLastName());
        view.addObject("userName", user.getName() + " " + user.getLastName());
        view.setViewName("/user/delete_item");
        return view;
    }

    @RequestMapping(value="/do_delete/{id}", method = RequestMethod.GET)
    public String handleDeleteUser(@PathVariable Integer id) {

        Item itemToDelete = itemService.findById(id);
        itemService.deleteItem(itemToDelete);
        System.out.println("Usunięto wydatek o id: " + id);
        return "redirect:/user/list_spendings";
    }
}
