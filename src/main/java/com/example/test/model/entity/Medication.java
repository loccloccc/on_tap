package com.example.test.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medications")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Medication {

    // Khóa chính
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tên thuốc
    private String name;

    // Nhà sản xuất
    private String manufacturer;

    // Giá thuốc
    private Double price;

    // Trạng thái thuốc
    @Enumerated(EnumType.STRING)
    private Status status;

    // Soft delete
    private Boolean isDeleted;
}