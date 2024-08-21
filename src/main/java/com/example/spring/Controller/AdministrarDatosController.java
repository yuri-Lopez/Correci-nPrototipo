/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring.Controller;

import com.example.spring.Model.AdministrarDatos;
import com.example.spring.Service.AdministrarDatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/AdministrarDatos")
public class AdministrarDatosController {
    
    @Autowired
    private AdministrarDatosService administrarDatosService;
    
    @GetMapping("/traerdato") // http://localhost:8080/AdministrarDatos/traerdato
    public ResponseEntity<List<AdministrarDatos>> traerdato() {
        List<AdministrarDatos> datos = administrarDatosService.traerdato();
        return ResponseEntity.ok(datos);
    }
    
    @PostMapping("/nuevodato") // http://localhost:8080/AdministrarDatos/nuevodato
    public ResponseEntity<String> creardato(@RequestBody AdministrarDatos dato) {
        String mensaje = administrarDatosService.guardardato(dato);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }
    
    @PutMapping("/actualizardato") // http://localhost:8080/AdministrarDatos/actualizardato
    public ResponseEntity<String> actualizardato(@RequestBody AdministrarDatos dato) {
        String mensaje = administrarDatosService.actualizardato(dato);
        if (mensaje.equals("Se actualizó correctamente")) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
    
    @DeleteMapping("/eliminar/{documento}") // http://localhost:8080/AdministrarDatos/eliminar/{documento}
    public ResponseEntity<String> eliminardato(@PathVariable("documento") String documento) {
        String mensaje = administrarDatosService.eliminardato(documento);
        if (mensaje.equals("Dato eliminado correctamente")) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
}
