package ac.siphiwo.mgijima.busbooking.config.domain;

import java.io.Serializable;

/**
 * Created by Cornelious on 4/13/2016.
 */
public class Employee implements Serializable{
    private long employeeNumber;
    private String empId;
    private String empName;
    private String empLastName;
    private EmpAddressVO objAddress;


   private Employee (EmployeeBuilder objEmployeeBuilder)
    {
        this.employeeNumber=objEmployeeBuilder.employeeNumber;
        this.empId =objEmployeeBuilder.empId;
        this.empName=objEmployeeBuilder.empName;
        this.empLastName=objEmployeeBuilder.empLastName;
        this.objAddress=objEmployeeBuilder.objAddress;
    }

    public long getEmployeeNumber() {
        return employeeNumber;
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public EmpAddressVO getObjAddress() {
        return objAddress;
    }

    public static class EmployeeBuilder
    {
        private long employeeNumber;
        private String empId;
        private String empName;
        private String empLastName;
        private EmpAddressVO objAddress;

        public EmployeeBuilder employeeNumber(Long employeeNumber)
        {
            this.employeeNumber=employeeNumber;
            return  this;
        }

        public EmployeeBuilder id(String empId)
        {
            this.empId= empId;
           return this ;
        }
        public EmployeeBuilder Name(String empName)
        {
            this.empName=empName;
            return this;
        }
        public EmployeeBuilder lastName(String emplastName)
        {
            this.empLastName=emplastName;
            return this;
        }
        public EmployeeBuilder address(EmpAddressVO objAddress){
            this.objAddress=objAddress;
            return this;
        }
        public EmployeeBuilder copy(Employee empValue)
        {
            this.employeeNumber=empValue.employeeNumber;
            this.empId=empValue.empId;
            this.empName=empValue.empName;
            this.empLastName=empValue.empLastName;
            this.objAddress=empValue.objAddress;
            return this;
        }

        public Employee build()
        {
            return new Employee(this);
        }

    }
}
