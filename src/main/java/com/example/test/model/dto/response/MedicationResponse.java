package com.example.test.model.dto.response;

import com.example.test.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicationResponse {

    private Long id;

    private String name;

    private String manufacturer;

    private Double price;

    private Status status;

    private Boolean isDeleted;
}