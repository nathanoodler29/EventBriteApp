package prototype.android.eventsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent splash_intent = new Intent(this,GettingStarted.class);
        startActivity(splash_intent);
        finish();
    }


}
