package com.code.springboot.thymeleafdemo.serviceOrbusinnes;

import java.util.List;
import java.util.Optional;

import com.code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service // bileşen taraması sayesinde bu bileşeni otomatik olarak kaydedecektir.
public class EmployeeServiceImpl implements EmployeeService {


	private EmployeeRepository employeeRepository;


	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}





	@Override
	public List<Employee> getAllEmployee(){
		return  employeeRepository.findAll();
	}



	@Override
	public Optional<Employee> findEmployeeById(long id) {
		return  employeeRepository.findById(id);
	}







	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(long theId) {
		Optional<Employee> result = employeeRepository.findById((theId));

		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		}
		else {

			throw new RuntimeException("bulunamadı - " + theId);
		}

		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(long theId) {
		employeeRepository.deleteById((theId));
	}

	@Override
	public List<Employee> getByKeyword(String keyword) {
		return employeeRepository.findByKeyword(keyword);
	}

}






