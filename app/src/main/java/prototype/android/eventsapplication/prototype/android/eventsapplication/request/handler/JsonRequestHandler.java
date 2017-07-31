package prototype.android.eventsapplication.prototype.android.eventsapplication.request.handler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import prototype.android.eventsapplication.protoype.android.eventsapplication.model.Event;

/**
 * created by Noodle on 25/07/2017.
 */

public class JsonRequestHandler {


    public String returnGetRequest(String siteUrl) {

        StringBuffer buffer = new StringBuffer();

        BufferedReader reader = null;
        URL url = null;
        try {
            //Goes to the dark api url
            url = new URL(siteUrl);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            int read;
            char[] chars = new char[1024];
            //Checks that next line exists
            while ((read = reader.read(chars)) != -1) {
                //if so carry on reading.
                buffer.append(chars, 0, read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return buffer.toString();


    }


    public ArrayList<Event> parseJson(String url) {
        ArrayList<Event> eventData = new ArrayList<Event>();

        try {

            JSONObject main = new JSONObject(returnGetRequest(url));

            JSONArray values = main.getJSONArray("events");


            for (int i = 0; i < values.length(); i++) {

                JSONObject row = values.getJSONObject(i);

                String name = row.getJSONObject("name").getString("text");


                String description = row.getJSONObject("description").getString("html");

                String startDate = row.getJSONObject("start").getString("utc");
                String convertedDate = convertDate(startDate);

                String endDate = row.getJSONObject("end").getString("utc");
                String convertedEndDate = convertDate(endDate);


                String imageUrl = row.getJSONObject("logo").getString("url");
                Bitmap newImage = convertImage(imageUrl);

                String isFree = row.getString("is_free");
                String ticketUrl = row.getString("url");


                Event event = new Event();


                event.setEvetName(name);
                event.setDate(convertedDate);
                event.setEventImage(newImage);
                event.setDescription(description);
                event.setIsPaid(isFree);
                event.setTicketUrl(ticketUrl);
                event.setEndDate(convertedEndDate);

                eventData.add(event);


            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }

        Log.i("arraylist size", String.valueOf(eventData.size()));

        return eventData;
    }


    public Bitmap convertImage(String imageUrl) {
        Bitmap bitmap = null;

        try {

            InputStream in = (InputStream) new URL(imageUrl).getContent(); //Reads whatever content found with the given URL Asynchronously And returns.
            bitmap = BitmapFactory.decodeStream(in); //Decodes the stream returned from getContent and converts It into a Bitmap Format
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    public String convertDate(String time) throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        final java.util.Date dateObj = sdf.parse(time);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));   // This line converts the given date into UTC time zone
        return new SimpleDateFormat("MMMM dd, HH:mm aa").format(dateObj);

    }


    public int isTicketPaid(String isPaid, Context context) {
        int icon;
        if (isPaid.contains("true")) {
            icon = context.getResources().getIdentifier("free", "drawable", context.getPackageName());

        } else {
            icon = context.getResources().getIdentifier("paid", "drawable", context.getPackageName());

        }


        return icon;
    }

}
