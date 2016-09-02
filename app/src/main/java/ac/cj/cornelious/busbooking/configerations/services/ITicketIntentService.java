package ac.cj.cornelious.busbooking.configerations.services;

import android.content.Context;

import ac.example.mgijima.bustransportingsystem.domain.Ticket;


/**
 * Created by Cornelious on 5/10/2016.
 */
public interface ITicketIntentService {
     void addTicket(Context context,Ticket ticket);
     void updateTicket(Context context,Ticket ticket);
}
