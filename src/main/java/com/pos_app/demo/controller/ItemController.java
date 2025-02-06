package com.pos_app.demo.controller;

import com.pos_app.demo.dto.ItemDto;
import com.pos_app.demo.dto.Response.ResponseActiveItemPage;
import com.pos_app.demo.dto.Response.ResponseActiveItemsDto;
import com.pos_app.demo.dto.Response.ResponseItemPage;
import com.pos_app.demo.dto.request.ItemSaveDto;
import com.pos_app.demo.dto.request.ItemUpdateDto;
import com.pos_app.demo.service.ItemService;
import com.pos_app.demo.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")

public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/save-item")
    public ResponseEntity<StandardResponse> addItem(@RequestBody ItemSaveDto saveDTO) {
        String save = itemService.addItem(saveDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, saveDTO.getItemName() + " saved successfully...", save),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = {"/update-item/"},
            params = {"id"})
    public ResponseEntity<StandardResponse> updateItem(@RequestBody ItemUpdateDto updateDto,
                                                       @RequestParam(value = "id") int id) {
        String updateItem = itemService.updateItem(updateDto, id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(202, "Successfully Updated...", updateItem),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/get-all-items")
    public ResponseEntity<StandardResponse> getAllItems() {
        List<ItemDto> getItems = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All Items are here...", getItems),
                HttpStatus.OK
        );
    }

    @GetMapping(path = {"/get-active-items"},
            params = {"state"})
    public ResponseEntity<StandardResponse> getItemsByState(@RequestParam(value = "state") String state) {
        List<ResponseActiveItemsDto> getItemsByState = itemService.getItemsByState(state);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, state + " items list...", getItemsByState),
                HttpStatus.OK
        );
    }

    @GetMapping(path = {"/get-item-count"})
    public ResponseEntity<StandardResponse> getItemCount() {
        long count = 0;
        long itemCount = itemService.getAllItemCount(count);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "items count...", itemCount),
                HttpStatus.OK
        );
    }

    @GetMapping(path = {"/get-item-count-state"},
            params = {"state"})
    public ResponseEntity<StandardResponse> getItemCountByState(@RequestParam(value = "state") String state) {
        long count = 0;
        long itemCount = itemService.getItemCountByState(count, state);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, state + " items count", itemCount),
                HttpStatus.OK
        );
    }

    @GetMapping(path = {"/get-items-page"},
            params = {"page"})
    public ResponseEntity<StandardResponse> getAllItemsPage(@RequestParam(value = "page") int page) {
        int size = 0;
        ResponseItemPage itemPage = itemService.getAllItemsPage(size, page);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,  "All items", itemPage),
                HttpStatus.OK
        );
    }

    @GetMapping(path = {"/get-items-state-page"},
            params = {"page", "state"})
    public ResponseEntity<StandardResponse> getAllItemsByState(@RequestParam(value = "page") int page,
                                                               @RequestParam(value = "state") String state) {
        int size = 0;
        ResponseActiveItemPage itemPage = itemService.getAllItemsState(size, page, state);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,  state + " items list", itemPage),
                HttpStatus.OK
        );
    }

}

