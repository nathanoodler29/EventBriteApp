package prototype.android.eventsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GettingStarted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getting_started);
    }

    public void changeToSelectLocation(View view){
        Intent selectLocation = new Intent(this, SelectLocation.class);
        startActivity(selectLocation);

    }
}
