package com.pos_app.demo.dto.paginated;

import com.pos_app.demo.dto.Response.ResponseOrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseOrderDetails {

    private List<ResponseOrderDetailsDTO> responseDto;
    private long dataCount;
}
