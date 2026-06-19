package com.payroll.repository;

import com.payroll.model.Employee;
import com.payroll.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee(Employee employee);

    List<LeaveRequest> findByStatus(
            LeaveRequest.LeaveStatus status
    );

    void deleteByEmployeeEmployeeId(Long employeeId);

    @Query("""
        SELECT l.leaveType, COUNT(l)
        FROM LeaveRequest l
        WHERE l.employee.employeeId = :employeeId
        GROUP BY l.leaveType
    """)
    List<Object[]> countLeavesByType(Long employeeId);
}