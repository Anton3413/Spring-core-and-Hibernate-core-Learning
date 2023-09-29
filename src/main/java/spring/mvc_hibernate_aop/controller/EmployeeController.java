package spring.mvc_hibernate_aop.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.mvc_hibernate_aop.dao.EmployeeDAO;
import spring.mvc_hibernate_aop.entity.Employee;
import spring.mvc_hibernate_aop.service.EmployeeService;

import java.util.List;

@RequestMapping("/")
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    String showAllEmployees(Model model){
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees",employees);
        return "all-employees";
    }

    @GetMapping("addNewEmployee")
    String addNewEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "employee-form-page";
    }

    @PostMapping("saveEmployee")
    String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }
    @GetMapping("updateInfo")
    String updateEmployee(@RequestParam("id") int id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "employee-form-page";
    }
    @GetMapping("deleteEmployee")
    String deleteEmployee(@RequestParam("id") int id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
}
