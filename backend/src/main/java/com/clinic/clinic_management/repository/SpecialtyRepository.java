package com.clinic.clinic_management.repository;

import com.clinic.clinic_management.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
}
