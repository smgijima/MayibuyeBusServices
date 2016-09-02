 package ac.example.mgijima.bustransportingsystem.services.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import ac.example.mgijima.bustransportingsystem.domain.ActivateTrip;
import ac.example.mgijima.bustransportingsystem.config.App;
import ac.example.mgijima.bustransportingsystem.repositories.impl.ActivateTripRepo;
import ac.example.mgijima.bustransportingsystem.repositories.IActivateTripRepo;

import java.util.Set;

 //Bound service is used since there is a need for a client-server interaction
 public class ActivateTripBoundService extends Service {
    private final IBinder localBinder = new LocalBinder ();
    private final IActivateTripRepo objtripRepo;
     private static ActivateTripBoundService service= null;
     public static ActivateTripBoundService getInstance(){
         if (service==null)
             service=new ActivateTripBoundService();
         return service;
     }


     public class LocalBinder extends Binder {
         public ActivateTripBoundService getService() {
             return ActivateTripBoundService.this;
         }
     }

    public ActivateTripBoundService() {
        objtripRepo=new ActivateTripRepo(App.getContext());
    }


    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
    public String addTrip(String departure,String time,String destination){
       ActivateTrip objTrip= new ActivateTrip.ActivateTripBuilder()
                .departure("departure")
                .time("time")
                .destination("destination")
                .build();
    ActivateTrip objAddedTrip=objtripRepo.add(objTrip);
        if (objAddedTrip!=null) {
            return "New Trip Activated";
        }
        else{
            return"Not activated";
        }

    }
     public Set<ActivateTrip> find(){
         Set<ActivateTrip> tripSet= objtripRepo.findAll();
         return tripSet;
     }


}
