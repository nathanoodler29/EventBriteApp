package prototype.android.eventsapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import prototype.android.eventsapplication.prototype.android.eventsapplication.request.handler.JsonRequestHandler;
import prototype.android.eventsapplication.protoype.android.eventsapplication.model.Event;

public class MoreInformation extends AppCompatActivity {



    private Event event = new Event();
    private JsonRequestHandler requestHandler = new JsonRequestHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);
        TextView eventsName = (TextView)findViewById(R.id.eventName);
        TextView eventsStartDate = (TextView)findViewById(R.id.eventsStartDate);
        TextView eventsEndDate = (TextView)findViewById(R.id.eventsEndDate);
        ImageView eventsImage = (ImageView)findViewById(R.id.event_image_banner);
        ImageView paidIcon = (ImageView)findViewById(R.id.paidicon);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("event_name");
        String date = bundle.getString("event_date");
        Bitmap image = (Bitmap) bundle.get("event_image");
        String free = bundle.getString("is_free");
        String endDate = bundle.getString("end_date");
        eventsName.setText(name);
        eventsStartDate.setText("Start Date: "+date);
        eventsEndDate.setText("End Date: "+endDate);
        eventsImage.setImageBitmap(image);
        Log.i("is free value",free);
        paidIcon.setImageResource(requestHandler.isTicketPaid(free,this));



        //isTicketPaid



        WebView webView = (WebView) findViewById(R.id.events_webview);
        webView.loadData(bundle.getString("description") , "text/html", null);



//        TextView textView = (TextView)findViewById(R.id.html_test);
//        textView.setMovementMethod(new ScrollingMovementMethod());
//
//

//        textView.setText(Html.fromHtml("<P CLASS=\\\"MsoNormal\\\">The best technology will be on display at the inaugural CES Unveiled Amsterdam.</P>\\n<P CLASS=\\\"MsoNormal\\\"><SPAN>Feel the future and see what's next along with more than 300 prominent European media and tech industry influencers.</SPAN></P>\\n<P CLASS=\\\"MsoNormal\\\"><SPAN>The half-day industry-only event will kick off with global tech trends and breaking CES news from CTA executives and industry experts. Tech companies from the Netherlands and across the region will showcase their ground-breaking technology during the tabletop exhibition and networking reception.</SPAN></P>\\n<P CLASS=\\\"MsoNormal\\\"><SPAN>From new-to-market startups to established brands, CES Unveiled Amsterdam offers companies the opportunity to stand out among the CES elite. Meet your business needs. Gain access to top influential media from <STRONG>The Netherlands</STRONG> and nearby countries, including <STRONG>Belgium</STRONG>, <STRONG>Denmark</STRONG>, <STRONG>Germany</STRONG>, <STRONG>Sweden</STRONG> and <STRONG>United Kingdom</STRONG>, at CES Unveiled Amsterdam, serving as a prelude to the innovation that will be displayed CES 2018, January 9-12, in Las Vegas, Nevada. </SPAN></P>\\n<H4 CLASS=\\\"MsoNormal\\\"><SPAN>*Event Schedule:</SPAN></H4>\\n<P CLASS=\\\"MsoNormal\\\"><SPAN>1-2 PM: Badge Pickup &amp; On-site Registration </SPAN></P>\\n<P CLASS=\\\"MsoNormal\\\"><SPAN>2-3:30 PM: CTA Conference Programming</SPAN></P>\\n<P CLASS=\\\"MsoNormal\\\"><SPAN>3:30-5:30 PM: Networking Reception &amp; Tabletop Exhibition</SPAN></P>\\n<P CLASS=\\\"MsoNormal\\\"><SPAN> </SPAN></P>\\n<P CLASS=\\\"MsoNormal\\\"><SPAN>PLEASE NOTE: CES Unveiled Amsterdam is open only to media and members of the consumer technology industry. Registrants must be at least 18 years old to attend the event. Use the green registration button at the top of this page and select the ticket that fits your affiliation.</SPAN></P>\\n<P CLASS=\\\"MsoNormal\\\"><SPAN>Visit </SPAN><SPAN><A HREF=\\\"http://www.ces.tech/UnveiledAmsterdam\\\" TARGET=\\\"_blank\\\" REL=\\\"noreferrer noopener nofollow noopener noreferrer nofollow\\\"><SPAN>www.CES.tech/UnveiledAmsterdam</SPAN></A></SPAN><SPAN> <SPAN CLASS=\\\"MsoHyperlink\\\">to learn more and see event details. </SPAN></SPAN></P>").toString());

    }

    public void goBackAPage(View view) {
        finish();

    }

    public void goToUrl(View view){
        Bundle bundle = getIntent().getExtras();

        String url = bundle.getString("url");

        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }



}
