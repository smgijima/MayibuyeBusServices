package ac.cj.cornelious.busbooking.configerations.services.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import ac.cj.cornelious.busbooking.configerations.App;
import ac.example.mgijima.bustransportingsystem.domain.Account;
import ac.example.mgijima.bustransportingsystem.repositories.IAccountRepo;
import ac.example.mgijima.bustransportingsystem.repositories.impl.AccountRepoImpl;


//Bound service is used since there is a need for a client-server interaction
public class AccountBoundService extends Service {
    private final IBinder mBinder= new LocalBinder();
    private final IAccountRepo objRepo;
    private static BusBoundService service= null;

    public static BusBoundService getInstance(){
        if (service==null)
            service=new BusBoundService();
        return service;
    }


    public AccountBoundService() {
        objRepo= new AccountRepoImpl(App.getContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public class LocalBinder extends Binder {
        public AccountBoundService getService() {
            return AccountBoundService.this;
        }
    }

        public String activateAccount( String username,String password){
            Account account= new Account.AccountBuilder()
                    .username(username)
                    .password(password)
                    .build();
            Account savedAccount=objRepo.add(account);
            if(savedAccount!=null){
                return "ACCOUNT ACTIVATED";
            }
            else{
                return"NOT ACTIVATED";
            }
        }
    }



