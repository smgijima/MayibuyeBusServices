package ac.example.mgijima.bustransportingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cornelious.busbooking.R;

import ac.example.mgijima.bustransportingsystem.config.App;
import ac.example.mgijima.bustransportingsystem.domain.Passenger;
import ac.example.mgijima.bustransportingsystem.repositories.impl.PassengerRepositoryImpl;
import ac.example.mgijima.bustransportingsystem.services.impl.PassengerIntentService;


public class Previewpassenger extends Activity{
    private TextView idNumber;
    private  TextView name;
    private  TextView lastName;
    private  TextView street;
    private  TextView city;
    private  TextView code;
    private PassengerRepositoryImpl objRepo;
    private PassengerIntentService service;
    private String key;
   private Passenger passenger;
    Intent intent;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previewpassenger);

        idNumber=(TextView)findViewById(R.id.txtID);
        name=(TextView)findViewById(R.id.txtName);
        lastName=(TextView)findViewById(R.id.txtLastName);
        street=(TextView)findViewById(R.id.txtStreet);
        city=(TextView)findViewById(R.id.txtCity);
        code=(TextView)findViewById(R.id.txtCode);
        intent=getIntent();

        bundle= intent.getExtras();
        if(bundle!=null)
        {
           passenger=((Passenger)bundle.get("pass"));
            key=(String)bundle.get("key");
            idNumber.setText(passenger.getPassNumber());
            name.setText(passenger.getName());
            lastName.setText(passenger.getLastName());
            street.setText(passenger.getObjAdress().getStreet());
            city.setText(passenger.getObjAdress().getCity());
            code.setText(passenger.getObjAdress().getStreet());


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_previewpassenger, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addPassenger(View view) {
        intent=getIntent();

        bundle= intent.getExtras();
        service=new PassengerIntentService();
        objRepo= new PassengerRepositoryImpl(App.getContext());
        key=(String) bundle.get("key");
        if(key.equals("ADD") ){
            service.addPassenger(this, passenger);
        }
        else if(key.equals("UPDATE")){
            service.updatePassenger(this, passenger);
            Toast.makeText(getBaseContext(),"updated",Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this,DisplayRecords.class);
        startActivity(intent);
    }
}
