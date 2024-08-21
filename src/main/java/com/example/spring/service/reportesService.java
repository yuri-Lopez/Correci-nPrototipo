/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring.service;

import com.example.spring.Model.Reportes;
import com.example.spring.repository.reportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class reportesService {

    @Autowired
    private reportesRepository reportesRepository; 

    public List<Reportes> findAll() {
        return reportesRepository.findAll();
    }

    public Optional<Reportes> findById(Integer id) {
        return reportesRepository.findById(id);
    }

    public Reportes save(Reportes reporte) {
        return reportesRepository.save(reporte);
    }

    public void deleteById(Integer id) {
        reportesRepository.deleteById(id);
    }
}
