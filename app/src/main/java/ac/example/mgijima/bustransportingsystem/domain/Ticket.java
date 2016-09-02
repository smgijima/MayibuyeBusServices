package ac.example.mgijima.bustransportingsystem.domain;

import java.io.Serializable;

/**
 * Created by Cornelious on 4/16/2016.
 */
public class Ticket implements Serializable {
    private Long ticketNum;
    private  String ticketType;
    private String route;
    private double cost;
    private Ticket(TicketBuilder objBuilder)
    {
        this.ticketNum=objBuilder.ticketNum;
        this.ticketType=objBuilder.ticketType;
        this.route=objBuilder.route;
        this.cost=objBuilder.cost;
    }

    public Long getTicketNum() {
        return ticketNum;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getRoute() {
        return route;
    }

    public double getCost() {
        return cost;
    }
    public static class TicketBuilder
    {
        private Long ticketNum;
        private  String ticketType;
        private String route;
        private double cost;
        public TicketBuilder ticketNumber(Long tickrtNumber)
        {
            this.ticketNum=tickrtNumber;
            return this;
        }
        public TicketBuilder ticketType(String ticketType)
        {
            this.ticketType=ticketType;
            return this;
        }
        public TicketBuilder route(String route)
        {
            this.route=route;
            return this;
        }
        public TicketBuilder cost(double cost)
        {
            this.cost=cost;
            return this;
        }
      public  TicketBuilder copy(Ticket objTicket)
      {
          this.ticketNum=objTicket.ticketNum;
          this.ticketType=objTicket.ticketType;
          this.route=objTicket.route;
          this.cost=objTicket.cost;
          return this;
      }
        public Ticket build()
        {
            return new Ticket(this);
        }
    }

}
