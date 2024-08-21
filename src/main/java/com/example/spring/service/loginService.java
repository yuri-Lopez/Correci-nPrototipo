/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring.service;

import com.example.spring.Model.Login;
import com.example.spring.repository.loginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class loginService {

    @Autowired
    private loginRepository loginRepository;

    public List<Login> findAll() {
        return loginRepository.findAll();
    }

    public Optional<Login> findById(Integer id) {
        return loginRepository.findById(id);
    }

    public Login save(Login login) {
        return loginRepository.save(login);
    }

    public void deleteById(Integer id) {
        loginRepository.deleteById(id);
    }

}
