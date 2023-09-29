package spring.mvc_hibernate_aop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mvc_hibernate_aop.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Employee> getAllEmployees() {
       Query<Employee> query = factory.getCurrentSession().createQuery("from Employee ", Employee.class);
       List<Employee> employees = query.getResultList();
       return employees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        factory.getCurrentSession().saveOrUpdate(employee);
    }

    @Override
   public Employee getEmployeeById(int id) {
        Employee employee = factory.getCurrentSession().get(Employee.class,id);
        System.out.println(employee.getId());
        return  employee;
    }

    @Override
    public void deleteEmployeeById(int id) {
        Session session = factory.getCurrentSession();
        Employee employee = session.get(Employee.class,id);

        session.remove(employee);

    }
}
