package ac.cj.cornelious.busbooking.configerations.services.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import ac.cj.cornelious.busbooking.configerations.App;
;
import ac.cj.cornelious.busbooking.configerations.services.IPassengerService;
import ac.example.mgijima.bustransportingsystem.domain.Passenger;
import ac.example.mgijima.bustransportingsystem.repositories.impl.PassengerRepositoryImpl;

//An Intent service is used because rhere is no need for a result to be returned to the caller
public class PassengerIntentService extends IntentService implements IPassengerService {

    private static final String ACTION_ADD = "com.cj.cornelious.busbooking.config.services.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.cj.cornelious.busbooking.config.services.impl.action.UPDATE";

    // TODO: Rename parameters
    private static final String EXTRA_ADD = "com.cj.cornelious.busbooking.config.services.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.cj.cornelious.busbooking.config.services.impl.extra.UPDATE";

    private PassengerRepositoryImpl objRepo;
    private PassengerIntentService service=null;

    public PassengerIntentService getInstance(){
        if(service==null)
            service= new PassengerIntentService();
        return  service;
    }

    public PassengerIntentService() {
        super("PassengerIntentService");
        objRepo= new PassengerRepositoryImpl(App.getContext());
    }


    public void addPassenger(Context context,Passenger passenger) {
        Intent intent = new Intent(context, PassengerIntentService.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, passenger);

        context.startService(intent);
    }


    public void updatePassenger(Context context,Passenger passenger) {
        Intent intent = new Intent(context, PassengerIntentService.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, passenger);

        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Passenger passenger = (Passenger) intent.getSerializableExtra(EXTRA_ADD);

                objRepo.add(passenger);
            } else if (ACTION_UPDATE.equals(action)) {
                final Passenger passenger = (Passenger) intent.getSerializableExtra(EXTRA_UPDATE);

                objRepo.update(passenger);
            }
        }
    }


}
