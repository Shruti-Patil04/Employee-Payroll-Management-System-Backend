// PayrollRepository.java
package com.payroll.repository;

import com.payroll.model.Employee;
import com.payroll.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
	@Transactional
	void deleteByEmployeeEmployeeId(Long employeeId);
    List<Payroll> findByEmployee(Employee employee);
    List<Payroll> findByMonthAndYear(Integer month, Integer year);
    Optional<Payroll> findByEmployeeAndMonthAndYear(Employee employee, Integer month, Integer year);
}