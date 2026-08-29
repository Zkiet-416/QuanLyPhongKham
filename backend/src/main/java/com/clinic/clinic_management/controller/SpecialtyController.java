package com.clinic.clinic_management.controller;

import com.clinic.clinic_management.entity.Specialty;
import com.clinic.clinic_management.service.SpecialtyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/specialties")

public class SpecialtyController {
    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    // get /api/specialties
    @GetMapping
    public List<Specialty> getAllSpecialties() {
        return specialtyService.getAllSpecialties();
    }

    // get /api/specialties/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Specialty> getSpecialtyById(@PathVariable Integer id) {
        return specialtyService.getSpecialtyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // post /api/specialties
    @PostMapping
    public Specialty addSpecialty(@RequestBody Specialty specialty) {
        return specialtyService.addSpecialty(specialty);
    }

    // put /api/specialties/{id}
    @PutMapping("/{id}")
    public Specialty updateSpecialty(@PathVariable Integer id, @RequestBody Specialty specialty) {
        return specialtyService.updateSpecialty(id, specialty);
    }

    // delete /api/specialties/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Integer id) {
        specialtyService.deleteSpecialty(id);
        return ResponseEntity.noContent().build();
    }
}
