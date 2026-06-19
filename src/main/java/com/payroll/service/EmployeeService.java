package com.payroll.service;

import com.payroll.dto.EmployeeDto;
import java.util.List;
import java.util.Map;
public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto getEmployeeByUserId(Long userId);
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);
    Map<String, Long> getLeaveSummary(Long employeeId);
}