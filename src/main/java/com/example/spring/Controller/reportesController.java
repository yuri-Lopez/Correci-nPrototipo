/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring.controller;

import com.example.spring.Model.Reportes;
import com.example.spring.service.reportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reportes")
public class reportesController {

    @Autowired
    private reportesService reportesService;

    // Obtener todos los reportes
    @GetMapping  //http://localhost:8080/api/reportes
    public ResponseEntity<List<Reportes>> getAllReportes() {
        List<Reportes> reportes = reportesService.findAll();
        return new ResponseEntity<>(reportes, HttpStatus.OK);
    }

    // Obtener un reporte por ID
    @GetMapping("/{id}") //http://localhost:8080/api/reportes/id
    public ResponseEntity<Reportes> getReporteById(@PathVariable Integer id) {
        Optional<Reportes> reporte = reportesService.findById(id);
        return reporte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo reporte
    @PostMapping("/nuevoreporte") // http://localhost:8080/api/reportes/nuevoreporte
    public ResponseEntity<Reportes> createReporte(@RequestBody Reportes reporte) {
        Reportes createdReporte = reportesService.save(reporte);
        return new ResponseEntity<>(createdReporte, HttpStatus.CREATED);
    }

    // Actualizar un reporte existente
    @PutMapping("/{id}") //http://localhost:8080/api/reportes/id
    public ResponseEntity<Reportes> updateReporte(@PathVariable Integer id, @RequestBody Reportes reporte) {
        if (reportesService.findById(id).isPresent()) {
            reporte.setId(id);
            Reportes updatedReporte = reportesService.save(reporte);
            return new ResponseEntity<>(updatedReporte, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un reporte
    @DeleteMapping("/{id}") //http://localhost:8080/api/reportes/id
    public ResponseEntity<Void> deleteReporte(@PathVariable Integer id) {
        if (reportesService.findById(id).isPresent()) {
            reportesService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
