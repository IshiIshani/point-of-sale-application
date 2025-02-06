package com.pos_app.demo.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseActiveItemPage {

    private List<ResponseActiveItemsDto> itemPageList;
    private long itemCount;
}
