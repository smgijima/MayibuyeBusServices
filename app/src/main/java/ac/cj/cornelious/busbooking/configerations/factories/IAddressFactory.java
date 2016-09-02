package ac.siphiwo.mgijima.busbooking.config.factories;


import ac.example.mgijima.bustransportingsystem.domain.PassengerAddress;
import ac.siphiwo.mgijima.busbooking.config.domain.EmpAddressVO;

/**
 * Created by Cornelious on 4/15/2016.
 */
public abstract class IAddressFactory {
     public PassengerAddress createPassengerAddress(String street, String city, String code){return null;};

     public EmpAddressVO createEmployeeAddress(Long id, String street, String city, String code){return null;};
}
