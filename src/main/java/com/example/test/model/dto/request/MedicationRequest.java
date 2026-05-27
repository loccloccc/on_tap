package com.example.test.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicationRequest {

    // Không được để trống tên thuốc
    @NotBlank(message = "Tên thuốc không được để trống")
    private String name;

    // Không được để trống nhà sản xuất
    @NotBlank(message = "Nhà sản xuất không được để trống")
    private String manufacturer;

    // Giá phải lớn hơn 0
    @NotNull(message = "Giá tiền không được để trống")
    @Min(value = 1, message = "Giá tiền phải lớn hơn 0")
    private Double price;
}