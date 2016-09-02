package ac.cj.cornelious.busbooking.configerations;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ac.cj.cornelious.busbooking.configerations.factories.impl.PassengerAddressFactoryImpl;
import ac.cj.cornelious.busbooking.configerations.factories.impl.PassengerFactoryImpl;
import ac.example.mgijima.bustransportingsystem.domain.Passenger;
import ac.example.mgijima.bustransportingsystem.domain.PassengerAddress;
import ac.example.mgijima.bustransportingsystem.repositories.IPassengerRepository;
import ac.example.mgijima.bustransportingsystem.repositories.impl.PassengerRepositoryImpl;

import com.example.cornelious.busbooking.R;


public class PassengerActivity extends Activity {
    private EditText  idNumber;
    private EditText name;
    private EditText lastName;
    private EditText street;
    private EditText city;
    private EditText code;
    private EditText searchId;
    private Button button;

    private String key;
    private IPassengerRepository repo;
    Passenger passenger  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registercustomeractivity);

        idNumber = (EditText) findViewById(R.id.txtID);
    //    button= (Button)findViewById(R.id.btnPreview);
        name = (EditText) findViewById(R.id.txtName);
        lastName = (EditText) findViewById(R.id.txtSurname);
        street = (EditText) findViewById(R.id.txtStreetName);
        city = (EditText) findViewById(R.id.txtCity);
        code = (EditText) findViewById(R.id.txtCode);
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
        DialogFragment dialogFragment;
        try {



            Intent getIntent=getIntent();
            Bundle bundle= getIntent.getExtras();
            key=(String)bundle.get("key");

            Intent intent = new Intent(getApplicationContext(), Previewpassenger.class);

            //intent.putExtra("idNumber", idNumber.getText().toString());
            //intent.putExtra("name", name.getText().toString());
            //intent.putExtra("lastname", lastName.getText().toString());
           // intent.putExtra("street", street.getText().toString());
           // intent.putExtra("code", code.getText().toString());
           // intent.putExtra("city", city.getText().toString());
            intent.putExtra("key",key);
            if(key.equals("ADD")){
            PassengerAddress address = new PassengerAddressFactoryImpl().createPassengerAddress(
                    street.getText().toString(),
                    code.getText().toString(),
                    city.getText().toString());
            Passenger newPassenger = new PassengerFactoryImpl().createPassenger(
                    idNumber.getText().toString(),
                    name.getText().toString(),
                    lastName.getText().toString(),
                    address);

                intent.putExtra("pass",newPassenger);
                startActivity(intent);
            }
            else if(key.equals("UPDATE"))
            {


                if(passenger!=null)
                {
                    PassengerAddress address = new PassengerAddress.AddressBuilder()
                            .copy(passenger.getObjAdress())
                            .street( street.getText().toString())
                            .city(city.getText().toString())
                            .code(lastName.getText().toString())
                            .build();
                    Passenger update = new Passenger.PassengerBuilder()
                            .copy(passenger)
                            .passNumber(idNumber.getText().toString())
                            .name(name.getText().toString())
                            .lastName(lastName.getText().toString())
                            .address(address)
                            .build();

                    intent.putExtra("pass",update);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getBaseContext(), "The record could not be found", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getBaseContext(),"no impl yet",Toast.LENGTH_LONG).show();

            }


        }
        catch(NumberFormatException nfe)
        {
            Toast.makeText(getBaseContext(),"Make sure ID number and code are in number format",Toast.LENGTH_LONG).show();

        }
        catch (NullPointerException npe){
            Toast.makeText(getBaseContext(),"null",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){}
    }
    public Passenger SearchByID(View view){
        repo= new PassengerRepositoryImpl(App.getContext());
      try {
          searchId=(EditText)findViewById(R.id.txtSearch);
          Long id =Long.parseLong(searchId.getText().toString());

           passenger = repo.findById(id);
         if (passenger!=null) {
             idNumber = (EditText) findViewById(R.id.txtID);
             name = (EditText) findViewById(R.id.txtName);
             lastName = (EditText) findViewById(R.id.txtSurname);
             street = (EditText) findViewById(R.id.txtStreetName);
             city = (EditText) findViewById(R.id.txtCity);
             code = (EditText) findViewById(R.id.txtCode);

             street.setText(passenger.getPassNumber());
             name.setText(passenger.getName());
             lastName.setText(passenger.getLastName());
             city.setText(passenger.getObjAdress().getCity());
             code.setText(passenger.getObjAdress().getCode());
             return passenger;
         }
          else{
             Toast.makeText(getBaseContext(), "The record could not be found", Toast.LENGTH_LONG).show();
             return  null;
         }

          //return passenger;
      }
      catch (NumberFormatException nfe){
          Toast.makeText(getBaseContext(), "Make sure your search id is a number", Toast.LENGTH_LONG).show();
          return  null;
      }
      catch (Exception e)
      {
          Toast.makeText(getBaseContext(), "something went wrong", Toast.LENGTH_LONG).show();
          return  null;
      }
    }
    public void clear(){
        idNumber.setText("");
        name.setText("");
        lastName.setText("");
        street.setText("");
        city.setText("");
        code.setText("");
    }
    public void deletePassenger(View view){
       if (passenger!= null){
           repo.remove(passenger);
           clear();
           Toast.makeText(getBaseContext(), "successfully deleted", Toast.LENGTH_LONG).show();
       }
        else{
           Toast.makeText(getBaseContext(), "did not find the record", Toast.LENGTH_LONG).show();
       }
    }

}
