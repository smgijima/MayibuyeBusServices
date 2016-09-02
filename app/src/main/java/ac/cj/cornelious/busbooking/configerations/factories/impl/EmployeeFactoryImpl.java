

import ac.example.mgijima.bustransportingsystem.domain.EmpAddressVO;
import ac.example.mgijima.bustransportingsystem.domain.Employee;
import ac.example.mgijima.bustransportingsystem.factories.IEmployeeFactory;


public class EmployeeFactoryImpl implements IEmployeeFactory {
    private static EmployeeFactoryImpl objEmpFactory=null;

    public IEmployeeFactory getInstance()
    {
        if (objEmpFactory==null)
            objEmpFactory=new EmployeeFactoryImpl();
        return objEmpFactory;
    }


    @Override
    public Employee createEmployee(Long employeeNumber, String empId, String empName, String empSurname, EmpAddressVO objAddress) {
        Employee objEmployee = new Employee.EmployeeBuilder()
                .employeeNumber(employeeNumber)
                .id(empId)
                .Name(empName)
                .lastName(empSurname)
                .build();
        return objEmployee;
    }
}
