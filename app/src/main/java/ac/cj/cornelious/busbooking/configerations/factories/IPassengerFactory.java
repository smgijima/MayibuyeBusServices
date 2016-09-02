package ac.siphiwo.mgijima.busbooking.config.factories;


import ac.example.mgijima.bustransportingsystem.domain.Passenger;
import ac.example.mgijima.bustransportingsystem.domain.PassengerAddress;

/**
 * Created by Cornelious on 4/16/2016.
 */
public interface IPassengerFactory {
    Passenger createPassenger(String passengerId, String passengerName, String passengerSurname, PassengerAddress obAddress);
}
