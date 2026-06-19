package com.payroll.config;

import com.github.javafaker.Faker;
import com.payroll.model.Department;
import com.payroll.model.Employee;
import com.payroll.model.JobRole;
import com.payroll.model.User;
import com.payroll.model.User.Role;
import com.payroll.repository.DepartmentRepository;
import com.payroll.repository.EmployeeRepository;
import com.payroll.repository.JobRoleRepository;
import com.payroll.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            UserRepository userRepository,
            DepartmentRepository departmentRepository,
            JobRoleRepository jobRoleRepository,
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            Faker faker = new Faker();
            Random random = new Random();

            // Create Admin User
            if (userRepository.findByUsername("admin").isEmpty()) {

                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                
                admin.setIsActive(true);
                admin.setCreatedAt(LocalDateTime.now());
                admin.setUpdatedAt(LocalDateTime.now());

                userRepository.save(admin);

                System.out.println("Admin user created!");
            }

            // Create Departments
            if (departmentRepository.count() == 0) {

                Department hr = new Department();
                hr.setDepartmentName("HR");
                hr.setDescription("Human Resources");

                Department it = new Department();
                it.setDepartmentName("IT");
                it.setDescription("Information Technology");

                Department finance = new Department();
                finance.setDepartmentName("Finance");
                finance.setDescription("Finance Department");

                Department marketing = new Department();
                marketing.setDepartmentName("Marketing");
                marketing.setDescription("Marketing Department");

                departmentRepository.saveAll(
                        List.of(hr, it, finance, marketing));

                System.out.println("Departments created!");
            }

            // Create Job Roles
            if (jobRoleRepository.count() == 0) {

                JobRole developer = new JobRole();
                developer.setJobTitle("Java Developer");
                developer.setDescription("Backend Developer");
                developer.setBaseSalary(BigDecimal.valueOf(50000));

                JobRole hrRole = new JobRole();
                hrRole.setJobTitle("HR Manager");
                hrRole.setDescription("HR Department");
                hrRole.setBaseSalary(BigDecimal.valueOf(45000));

                JobRole tester = new JobRole();
                tester.setJobTitle("QA Tester");
                tester.setDescription("Testing Team");
                tester.setBaseSalary(BigDecimal.valueOf(40000));

                JobRole manager = new JobRole();
                manager.setJobTitle("Project Manager");
                manager.setDescription("Management Team");
                manager.setBaseSalary(BigDecimal.valueOf(70000));

                jobRoleRepository.saveAll(
                        List.of(developer, hrRole, tester, manager));

                System.out.println("Job roles created!");
            }

            // Create Random Employees
            if (employeeRepository.count() == 0) {

                List<Department> departments =
                        departmentRepository.findAll();

                List<JobRole> jobRoles =
                        jobRoleRepository.findAll();

                for (int i = 0; i < 20; i++) {

                    User user = new User();
                    user.setUsername(
                            faker.name().username() + i);

                    user.setEmail(
                            faker.internet().emailAddress());

                    user.setPassword(
                            passwordEncoder.encode("Admin@123"));
                    
                    user.setRole(Role.EMPLOYEE);
                   
                    user.setIsActive(true);
                    user.setCreatedAt(LocalDateTime.now());
                    user.setUpdatedAt(LocalDateTime.now());

                    userRepository.save(user);

                    Employee employee = new Employee();

                    employee.setFirstName(
                            faker.name().firstName());

                    employee.setLastName(
                            faker.name().lastName());

                    employee.setPhoneNumber(
                            "9" + faker.number().digits(9)
                    );
                    
                    employee.setAddress(
                            faker.address().fullAddress()
                    );

                    employee.setDateOfBirth(
                            LocalDate.now()
                                    .minusYears(random.nextInt(20) + 22)
                    );

                    employee.setHireDate(LocalDate.now());

                    employee.setLeaveBalance(12);

                    employee.setDepartment(
                            departments.get(
                                    random.nextInt(departments.size())
                            )
                    );

                    employee.setJobRole(
                            jobRoles.get(
                                    random.nextInt(jobRoles.size())
                            )
                    );

                    employee.setUser(user);

                    employeeRepository.save(employee);
                }

                System.out.println("Random employees created!");
            }
        };
    }
}