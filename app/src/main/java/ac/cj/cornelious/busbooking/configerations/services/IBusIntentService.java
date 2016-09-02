package ac.cj.cornelious.busbooking.configerations.services;

import android.content.Context;

import ac.example.mgijima.bustransportingsystem.domain.Bus;

/**
 * Created by Cornelious on 5/10/2016.
 */
public interface IBusIntentService {
      void addBus(Context context,Bus bus);
      void updateBus(Context context,Bus bus);
}
