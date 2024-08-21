/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring.Controller;

import com.example.spring.Model.Registro;
import com.example.spring.Service.registroService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registro")
public class RegistroController {

    @Autowired
    private registroService registroService;

    @GetMapping// http://localhost:8080/api/registro
    public ResponseEntity<List<Registro>> getAllRegistros() {
        List<Registro> registros = registroService.getAllRegistros();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}") //http://localhost:8080/api/registro/id
    public ResponseEntity<Registro> getRegistroById(@PathVariable("id") Long id) {
        Optional<Registro> registro = registroService.getRegistroById(id);
        return registro.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping ("/nuevoregistro")//http://localhost:8080/api/registro/nuevoregistro
    public ResponseEntity<Registro> createRegistro(@RequestBody Registro registro) {
        Registro newRegistro = registroService.createRegistro(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRegistro);
    }

    @PutMapping("/{id}")// http://localhost:8080/api/registro/{id}
    public ResponseEntity<Registro> updateRegistro(@PathVariable("id") Long id, @RequestBody Registro registro) {
        try {
            Registro updatedRegistro = registroService.updateRegistro(id, registro);
            return ResponseEntity.ok(updatedRegistro);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")// http://localhost:8080/api/registro/{id}
    public ResponseEntity<String> deleteRegistro(@PathVariable("id") Long id) {
        try {
            registroService.deleteRegistro(id);
            return ResponseEntity.ok("Registro eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
        }
    }
}
