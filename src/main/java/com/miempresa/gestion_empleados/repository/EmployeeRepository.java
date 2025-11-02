package com.miempresa.gestion_empleados.repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miempresa.gestion_empleados.model.Employee;

// @Repository indica que esta interfaz es un componente de persistencia de datos.
@Repository
// JpaRepository<Entidad, Tipo_de_ID> proporciona los métodos CRUD básicos.
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Por ahora, no necesitamos añadir ningún método.
    // Spring ya nos da: save(), findById(), findAll(), deleteById(), etc.
}
