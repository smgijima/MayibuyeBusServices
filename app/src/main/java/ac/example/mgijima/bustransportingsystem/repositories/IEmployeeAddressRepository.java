package ac.example.mgijima.bustransportingsystem.repositories;



import ac.example.mgijima.bustransportingsystem.domain.EmpAddressVO;

/**
 * Created by Cornelious on 4/17/2016.
 */
public interface IEmployeeAddressRepository {
    EmpAddressVO addAddress (String key, EmpAddressVO objAddress);
    EmpAddressVO findAddress(String key);
    EmpAddressVO remove(String key);
}
