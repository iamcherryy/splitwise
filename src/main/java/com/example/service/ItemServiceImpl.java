package com.example.service;

import com.example.model.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List <Item> getAll(){
        return itemRepository.findAll();
    }

    @Override
    public Item findById(Integer Id) {
        return itemRepository.findById(Id);
    }

    @Override
    public void saveItem(Item item){
        item.setI_name(item.getI_name());
        item.setI_price(item.getI_price());
        //item.setI_friend(item.getI_friend());
        item.setI_user_1(item.getI_user_1());
        item.setI_user_2(item.getI_user_2());
      //  item.setI_case_id(item.getI_case_id());
        item.setI_date(item.getI_date());
        item.setI_paid(item.getI_paid());
      //  item.setInner_price(item.getInner_price());
        itemRepository.save(item);
    }

    @Override
    public void updateItem(Item item){
        saveItem(item);
    }

    @Override
    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }
}
