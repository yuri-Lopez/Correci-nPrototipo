/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring.Controller;

import com.example.spring.Model.IngresoPersonal;
import com.example.spring.Service.IngresoPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ingreso-personal")
public class IngresoPersonalController {

    @Autowired
    private IngresoPersonalService service;

    @GetMapping//http://localhost:8080/api/ingreso-personal
    public ResponseEntity<List<IngresoPersonal>> getAllIngresoPersonals() {
        List<IngresoPersonal> ingresoPersonals = service.findAll(); 
        return new ResponseEntity<>(ingresoPersonals, HttpStatus.OK);
    }

    @GetMapping("/{id}")//http://localhost:8080/api/ingreso-personal/{id}
    public ResponseEntity<IngresoPersonal> getIngresoPersonalById(@PathVariable Long id) {
        Optional<IngresoPersonal> ingresoPersonal = service.findById(id);
        return ingresoPersonal.map(ResponseEntity::ok)
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping//http://localhost:8080/api/ingreso-personal
    public ResponseEntity<IngresoPersonal> createIngresoPersonal(@RequestBody IngresoPersonal ingresoPersonal) {
        IngresoPersonal createdIngresoPersonal = service.save(ingresoPersonal);
        return new ResponseEntity<>(createdIngresoPersonal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")//http://localhost:8080/api/ingreso-personal/{id}
    public ResponseEntity<IngresoPersonal> updateIngresoPersonal(@PathVariable Long id,
                                                                 @RequestBody IngresoPersonal ingresoPersonal) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ingresoPersonal.setId(id);
        IngresoPersonal updatedIngresoPersonal = service.save(ingresoPersonal);
        return ResponseEntity.ok(updatedIngresoPersonal);
    }

    @DeleteMapping("/{id}")//http://localhost:8080/api/ingreso-personal/{id}
    public ResponseEntity<Void> deleteIngresoPersonal(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
