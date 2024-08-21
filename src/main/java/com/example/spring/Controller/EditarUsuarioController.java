/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring.Controller;

import com.example.spring.Model.EditarUsuario;
import com.example.spring.Service.EditarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/editar_usuario")
public class EditarUsuarioController {
    
    @Autowired
    private EditarUsuarioService editarUsuarioService;
    
    @GetMapping("/traerdato") // http://localhost:8080/editar_usuario/traerdato
    public ResponseEntity<List<EditarUsuario>> traerdato() {
        List<EditarUsuario> datos = editarUsuarioService.traerUsuarios();
        return ResponseEntity.ok(datos);
    }
    
    @PostMapping("/nuevodato") // http://localhost:8080/editar_usuario/nuevodato
    public ResponseEntity<String> creardato(@RequestBody EditarUsuario dato) {
        String mensaje = editarUsuarioService.guardarUsuario(dato);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }
    
    @PutMapping("/actualizardato") // http://localhost:8080/editar_usuario/actualizardato
    public ResponseEntity<String> actualizardato(@RequestBody EditarUsuario dato) {
        String mensaje = editarUsuarioService.actualizarUsuario(dato);
        if (mensaje.equals("Se actualizó correctamente")) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
    
    @DeleteMapping("/eliminar/{documento}") // http://localhost:8080/editar_usuario/eliminar/{documento}
    public ResponseEntity<String> eliminardato(@PathVariable("documento") String documento) {
        String mensaje = editarUsuarioService.eliminarUsuario(documento);
        if (mensaje.equals("Usuario eliminado correctamente")) {
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }
}
