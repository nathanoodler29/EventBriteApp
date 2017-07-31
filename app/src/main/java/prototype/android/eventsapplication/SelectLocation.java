package prototype.android.eventsapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import prototype.android.eventsapplication.prototype.android.eventsapplication.request.handler.JsonRequestHandler;

public class SelectLocation extends AppCompatActivity {

    private JsonRequestHandler requestHandler = new JsonRequestHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

    }

    public void goToUk(View view){
        Intent changeToUk = new Intent(this, UKEvents.class);
        startActivity(changeToUk);

    }

    public void goToAmsterdam(View view){
        Intent changeToAmsterdam = new Intent(this, AmsterdamEvents.class);
        startActivity(changeToAmsterdam);

    }


}
