package com.miempresa.gestion_empleados.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miempresa.gestion_empleados.model.Employee;
import com.miempresa.gestion_empleados.service.EmployeeService;

// @RestController es una combinación de @Controller y @ResponseBody.
// Indica que la clase maneja peticiones web y los métodos devuelven datos (JSON/XML).
@RestController
// @RequestMapping define la URL base para este controlador
@RequestMapping("/api/v1/empleados")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Inyección del Service
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // --- Endpoints de la API REST (CRUD) ---

    // GET /api/v1/empleados -> Obtener todos los empleados
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // POST /api/v1/empleados -> Crear un nuevo empleado
    // @RequestBody mapea el JSON que viene en el cuerpo de la petición a un objeto Employee de Java
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        // Llamamos al Service para guardar el nuevo empleado
        return employeeService.saveEmployee(employee);
    }

    // GET /api/v1/empleados/{id} -> Obtener un empleado por ID
    // @PathVariable extrae la variable 'id' de la URL
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        // Usamos .map() para manejar el Optional de forma segura y devolver 404 si no existe
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok) // Si existe, devuelve 200 OK con el empleado
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no, devuelve 404 Not Found
    }

    // PUT /api/v1/empleados/{id} -> Actualizar un empleado existente
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.getEmployeeById(id)
                .map(employee -> {
                    // Actualizar los campos del empleado existente con los nuevos detalles
                    employee.setNombre(employeeDetails.getNombre());
                    employee.setApellido(employeeDetails.getApellido());
                    // ... (Aquí irían todos los setters para todos los campos que quieras actualizar) ...
                    employee.setSalario(employeeDetails.getSalario());

                    Employee updatedEmployee = employeeService.saveEmployee(employee);
                    return ResponseEntity.ok(updatedEmployee);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /api/v1/empleados/{id} -> Eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> {
                    employeeService.deleteEmployee(id);
                    // Devuelve 200 OK sin contenido (No Content)
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
