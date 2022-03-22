package com.tarya.payroll.controller;

import com.tarya.payroll.model.Employee;
import com.tarya.payroll.model.EmployeeRequest;
import com.tarya.payroll.model.MessageResponse;
import com.tarya.payroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payroll/api/v1")
@CrossOrigin
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


   @RequestMapping(value = "/employee", method = RequestMethod.POST)
   public ResponseEntity<MessageResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest){
        MessageResponse messageResponse = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
   }


    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }


    @RequestMapping(value = "employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id){
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value= "employee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable String id, @RequestBody EmployeeRequest employeeRequest){
        Employee updatedEmployee = employeeService.updateEmployeeById(id, employeeRequest);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
