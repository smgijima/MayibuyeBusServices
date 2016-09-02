package ac.cj.cornelious.busbooking.configerations.services.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import ac.cj.cornelious.busbooking.configerations.App;

import ac.cj.cornelious.busbooking.configerations.services.IEmployeeIntentService;
import ac.example.mgijima.bustransportingsystem.domain.Employee;
import ac.example.mgijima.bustransportingsystem.repositories.impl.EmployeeRepoImpl;

//An Intent service is used because rhere is no need for a result to be returned to the caller
public class EmployeeIntentService extends IntentService implements IEmployeeIntentService {
    private final EmployeeRepoImpl objRepo;
    private EmployeeIntentService service=null;

    private static final String ACTION_ADD = "com.cj.cornelious.busbooking.config.services.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.cj.cornelious.busbooking.config.services.impl.action.UPDATE";

    // TODO: Rename parameters
    private static final String EXTRA_ADD = "com.cj.cornelious.busbooking.config.services.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.cj.cornelious.busbooking.config.services.impl.extra.UPDATE";


            public EmployeeIntentService getInstance(){
                if (service==null)
                    service= new EmployeeIntentService();
                return  service;
            }

    public EmployeeIntentService() {
        super("EmployeeIntentService");
        objRepo= new EmployeeRepoImpl(App.getContext());
    }


    public void addEmp(Context context,Employee employee){
        Intent intent = new Intent(context, EmployeeIntentService.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, employee);

        context.startService(intent);
    }


    public  void updateEmp(Context context,Employee employee) {
        Intent intent = new Intent(context, EmployeeIntentService.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, employee);

        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Employee employee = (Employee)intent.getSerializableExtra(EXTRA_ADD);

                objRepo.add(employee);
            } else if (ACTION_UPDATE.equals(action)) {
                final Employee employee =(Employee) intent.getSerializableExtra(EXTRA_UPDATE);

                objRepo.update(employee);
            }
        }
    }


}
