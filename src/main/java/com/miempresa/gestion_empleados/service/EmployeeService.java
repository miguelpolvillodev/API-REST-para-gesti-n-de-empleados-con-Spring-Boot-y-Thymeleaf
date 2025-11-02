package com.miempresa.gestion_empleados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.miempresa.gestion_empleados.model.Employee;
import com.miempresa.gestion_empleados.repository.EmployeeRepository;

// @Service le dice a Spring que esta clase es un componente de servicio/lógica de negocio
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Inyección de Dependencias por Constructor (la forma recomendada en Spring)
    // Spring inyectará automáticamente una instancia de EmployeeRepository
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // --- Métodos de la Lógica de Negocio (CRUD) ---

    // 1. Obtener todos los empleados (Read All)
    public List<Employee> getAllEmployees() {
        // Simplemente delegamos la acción al Repository
        return employeeRepository.findAll();
    }

    // 2. Obtener un empleado por ID (Read One)
    public Optional<Employee> getEmployeeById(Long id) {
        // Opcional, práctica para manejar la posibilidad de que no exista el ID
        return employeeRepository.findById(id);
    }
    
    // 3. Crear o Actualizar un empleado (Create/Update)
    public Employee saveEmployee(Employee employee) {
        // Validaciones adicionales antes de guardar
        // Por ahora, solo guardamos
        return employeeRepository.save(employee);
    }

    // 4. Eliminar un empleado (Delete)
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
