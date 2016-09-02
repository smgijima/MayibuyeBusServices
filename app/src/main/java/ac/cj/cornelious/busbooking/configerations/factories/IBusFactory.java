package ac.siphiwo.mgijima.busbooking.config.factories;

import ac.example.mgijima.bustransportingsystem.domain.Bus;

/**
 * Created by Cornelious on 4/16/2016.
 */
public interface IBusFactory {
    Bus createBus(String numberPlate, int numberOfSeats);
}
