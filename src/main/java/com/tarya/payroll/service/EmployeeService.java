package com.tarya.payroll.service;

import com.tarya.payroll.exception.ResourceNotFoundException;
import com.tarya.payroll.model.Employee;
import com.tarya.payroll.model.EmployeeRequest;
import com.tarya.payroll.model.MessageResponse;
import com.tarya.payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public MessageResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setRole(employeeRequest.getRole());
        employeeRepository.save(employee);
        return new MessageResponse("New Employee created successfully");
    }


    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee :" + id + "not found"));
    }


    public Employee updateEmployeeById(String id, EmployeeRequest employeeRequest) {
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee :" + id + "not found"));
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(employeeRequest.getName());
        employee.setRole(employeeRequest.getRole());
        employee.setEmail(employeeRequest.getEmail());
        return employeeRepository.save(employee);
    }


    public void deleteEmployee(String id) {
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee :" + id + "not found"));
        employeeRepository.deleteById(id);
    }
}
