package prototype.android.eventsapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import prototype.android.eventsapplication.prototype.android.eventsapplication.adapter.EventAdapter;
import prototype.android.eventsapplication.prototype.android.eventsapplication.request.handler.JsonRequestHandler;
import prototype.android.eventsapplication.protoype.android.eventsapplication.model.Event;

public class AmsterdamEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amsterdam_events);
        new PerformLookUpForEventBriteAmsterdam().execute();
    }

    private class PerformLookUpForEventBriteAmsterdam extends AsyncTask<String, Void, ArrayList<Event>> {


        private JsonRequestHandler requestHandler = new JsonRequestHandler();
        private ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        private TextView loadingText = (TextView) findViewById(R.id.loadingText);


        /**
         * Performs the get request on the dark API
         *
         * @param params The Arraylist is a param, and the url for the get request.
         * @return Event data from the request as a event arraylist.
         */
        @Override
        protected ArrayList<Event> doInBackground(String... params) {


            return requestHandler.parseJson("https://www.eventbriteapi.com/v3/events/search/?token=VBUSKKCQ2VTXKPOP34PX&location.latitude=52.3702&location.longitude=4.8952");


        }

        /**
         * Populates the recycler view with the data from the get request.
         *
         * @param result Relates to the arraylist value of the weather data.
         */
        @Override
        protected void onPostExecute(ArrayList<Event> result) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.amsterdam_recycler);
            //creates a linear layout
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            //sets a linear layout
            recyclerView.setLayoutManager(linearLayoutManager);
            //Populates the weather adapter with the data from the get request.
            EventAdapter mAdapter = new EventAdapter(getApplicationContext(), result);
            recyclerView.setAdapter(mAdapter);
            //Checks to see if the adapter is no elements.
            if (mAdapter.getItemCount() == 0) {
                loadingText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

            } else {
                loadingText.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);

            }


        }


    }
}
