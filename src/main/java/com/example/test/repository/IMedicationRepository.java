package com.example.test.repository;

import com.example.test.model.entity.Medication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicationRepository extends JpaRepository<Medication, Long> {}