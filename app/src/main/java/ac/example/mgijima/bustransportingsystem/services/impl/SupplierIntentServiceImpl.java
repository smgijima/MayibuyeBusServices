package ac.example.mgijima.bustransportingsystem.services.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import ac.example.mgijima.bustransportingsystem.config.App;
import ac.example.mgijima.bustransportingsystem.domain.Supplier;
import ac.example.mgijima.bustransportingsystem.repositories.impl.SupplierRepoImpl;
import ac.example.mgijima.bustransportingsystem.services.ISupplierIntentService;

//An Intent service is used because rhere is no need for a result to be returned to the caller
public class SupplierIntentServiceImpl extends IntentService implements ISupplierIntentService {

    private static final String ACTION_ADD = "com.example.cornelious.busbooking.services.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.example.cornelious.busbooking.services.impl.action.UPDATE";

    // TODO: Rename parameters
    private static final String EXTRA_ADD = "com.example.cornelious.busbooking.services.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.example.cornelious.busbooking.services.impl.extra.UPDATE";
    private SupplierRepoImpl objRepo;
    public SupplierIntentServiceImpl() {
        super("SupplierIntentServiceImpl");
        objRepo= new SupplierRepoImpl(App.getContext());
    }


    private  static SupplierIntentServiceImpl supplierIntentService=null;
    public static  SupplierIntentServiceImpl getInstance(){
        if(supplierIntentService==null)
            supplierIntentService= new SupplierIntentServiceImpl();
        return supplierIntentService;
    }
    public void addPassenger(Context context,Supplier supplier) {
        Intent intent = new Intent(context, SupplierIntentServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, supplier);

        context.startService(intent);
    }


    public  void updatePassenger(Context context,Supplier supplier) {
        Intent intent = new Intent(context, SupplierIntentServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, supplier);

        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Supplier supplier =(Supplier) intent.getSerializableExtra(EXTRA_ADD);
                objRepo.add(supplier);
            } else if (ACTION_UPDATE.equals(action)) {
                final Supplier supplier =(Supplier) intent.getSerializableExtra(EXTRA_UPDATE);

                objRepo.update(supplier);
            }
        }
    }


}
