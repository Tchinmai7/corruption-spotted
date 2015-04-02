

package com.example.aarthi.anti_corruption;

/**
 * Created by aarthi on 12/3/15.
 */

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.location.Address;
        import android.location.Geocoder;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v4.app.FragmentActivity;
        import android.support.v7.widget.CardView;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RelativeLayout;
        import android.widget.SeekBar;
        import android.widget.SeekBar.OnSeekBarChangeListener;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.maps.CameraUpdate;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.BitmapDescriptor;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.GroundOverlay;
        import com.google.android.gms.maps.model.GroundOverlayOptions;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.LatLngBounds;
        import com.google.android.gms.maps.model.MarkerOptions;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
public class repmap extends FragmentActivity
        implements OnMapReadyCallback {
    private final List<BitmapDescriptor> mImages = new ArrayList<BitmapDescriptor>();
    private GroundOverlay mGroundOverlay;
    private int mCurrentEntry = 0;
    Button b1,next;
    GoogleMap googleMap;
    MarkerOptions markerOptions;
    LatLng latLng;
    public EditText etLocation;
    public SharedPreferences sp;
    public SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ground_overlay_demo);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        googleMap = mapFragment.getMap();
        b1 = (Button) findViewById(R.id.btn_find);
        next = (Button) findViewById(R.id.button4);
        etLocation = (EditText) findViewById(R.id.et_location);
        sp=getSharedPreferences("myapp",0);
        ed=sp.edit();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String location = etLocation.getText().toString();

                if (location != null && !location.equals("")) {
                    new GeocoderTask().execute(location);

                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Getting user input location
                String location = etLocation.getText().toString();

                ed.putString("location",location);
                ed.apply();
                Intent i=new Intent(etLocation.getContext(),CameraPhotoCapture.class);
                startActivity(i);
                finish();

            }

        });
    }

    @Override
    public void onMapReady(GoogleMap map) {

        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener mlocListener = new MyLocationListener();

        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(12.987501, 79.971542), 13));
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mImages.clear();
        // mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.map));
        map.setMyLocationEnabled(true);
        mCurrentEntry = 0;
        //mGroundOverlay = map.addGroundOverlay(new GroundOverlayOptions()
        //      .image(BitmapDescriptorFactory.fromResource(R.drawable.map))
        //    .positionFromBounds(newarkBounds));
        map.getUiSettings().setZoomControlsEnabled(true);

        // Override the default content description on the view, for accessibility mode.
        // Ideally this string would be localised.
        getter g1=new getter();
        g1.execute();


    }
    public double latitude,longitude;
    public class MyLocationListener implements LocationListener

    {
        @Override
/*
location update and moving the marker dynamically
 */    public void onLocationChanged(Location loc)

        {
            latitude= loc.getLatitude();
            longitude= loc.getLongitude();
        /*String Text = " My current location is: " + " Latitude =" + latitude +
                    "Longitude = " + longitude;*/

            LatLng LOCATION_USER = new LatLng(latitude,longitude);
            // LatLng source= new LatLng(12.9896234,80.2292689);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_USER, 16);
            googleMap.animateCamera(update);
/*
Below commented code is to test location connectivity
 */

            //Toast.makeText(getApplicationContext(),

            //     Text,

//                   Toast.LENGTH_SHORT).show();

        }

        @Override
/*
Used to check the gps status on the device.
 */
        public void onProviderDisabled(String provider)

        {

            Toast.makeText(getApplicationContext(),

                    "Gps Disabled",

                    Toast.LENGTH_SHORT).show();

        }

        @Override

        public void onProviderEnabled(String provider)

        {

            Toast.makeText(getApplicationContext(),

                    "Gps Enabled,",
                    Toast.LENGTH_SHORT).show();

        }





        @Override

        public void onStatusChanged(String provider, int status, Bundle extras)

        {

        }


    }

    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if (addresses == null || addresses.size() == 0) {
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
            }

            // Clears all the existing markers on the map
            // googleMap.clear();

            // Adding Markers on Google Map for each matching address
            // for (int i = 0; i < addresses.size(); i++) {

            Address address = (Address) addresses.get(0);

            // Creating an instance of GeoPoint, to display in Google Map
            latLng = new LatLng(address.getLatitude(), address.getLongitude());

            String addressText = String.format("%s, %s",
                    address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                    address.getCountryName());

            markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(addressText);

            googleMap.addMarker(markerOptions);

            // Locate the first location
            //  if (i == 0)
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            //}
        }
    }
    public ArrayList<HashMap<String, String>> infolist;

    protected class getter extends AsyncTask<Void,Void,Void>
    {
        public HashMap<String, String> info;
        public String s;
        public int count;
        ProgressDialog mProgress;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            mProgress = new ProgressDialog(repmap.this);
            mProgress.setMessage("Please wait...");
            mProgress.setCancelable(false);
            mProgress.show();
        }
        protected Void doInBackground(Void... params) {

            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            String url="http://smallbang.hol.es/corruption/mapdata.php";

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            infolist=new ArrayList<>(10);
            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray event = jsonObj.getJSONArray("results");

                    count=event.length();
                    for (int i = 0; i < event.length(); i++) {
                        info=new HashMap<>(10);

                        JSONObject c = event.getJSONObject(i);

                        //  String img = c.getString("Image");
                        String loc = c.getString("Location");
                        String cnt = c.getString("Count");
                        //String to = c.getString(TagTo);
                        //String venue = c.getString(TagVenue);
                        Log.d("data",loc+cnt);
                        // info.put("Image", img);
                        info.put("Location", loc);
                        info.put("Count", cnt);
                        //info.put(TagTo, to);
                        //info.put(TagVenue, venue);
                        infolist.add(info);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (mProgress.isShowing())
                mProgress.dismiss();
            String s1,s2,s3;
            //  s1 = infolist.get(j).get("Image");
            for(int i=0;i<count-1;i++){
                s2 = infolist.get(i).get("Location");
                reputask rt=new reputask();
                rt.execute(s2);
             }
        }
    }
    public static int counter=0;
    private class reputask extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if (addresses == null || addresses.size() == 0) {
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
            }
        else
            {
            // Clears all the existing markers on the map
            // googleMap.clear();

            // Adding Markers on Google Map for each matching address
            // for (int i = 0; i < addresses.size(); i++) {

            Address address = (Address) addresses.get(0);

            // Creating an instance of GeoPoint, to display in Google Map
            latLng = new LatLng(address.getLatitude(), address.getLongitude());

            String addressText = String.format("%s, %s",
                    address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                    address.getCountryName());

            markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
                int st=counter;

            String mk=infolist.get(st).get("Count");
            markerOptions.title(mk);
                Log.d("address checker",infolist.get(counter).get("Count"));
                counter++;

                googleMap.addMarker(markerOptions);

            // Locate the first location
            //  if (i == 0)
            //}
        }
    }}



}
