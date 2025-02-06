package com.pos_app.demo.service;

import com.pos_app.demo.dto.request.ItemUpdateDto;
import com.pos_app.demo.dto.ItemDto;
import com.pos_app.demo.dto.Response.ResponseActiveItemPage;
import com.pos_app.demo.dto.Response.ResponseActiveItemsDto;
import com.pos_app.demo.dto.Response.ResponseItemPage;
import com.pos_app.demo.dto.request.ItemSaveDto;

import java.util.List;


public interface ItemService {

     String addItem(ItemSaveDto saveDTO);


     String updateItem(ItemUpdateDto updateDto, int id);

    List<ItemDto> getAllItems();

    List<ResponseActiveItemsDto> getItemsByState(String state);

    long getAllItemCount(long count);

    long getItemCountByState(long count, String state);

    ResponseItemPage getAllItemsPage(int size, int page);

    ResponseActiveItemPage getAllItemsState(int size, int page, String state);

}
