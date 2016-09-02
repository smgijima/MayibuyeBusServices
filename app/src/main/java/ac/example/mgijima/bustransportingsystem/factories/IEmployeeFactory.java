package ac.example.mgijima.bustransportingsystem.factories;

import ac.example.mgijima.bustransportingsystem.domain.EmpAddressVO;
import ac.example.mgijima.bustransportingsystem.domain.Employee;

/**
 * Created by Cornelious on 4/14/2016.
 */
public interface IEmployeeFactory {
    Employee createEmployee(Long employeeNumber,String empId, String name, String surname,EmpAddressVO objAddress);

}
