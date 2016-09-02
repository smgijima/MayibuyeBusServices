package ac.cj.cornelious.busbooking.configerations.factories.impl;


import ac.example.mgijima.bustransportingsystem.domain.PassengerAddress;
import ac.example.mgijima.bustransportingsystem.factories.IAddressFactory;

/**
 * Created by Cornelious on 4/16/2016.
 */
public class PassengerAddressFactoryImpl extends IAddressFactory {
    private static PassengerAddressFactoryImpl objAddressFactory=null;
    public static PassengerAddressFactoryImpl getInstance()
    {
        if (objAddressFactory==null)
            objAddressFactory=new PassengerAddressFactoryImpl();
        return objAddressFactory;
    }
    @Override
    public PassengerAddress createPassengerAddress(String street, String city, String code) {
        PassengerAddress objAddress= new PassengerAddress.AddressBuilder()
                .street(street)
                .city(city)
                .code(code)
                .build();

        return objAddress;
    }
}
