package ac.example.mgijima.bustransportingsystem.services;

import android.content.Context;

import ac.example.mgijima.bustransportingsystem.domain.Employee;

/**
 * Created by Cornelious on 5/10/2016.
 */
public interface IEmployeeIntentService {
     void addEmp(Context context,Employee employee);
      void updateEmp(Context context,Employee employee);
}
