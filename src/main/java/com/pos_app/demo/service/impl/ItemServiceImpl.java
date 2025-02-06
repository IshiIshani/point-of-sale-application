package com.pos_app.demo.service.impl;

import com.pos_app.demo.dto.request.ItemUpdateDto;
import com.pos_app.demo.dto.ItemDto;
import com.pos_app.demo.dto.Response.ResponseActiveItemPage;
import com.pos_app.demo.dto.Response.ResponseActiveItemsDto;
import com.pos_app.demo.dto.Response.ResponseItemPage;
import com.pos_app.demo.dto.request.ItemSaveDto;
import com.pos_app.demo.repo.ItemRepo;
import com.pos_app.demo.service.ItemService;
import com.pos_app.demo.util.mappers.ItemMapper;
import com.pos_app.demo.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.mappers.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addItem(ItemSaveDto saveDto) {

       /* Item item = itemMapper.saveItemToEntity(saveDto);
        item.setActiveState(true);
        item.setMeasuringUnit(saveDto.getMeasuringUnit());

        if (itemRepo.existById(item.getItemId())) {
            return itemRepo.save(item).getItemName();
        } else
            throw new EntityExistsException("Item Id is already exist...");*/

        Item saveItems = itemMapper.saveItemToEntity(saveDto);
        saveItems.setActiveState(true);
        saveItems.setMeasuringUnit(saveDto.getMeasuringUnit());
        System.out.println(saveItems);
        itemRepo.save(saveItems);
        return "Ok";
    }

    @Override
    public String updateItem(ItemUpdateDto updateDto, int id) {

        if (itemRepo.existsById(id)) {
//            Item updateItem = itemRepo.findById(id).orElseThrow();
/*
            updateItem.setBalanceQty(updateDto.getBalanceQty());
            updateItem.setSupplierPrice(updateDto.getSupplierPrice());
            updateItem.setSellingPrice(updateDto.getSellingPrice());
            updateItem.setActiveState(updateDto.isActiveState());

            itemRepo.save(updateItem);*/

            itemRepo.updateItem(
                    updateDto.getBalanceQty(), updateDto.getSellingPrice(),
                    updateDto.getSupplierPrice(), updateDto.isActiveState(), id
            );
            return "updated";
        } else
            throw new EntityNotFoundException("Invalid item id...");


    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> itemList = itemRepo.findAll();
        List<ItemDto> list = itemMapper.entityListTodtoList(itemList);
        return list;
    }

    @Override
    public List<ResponseActiveItemsDto> getItemsByState(String state) {

        if (state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")) {

            /*if(state == "active"){
                boolean v = true;
            }else{
                boolean v = false;
            }*/
            boolean b = state.equalsIgnoreCase("active") ? true : false;
            List<Item> items = itemRepo.findAllByActiveStateEquals(b);
            if (items.size() > 0) {
                List<ResponseActiveItemsDto> dtos = itemMapper.getItemsByState(items);
                return dtos;
            }
        } else if (state.equalsIgnoreCase("all")) {
            List<Item> items = itemRepo.findAll();
            List<ResponseActiveItemsDto> dtoList = itemMapper.getItemsByState(items);
            return dtoList;
//            return itemMapper.getItemsByState(itemRepo.findAll());
        }
        return null;
    }

    @Override
    public long getAllItemCount(long count) {
        count = itemRepo.count();
        return count;
    }

    @Override
    public long getItemCountByState(long count, String state) {
        if (state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")) {
            boolean val = state.equalsIgnoreCase("active") ? true : false;
            count = itemRepo.countAllByActiveStateEquals(val);
            return count;
            //return itemRepo.countAllByActiveStateEquals(val);
        } else if (state.equalsIgnoreCase("all")) {
            count = itemRepo.count();
            return count;
//            return itemRepo.count();
        } else
            throw new EntityNotFoundException("Invalid state input");
    }

    @Override
    public ResponseItemPage getAllItemsPage(int size, int page) {

        Page<Item> itemPage = itemRepo.findAll(PageRequest.of(page, 2));
        List<ItemDto> list = itemMapper.pageToList(itemPage);
        long count = itemRepo.count();

        return new ResponseItemPage(
                list,
                count
                //itemMapper.pageToList(itemPage),
                //itemRepo.count();
        );
    }

    @Override
    public ResponseActiveItemPage getAllItemsState(int size, int page, String state) {

        if (state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")) {

            boolean val = state.equalsIgnoreCase("active") ? true : false;
            List<Item> itemList = itemRepo.findAllByActiveStateEquals(val);

            if (itemList.size() > 0) {
                Page<Item> itemsByPage = itemRepo.findAllByActiveStateEquals(val, PageRequest.of(page, 2));
                List<ResponseActiveItemsDto> dtoList = itemMapper.itemPageToState(itemsByPage);
                long itemsCount = itemRepo.countAllByActiveStateEquals(val);

                return new ResponseActiveItemPage(
                        dtoList,
                        itemsCount
                );
            }

        }else if (state.equalsIgnoreCase("all")){
            Page<Item> itemPage = itemRepo.findAll(PageRequest.of(page, 2));
            List<ResponseActiveItemsDto> dtoList = itemMapper.itemPageToResponse(itemPage);
            long itemCount = itemRepo.count();

            return new ResponseActiveItemPage(
                    dtoList,
                    itemCount
            );

        }else
            throw new EntityNotFoundException("Invalid state input");


        return null;
    }


}
