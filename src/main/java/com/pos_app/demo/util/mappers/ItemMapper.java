package com.pos_app.demo.util.mappers;

import com.pos_app.demo.dto.ItemDto;
import com.pos_app.demo.dto.Response.ResponseActiveItemsDto;
import com.pos_app.demo.dto.request.ItemSaveDto;
import com.pos_app.demo.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item saveItemToEntity(ItemSaveDto saveDto);

    List<ItemDto> entityListTodtoList(List<Item> itemList);

    List<ResponseActiveItemsDto> getItemsByState(List<Item> items);

    List<ItemDto> pageToList(Page<Item> itemPage);

    List<ResponseActiveItemsDto> itemPageToState(Page<Item> itemsByPage);


    List<ResponseActiveItemsDto> itemPageToResponse(Page<Item> itemPage);

}
