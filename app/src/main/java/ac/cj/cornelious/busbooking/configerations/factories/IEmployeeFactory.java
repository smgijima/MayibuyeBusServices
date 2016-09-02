package ac.cj.cornelious.busbooking.configerations.factories;


import ac.example.mgijima.bustransportingsystem.domain.EmpAddressVO;
import ac.example.mgijima.bustransportingsystem.domain.Employee;

public interface IEmployeeFactory {
    Employee createEmployee(Long employeeNumber, String empId, String name, String surname, EmpAddressVO objAddress);



}
