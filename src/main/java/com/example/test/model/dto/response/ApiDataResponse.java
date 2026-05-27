package com.example.test.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiDataResponse<T> {

    // API có thành công hay không
    private boolean success;

    // Message mô tả
    private String message;

    // Data trả về
    private T data;
}