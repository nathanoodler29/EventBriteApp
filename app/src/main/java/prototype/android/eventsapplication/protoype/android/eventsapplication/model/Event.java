package prototype.android.eventsapplication.protoype.android.eventsapplication.model;

import android.graphics.Bitmap;

/**
 * created by Noodle on 25/07/2017.
 */

public class Event {
    private String evetName;
    private Bitmap eventImage;
    private String date;
    private String endDate;
    private String ticketUrl;
    private String isPaid;

    public Event() {

    }


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }


    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;


    public String getEvetName() {
        return evetName;
    }

    public void setEvetName(String evetName) {
        this.evetName = evetName;
    }

    public Bitmap getEventImage() {
        return eventImage;
    }

    public void setEventImage(Bitmap eventImage) {
        this.eventImage = eventImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
