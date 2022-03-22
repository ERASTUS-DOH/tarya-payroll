package com.tarya.payroll.service;

import com.tarya.payroll.model.Employee;
import com.tarya.payroll.model.EmployeeRequest;
import com.tarya.payroll.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    private EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        this.employeeService = new EmployeeService(employeeRepository);
    }


    @Test
    void testGetAllEmployeesSuccess() {
        Employee employee1 = new Employee("123", "Test Emp1", "Developer", "emp1@gmail.com");
        Employee employee2 = new Employee("124", "Test Emp2", "Quality Assurance", "emp2@gmail.com");
        Employee employee3 = new Employee("125", "Test Emp3", "Manager", "emp3@gmail.com");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> employeeList2 = employeeService.getAllEmployees();
        assertEquals(employeeList, employeeList2);
    }

    @Test
    void testCreateEmployeeSuccess() {
        Employee testEmployee = new Employee("1234", "Jane Doe", "Developer", "jane.doe@gmail.com");
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);
        Employee savedEmployee = employeeRepository.save(testEmployee);
        assertThat(savedEmployee.getName()).isNotNull();
    }

    @Test
    void testGetEmployeeById() {
        String id = "1234";
        Employee testEmployee = new Employee(id, "Jane Doe", "Developer", "jane.doe@gmail.com");
        given(employeeRepository.findById(id)).willReturn(Optional.of(testEmployee));
        final Optional<Employee> expectedEmployee = employeeRepository.findById(id);
        assertThat(expectedEmployee).isNotNull();
    }

    @Test
    void updateEmployeeById() {
        String id = "62389";
        final Employee testEmployee = new Employee(id, "Foo Bar", "Quality Assurance", "foo.bar@gmail.com");
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);
        final Employee expectedEmployee = employeeRepository.save(testEmployee);
        assertThat(expectedEmployee).isNotNull();
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void deleteEmployee() {
        String id = "456";
        final Employee testEmployee = new Employee(id, "Foo Bar", "Quality Assurance", "foo.bar@gmail.com");
        given(employeeRepository.findById(id)).willReturn(Optional.of(testEmployee));
        employeeService.deleteEmployee(id);
        employeeService.deleteEmployee(id);
        verify(employeeRepository, times(2)).deleteById(id);
    }
}









