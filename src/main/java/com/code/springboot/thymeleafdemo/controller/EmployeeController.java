package com.code.springboot.thymeleafdemo.controller;

import com.code.springboot.thymeleafdemo.entity.Employee;
import com.code.springboot.thymeleafdemo.serviceOrbusinnes.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller

public class EmployeeController {


	@Autowired
	private EmployeeService employeeService;


	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}




	@GetMapping("/list")
	public String listEmployees(HttpServletResponse response, Model theModel) throws IOException {
		List<Employee> list = employeeService.getAllEmployee();
		theModel.addAttribute("list", list);

		return "employees/list-employees";
	}


	@GetMapping("/showFormForAdd")
	public String showFormAdd(Model model) {
		Employee theEmployee = new Employee();
		model.addAttribute("list",theEmployee);
		return "employees/employee-form";
	}


	@PostMapping("/save")
	public String save(@ModelAttribute("list") Employee employee) throws IOException {


		employeeService.save(employee);
		return "redirect:/list";
	}

	@PostMapping("/save2")
	public String save2(@ModelAttribute("list") @RequestParam("file")MultipartFile file , Employee employee) throws IOException {
		employee.setContent(file.getBytes());

		employeeService.save(employee);
		return "redirect:/list";
	}

	@GetMapping("/image")
	public void showImage(@RequestParam("id") Long id, HttpServletResponse response,Optional<Employee> employee) throws IOException {
		employee=employeeService.findEmployeeById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
		response.getOutputStream().write(employee.get().getContent());
		response.getOutputStream().close();

	}

	@GetMapping("/showFormForUpdate")
	//çalışan kimliği için bir istek parametresi kabul eder ve formdan gelen employeId yı alır
	public String showFormFoUpdate(@RequestParam("employeeId") int theId,Model model) {
		Employee theEmployee =employeeService.findById(theId);
		model.addAttribute("list",theEmployee);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate2")
	//çalışan kimliği için bir istek parametresi kabul eder ve formdan gelen employeId yı alır
	public String showFormFoUpdate2(@RequestParam("employeeId") int theId,Optional<Employee> employee, Model model) {
		Employee theEmployee =employeeService.findById(theId);
		model.addAttribute("list",theEmployee);

		return "employees/employee-form-image";
	}


	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId,Model model) {
		employeeService.deleteById(theId);
		return "redirect:/list";
	}

@GetMapping("/")
	public String LoginPage() {
	return "employees/list-employees";
}
	@GetMapping("/news")
	public String news(Model theModel) {
		List<Employee> theEmployees=employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("list", theEmployees);
		return "news/news";
	}

	@GetMapping("/login")
	public String showMyLoginPage() {
		return "plain-login";
	}

	@RequestMapping(path = {"/","/search"})
	public String home(Employee shop, Model model, String keyword) {
		if(keyword!=null) {
			List<Employee> list = employeeService.getByKeyword(keyword);
			model.addAttribute("list", list);
		}else {
			List<Employee> list = employeeService.getAllEmployee();
			model.addAttribute("list", list);}
		return "news/news";
	}

	@RequestMapping(path = {"/search2"})
	public String home2(Employee shop, Model model, String keyword) {
		if(keyword!=null) {
			List<Employee> list = employeeService.getByKeyword(keyword);
			model.addAttribute("list", list);
		}else {
			List<Employee> list = employeeService.getAllEmployee();
			model.addAttribute("list", list);}
		return "employees/list-employees";
	}


}









