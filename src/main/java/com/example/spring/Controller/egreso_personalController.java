/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring.Controller;

import com.example.spring.Model.EgresoPersonal;
import com.example.spring.Service.egresopersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/egreso-personal")
public class egreso_personalController {

    @Autowired
    private egresopersonalService egresoPersonalService;

    // Obtener todos los registros
    @GetMapping
    public ResponseEntity<List<EgresoPersonal>> getAllEgresoPersonales() {
        List<EgresoPersonal> egresoPersonales = egresoPersonalService.findAll();
        return new ResponseEntity<>(egresoPersonales, HttpStatus.OK);
    }

    // Obtener un registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<EgresoPersonal> getEgresoPersonalById(@PathVariable("id") Long id) {
        Optional<EgresoPersonal> egresoPersonal = egresoPersonalService.findById(id);
        return egresoPersonal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo registro
    @PostMapping
    public ResponseEntity<EgresoPersonal> createEgresoPersonal(@RequestBody EgresoPersonal egresoPersonal) {
        EgresoPersonal createdEgresoPersonal = egresoPersonalService.save(egresoPersonal);
        return new ResponseEntity<>(createdEgresoPersonal, HttpStatus.CREATED);
    }

    // Actualizar un registro existente
    @PutMapping("/{id}")
    public ResponseEntity<EgresoPersonal> updateEgresoPersonal(@PathVariable("id") Long id, @RequestBody EgresoPersonal egresoPersonal) {
        if (!egresoPersonalService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        egresoPersonal.setId(id);
        EgresoPersonal updatedEgresoPersonal = egresoPersonalService.save(egresoPersonal);
        return new ResponseEntity<>(updatedEgresoPersonal, HttpStatus.OK);
    }

    // Eliminar un registro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEgresoPersonal(@PathVariable("id") Long id) {
        if (!egresoPersonalService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        egresoPersonalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
