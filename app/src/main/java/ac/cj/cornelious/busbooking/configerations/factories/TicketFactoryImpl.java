package ac.siphiwo.mgijima.busbooking.config.factories;


import ac.example.mgijima.bustransportingsystem.domain.Ticket;

public class TicketFactoryImpl implements ITicketFactory {
    private static TicketFactoryImpl objTicketFactory=null;
    public TicketFactoryImpl getInstance()
    {
        if(objTicketFactory==null)
            objTicketFactory=new TicketFactoryImpl();
        return objTicketFactory;
    }
    @Override
    public Ticket createTicket(Long ticketNum, String ticketType, String route, double cost) {
        Ticket objTicket = new Ticket.TicketBuilder()
                .ticketNumber(ticketNum)
                .ticketType(ticketType)
                .route(route)
                .cost(cost)
                .build();
        return objTicket;
    }
}
