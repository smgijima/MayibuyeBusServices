package ac.siphiwo.mgijima.busbooking.config.domain;

import java.io.Serializable;

/**
 * Created by Cornelious on 4/16/2016.
 */
public class Passenger implements Serializable{
    private Long passNumber;
    private String idNumber;
    private String name;
    private String lastName;
    private PassengerAddress objAdress;
    private Passenger(PassengerBuilder objBuilder)
    {
        this.passNumber=objBuilder.id;
        this.idNumber = objBuilder.passNumber;
        this.name=objBuilder.name;
        this.lastName=objBuilder.lastName;
        this.objAdress=objBuilder.objAdress;
    }

    public Long id() {
        return passNumber;
    }

    public String getPassNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public PassengerAddress getObjAdress() {
        return objAdress;
    }

    public static class PassengerBuilder
    {
        private Long id;
        private String passNumber;
        private String name;
        private String lastName;
        private  PassengerAddress objAdress;

        public PassengerBuilder id(Long id)
        {
            this.id =id;
            return this;
        }
        public PassengerBuilder passNumber(String passNumber)
        {
            this.passNumber =passNumber;
            return this;
        }
        public PassengerBuilder name(String name)
        {
            this.name=name;
            return this;
        }
        public PassengerBuilder lastName(String lastName)
        {
            this.lastName=lastName;
            return this;
        }
        public PassengerBuilder address(PassengerAddress objAdress)
        {
            this.objAdress=objAdress;
            return this;
        }
        public PassengerBuilder copy(Passenger objPassenger)
        {
            this.id =objPassenger.passNumber;
            this.passNumber =objPassenger.idNumber;
            this.name=objPassenger.name;
            this.lastName=objPassenger.lastName;
            this.objAdress=objPassenger.objAdress;
            return  this;
        }
        public Passenger build()
        {
            return new Passenger(this);
        }


    }
}
