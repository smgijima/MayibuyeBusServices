package ac.example.mgijima.bustransportingsystem.factories;

import ac.example.mgijima.bustransportingsystem.domain.Ticket;

/**
 * Created by Cornelious on 4/16/2016.
 */
public interface ITicketFactory {

    Ticket createTicket(Long ticketNum, String ticketType, String route, double cost);
}
