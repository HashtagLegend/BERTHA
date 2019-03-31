package com.example.bertha.Activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bertha.Model.CombinedSendData;
import com.example.bertha.R;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

public class DataActivity extends AppCompatActivity implements OnMapReadyCallback {

    //Toolbar
    private Toolbar toolbar;


    //Mapbox
    private MapView mapView;

    public static final String DATA = "DATA";

    private double getlatitude = 55.639947;
    private double getlongitude = 12.085949;


    private CombinedSendData data;
    private TextView header, latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        data = (CombinedSendData) intent.getSerializableExtra(DATA);
        getlatitude = data.getLatitude();
        getlongitude = data.getLongitude();

        //Toolbar
        toolbar = findViewById(R.id.dataActivityToolbar);
        setSupportActionBar(toolbar);

        //Mapbox
        Mapbox.getInstance(this, "pk.eyJ1IjoicGF0cmlja29lcnVtIiwiYSI6ImNqdG4zZjQzajBsbGE0Y2xpcDdra25manQifQ.FgU1Q0paq3t2UdKHz6Ka9Q");

        setContentView(R.layout.activity_data);
        //Map options

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                CameraPosition position = new CameraPosition.Builder()
                        .target(new LatLng(getlatitude, getlongitude))
                        .zoom(10)
                        .tilt(20)
                        .build();

                MarkerOptions options = new MarkerOptions();
                options.title("Måling");
                options.position(new LatLng(getlatitude, getlongitude));
                mapboxMap.addMarker(options);
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 5000);
                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments.
                        /*
                        style.addImage("marker-icon-id",
                                BitmapFactory.decodeResource(
                                        DataActivity.this.getResources(),
                                        R.drawable.mapbox_marker_icon_default
                                ));
                        GeoJsonSource geoJsonSource = new GeoJsonSource("source-id", Feature.fromGeometry(
                                Point.fromLngLat(getlongitude, getlatitude)));
                        style.addSource(geoJsonSource);

                        SymbolLayer symbolLayer = new SymbolLayer("layer-id", "source-id");
                        symbolLayer.withProperties(
                                PropertyFactory.iconImage("marker-icon-id")
                        );
                        style.addLayer(symbolLayer);
                        */
                    }
                });
            }
        });



        header = findViewById(R.id.DataHeaderTV);
        header.setText("DTG(Dato Tids Gruppe): " + data.getUtc());

        latitude = findViewById(R.id.dataLatitude);
        latitude.setText("Latitude: " + data.getLatitude());


        longitude = findViewById(R.id.dataLongitude);
        longitude.setText("Longitude: " + data.getLongitude());


    }

    public void goBack(View view) {finish();}

    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {

    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.actionBarUserSettings) {
            Intent intent = new Intent(this, UserSettings.class);
            startActivity(intent);
        }
        else if(id == R.id.actionBarLogOut){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.actionBarSettings){

        }

        return super.onOptionsItemSelected(item);
    }
}
