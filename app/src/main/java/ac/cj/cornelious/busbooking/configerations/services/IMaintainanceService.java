package ac.cj.cornelious.busbooking.configerations.services;

import android.content.Context;

import ac.example.mgijima.bustransportingsystem.domain.Maintainance;

/**
 * Created by Cornelious on 5/10/2016.
 */
public interface IMaintainanceService {
     void addMaintainance(Context context, Maintainance maintainance);
     void updateMaintainance(Context context, Maintainance maintainance);
}
