    package ac.example.mgijima.bustransportingsystem;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cornelious.busbooking.R;

import ac.example.mgijima.bustransportingsystem.config.App;
import ac.example.mgijima.bustransportingsystem.repositories.impl.BusRepoImpl;
import ac.example.mgijima.bustransportingsystem.services.impl.BusBoundService;

public class AvailableBusesActivity extends AppCompatActivity {
    private BusBoundService mService;
    BusRepoImpl busRepo ;
    private boolean status;
    ListView busListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.main_activity);
        //busListView = (ListView)findViewById(R.id.listView);

        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                BusBoundService.LocalBinder binder =(BusBoundService.LocalBinder)service;
                mService=binder.getService();
                status=true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                status=false;
            }
        };

      Intent intent = new Intent(App.getContext(),BusBoundService.class );
        App.getContext().bindService(intent,connection, Context.BIND_AUTO_CREATE);
        status=true;

        Toast.makeText(this,"service binded successfully",Toast.LENGTH_LONG).show();

        //Set <Bus>busSet=mService.find();
     /*   List<String> busList = new ArrayList<String>(busSet);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,busList);
        busListView.setAdapter(arrayAdapter);*/
    }
}
