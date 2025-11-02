package com.miempresa.gestion_empleados.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.miempresa.gestion_empleados.service.EmployeeService;

@Controller
public class EmployeeWebController {

    private final EmployeeService employeeService;

    // Inyección de dependencia (constructor)
    public EmployeeWebController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Maneja la petición GET a la URL base (http://localhost:8080/)
    @GetMapping("/")
    public String viewHomePage(Model model) {
        // Llama al servicio para obtener TODOS los empleados
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        
        // Devuelve el nombre del archivo de la plantilla (employees.html)
        return "employees";
    }
}
