package com.pos_app.demo.dto.Response;

import com.pos_app.demo.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseItemPage {

    private List<ItemDto> dtoList;
    private long count;
}
