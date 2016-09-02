package ac.example.mgijima.bustransportingsystem.factories.impl;

import ac.example.mgijima.bustransportingsystem.factories.IEmployeeFactory;
import ac.example.mgijima.bustransportingsystem.domain.EmpAddressVO;
import ac.example.mgijima.bustransportingsystem.domain.Employee;

/**
 * Created by Cornelious on 4/15/2016.
 */
public class EmployeeFactoryImpl implements IEmployeeFactory {
    private static EmployeeFactoryImpl objEmpFactory=null;

    public IEmployeeFactory getInstance()
    {
        if (objEmpFactory==null)
            objEmpFactory=new EmployeeFactoryImpl();
        return objEmpFactory;
    }

    @Override
    public Employee createEmployee(Long employeeNumber,String empId, String empName, String empSurname,EmpAddressVO objAddress) {
        Employee objEmployee = new Employee.EmployeeBuilder()
                .employeeNumber(employeeNumber)
                .id(empId)
                .Name(empName)
                .lastName(empSurname)
                .address(objAddress)
                .build();
        return objEmployee;
    }
}
