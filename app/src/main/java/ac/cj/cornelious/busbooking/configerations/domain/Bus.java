package ac.siphiwo.mgijima.busbooking.config.domain;

import java.io.Serializable;

/**
 * Created by Cornelious on 4/16/2016.
 */
public class Bus implements Serializable {

    private Long busNumber;
    private String numberPlate;
    private int numberOfSeats;


    public  Bus(BusBuilder objBusBuilder)
    {
        this.busNumber=objBusBuilder.busNumber;
        this.numberPlate=objBusBuilder.numberPlate;
        this.numberOfSeats=objBusBuilder.numberOfseats;

    }

    public Long getBusNumber() {
        return busNumber;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public int getSeats() {
        return numberOfSeats;
    }
    public static class BusBuilder
    {
        private Long busNumber;
        private String numberPlate;
        private int numberOfseats;

        public BusBuilder busNumber(Long busNumber)
        {
            this.busNumber=busNumber;
                    return this;
        }
        public BusBuilder getnumberPlate(String numberPlate)
        {
            this.numberPlate=numberPlate;
            return this;
        }
        public BusBuilder seats(int numberOfSeats)
        {
            this.numberOfseats=numberOfSeats;
            return this;
        }

        public BusBuilder copy(Bus objBus)
        {
            this.busNumber=objBus.busNumber;
            this.numberPlate=objBus.numberPlate;
            this.numberOfseats=objBus.numberOfSeats;
            return this;
        }
        public Bus build()
        {
            return new Bus(this);
        }


    }
}
