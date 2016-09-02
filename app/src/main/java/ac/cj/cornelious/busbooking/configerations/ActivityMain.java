package ac.cj.cornelious.busbooking.configerations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cornelious.busbooking.R;

/**
 * Created by Cornelious on 8/20/2016.
 */
public class ActivityMain extends AppCompatActivity {
    private     String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


    }
    public void addPassenger(View view){
        try {
         key="ADD";
            Intent intent = new Intent(getApplicationContext(), PassengerActivity.class);
            intent.putExtra("key",key);
            startActivity(intent);
            }
            catch(Exception nfe)
            {
            }
    }
   public void  displayAll(View view){
       try{
           Intent intent = new Intent(getApplicationContext(), DisplayRecords.class);
           startActivity(intent);
       }
       catch(Exception e) {

       }
   }
    public void updateRecord(View view){
        try{

            key ="UPDATE";
            Intent intent = new Intent(getApplicationContext(), PassengerActivity.class);
            intent.putExtra("key",key);
            startActivity(intent);
        }
        catch ( Exception e) {
        }
            }

public void Delete(View view){
    key="DELETE";
    Intent intent = new Intent(getApplicationContext(), PassengerActivity.class);
    intent.putExtra("key",key);
    startActivity(intent);
}
}