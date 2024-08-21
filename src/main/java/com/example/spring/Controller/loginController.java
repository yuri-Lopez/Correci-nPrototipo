/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring.controller;

import com.example.spring.Model.Login;
import com.example.spring.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class loginController { 

    @Autowired
    private loginService loginService; // Corregido el nombre del servicio

    // Obtener todos los registros de Login
    @GetMapping  // http://localhost:8080/api/login
    public ResponseEntity<List<Login>> getAllLogins() {
        List<Login> logins = loginService.findAll();
        return new ResponseEntity<>(logins, HttpStatus.OK);
    }

    // Obtener un registro de Login por ID
    @GetMapping("/{id}") // http://localhost:8080/api/login/id
    public ResponseEntity<Login> getLoginById(@PathVariable Integer id) {
        Optional<Login> login = loginService.findById(id);
        return login.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo registro de Login
    @PostMapping("/nuevologin") // http://localhost:8080/api/login/nuevologin
    public ResponseEntity<Login> createLogin(@RequestBody Login login) {
        Login createdLogin = loginService.save(login);
        return new ResponseEntity<>(createdLogin, HttpStatus.CREATED);
    }

    // Actualizar un registro de Login existente
    @PutMapping("/{id}") // http://localhost:8080/api/login/id
    public ResponseEntity<Login> updateLogin(@PathVariable Integer id, @RequestBody Login login) {
        if (loginService.findById(id).isPresent()) {
            login.setId(id);
            Login updatedLogin = loginService.save(login);
            return new ResponseEntity<>(updatedLogin, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un registro de Login
    @DeleteMapping("/{id}") // http://localhost:8080/api/login/id
    public ResponseEntity<Void> deleteLogin(@PathVariable Integer id) {
        if (loginService.findById(id).isPresent()) {
            loginService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
