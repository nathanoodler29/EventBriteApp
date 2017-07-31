package prototype.android.eventsapplication.prototype.android.eventsapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import prototype.android.eventsapplication.MoreInformation;
import prototype.android.eventsapplication.R;
import prototype.android.eventsapplication.prototype.android.eventsapplication.request.handler.JsonRequestHandler;
import prototype.android.eventsapplication.protoype.android.eventsapplication.model.Event;

/**
 * created by Noodle on 25/07/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private ArrayList<Event> eventArrayList = new ArrayList<Event>();
    private Context mContext;


    /**
     * This class handles the layout elements.
     */
    public static class ViewHolder extends
            RecyclerView.ViewHolder {

        private TextView eventName;
        private TextView eventDate;
        private ImageView eventImage;
        private ImageView calanderIcon;
        private JsonRequestHandler jsonRequestHandler = new JsonRequestHandler();
        private ImageView cardView;

        public ViewHolder(View v) {
            super(v);

        }


    }

    /**
     * Refers to the adapter itself.
     *
     * @param context   Related to the current activity it's been called in.
     * @param eventData Refers to the weather data, which is used to populate the adapter.
     */
    public EventAdapter(Context context, ArrayList<Event> eventData) {
        eventArrayList = eventData;
        mContext = context;

    }


    public void storeEventDetailsForMoreInformationActivtiy(final ViewHolder viewHolder) {

        viewHolder.eventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int holderPos = viewHolder.getAdapterPosition();
                Log.i("adapter pos", String.valueOf(holderPos));
                String eventsName = eventArrayList.get(holderPos).getEvetName();
                String description = eventArrayList.get(holderPos).getDescription();

                String eventStartDate = eventArrayList.get(holderPos).getDate();
                String eventEndDate = eventArrayList.get(holderPos).getEndDate();

                String free = eventArrayList.get(holderPos).getIsPaid();

                Bitmap image = eventArrayList.get(holderPos).getEventImage();

                String ticketUrl = eventArrayList.get(holderPos).getTicketUrl();
                Intent intent = new Intent(mContext, MoreInformation.class);
                intent.putExtra("event_name", eventsName);
                intent.putExtra("event_date", eventStartDate);
                intent.putExtra("event_image", image);
                intent.putExtra("description", description);
                intent.putExtra("is_free", free);
                intent.putExtra("url", ticketUrl);
                intent.putExtra("end_date", eventEndDate);

                mContext.startActivity(intent);
                Log.i("EVENT NAME", eventArrayList.get(holderPos).getEvetName());


            }
        });
    }

    /**
     * Initialises the elements in the layout, from the 'generic_weather_item.xml' layout file
     *
     * @param parent   Refers to the current layout.
     * @param viewType Relates to the type of view in the application.
     * @return The view holder object with the layout.
     */
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).inflate(

                R.layout.generic_event_item, parent, false);

        final EventAdapter.ViewHolder viewHolder = new EventAdapter.ViewHolder(v);
        viewHolder.eventName = (TextView) v.findViewById(R.id.event_name);
        viewHolder.eventDate = (TextView) v.findViewById(R.id.event_date);
        viewHolder.eventImage = (ImageView) v.findViewById(R.id.event_image);
        viewHolder.calanderIcon = (ImageView) v.findViewById(R.id.calandericon);
        viewHolder.calanderIcon.setAlpha(0.5f);

        storeEventDetailsForMoreInformationActivtiy(viewHolder);

        return viewHolder;
    }

    /**
     * This method adds the data to the view holder created.
     * The method also changes the weather icons and colour of card views depending on the icon type from the get request.
     *
     * @param holder   Holder relates to the object, that has the layout.
     * @param position Relates to the number of items in the recycler view, which are indexed in the arraylist.
     */
    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {

        holder.eventName.setText(eventArrayList.get(position).getEvetName());
        holder.eventDate.setText(eventArrayList.get(position).getDate());
        holder.eventImage.setImageBitmap(eventArrayList.get(position).getEventImage());


    }

    /**
     * States how many items are in the adapter.
     *
     * @return The number of items in the adapter as an integer.
     */
    @Override
    public int getItemCount() {

        return eventArrayList.size();
    }


}
