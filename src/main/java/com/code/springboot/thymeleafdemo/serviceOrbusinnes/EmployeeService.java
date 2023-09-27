package com.code.springboot.thymeleafdemo.serviceOrbusinnes;

import java.util.List;
import java.util.Optional;

import com.code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {

	List<Employee> getAllEmployee();


	Optional<Employee> findEmployeeById(long id);

	List<Employee> findAll();

	Employee findById(long theId);

	void save(Employee theEmployee);


	void deleteById(long theId);

	List<Employee> getByKeyword(String keyword);

}
