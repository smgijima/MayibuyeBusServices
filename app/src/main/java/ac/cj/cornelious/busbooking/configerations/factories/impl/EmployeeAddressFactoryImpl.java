package ac.cj.cornelious.busbooking.configerations.factories.impl;


import ac.example.mgijima.bustransportingsystem.domain.EmpAddressVO;
import ac.example.mgijima.bustransportingsystem.factories.IAddressFactory;

public class EmployeeAddressFactoryImpl extends IAddressFactory {
    private static EmployeeAddressFactoryImpl objAddressFactory=null;
    public static EmployeeAddressFactoryImpl getInstance()
    {
        if (objAddressFactory==null)
            objAddressFactory=new EmployeeAddressFactoryImpl();
        return objAddressFactory;
    }
    @Override
    public EmpAddressVO createEmployeeAddress(Long id, String street, String city, String code){
        EmpAddressVO objAddress= new EmpAddressVO.AddressBuilder()
                .id(id)
                .street(street)
                .city(city)
                .code(code)
                .build();
        return objAddress;
        }


}
