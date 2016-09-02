package ac.example.mgijima.bustransportingsystem.factories.impl;

import ac.example.mgijima.bustransportingsystem.domain.Bus;

/**
 * Created by Cornelious on 4/16/2016.
 */
public class BusFactoryImpl  {
    private static BusFactoryImpl objBusFactory=null;
    public BusFactoryImpl getInstance()
    {
        if (objBusFactory==null)
            objBusFactory= new BusFactoryImpl();
        return objBusFactory;
    }

    public static Bus createBus(String numberPlate, int numberOfSeats) {
        Bus objBus= new Bus.BusBuilder()
                .getnumberPlate(numberPlate)
                .seats(numberOfSeats)
                .build();
        return objBus;
    }
}
