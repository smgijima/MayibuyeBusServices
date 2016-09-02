package ac.example.mgijima.bustransportingsystem.domain;

import java.io.Serializable;


public class ActivateTrip implements Serializable{
    private String departure;
    private String time;
    private String destination;
    private Long id;

    private ActivateTrip(ActivateTripBuilder objBuilder){
        this.id=objBuilder.id;
        this.departure=objBuilder.departure;
        this.time=objBuilder.time;
        this.destination=objBuilder.destination;
    }
    public String getDeparture() {
        return departure;
    }

    public String getTime() {
        return time;
    }

    public String getDestination() {
        return destination;
    }

    public Long getId() {
        return id;
    }
    public static class ActivateTripBuilder{
        private String departure;
        private String time;
        private String destination;
        private Long id;



        public ActivateTripBuilder id(Long id){
            this.id=id;
            return this;
        }
        public ActivateTripBuilder departure(String departure){
            this.departure=departure;
            return this;
        }
        public ActivateTripBuilder time(String time){
            this.time=time;
            return this;
        }
        public ActivateTripBuilder destination(String destination){
            this.destination=destination;
            return this;
        }
        public  ActivateTripBuilder copy(ActivateTrip objTrip){
            this.id=objTrip.id;
            this.departure=objTrip.departure;
            this.time=objTrip.time;
            this.destination=objTrip.destination;
            return this;
        }
        public ActivateTrip build(){
            return new ActivateTrip(this);
        }

    }
}