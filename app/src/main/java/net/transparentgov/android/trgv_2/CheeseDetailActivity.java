/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.transparentgov.android.trgv_2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonElement;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.annotations.Polyline;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.MyBearingTracking;
import com.mapbox.mapboxsdk.constants.MyLocationTracking;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationListener;
import com.mapbox.mapboxsdk.location.LocationServices;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;


//import com.mapbox.mapboxandroiddemo.R;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.Polygon;
import com.mapbox.services.commons.geojson.MultiLineString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CheeseDetailActivity extends AppCompatActivity {


    private MapView mapView;
    private MapboxMap map;
    private LocationServices locationServices;
    private FloatingActionButton floatingActionButton;

    private Marker featureMarker;
    private com.mapbox.mapboxsdk.annotations.Polygon selectedPolygon;
    private com.mapbox.mapboxsdk.annotations.Polyline selectedPolyline;



    private String area_subject;
    private String _area_;
    private String _subject_;

    private String _geometry_type;
    private String _key;
    private int _i;
    private double _lat;
    private double _lng;
    private int _zoom;

    CameraPosition position;


    private static final int PERMISSIONS_LOCATION = 0;


    public static final String EXTRA_NAME = "cheese_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

        area_subject = cheeseName;
        Map<String, List<Double>> area_hmap_ = Area.getAreaInitLocation();

        for (Map.Entry<String, List<Double>> entry : area_hmap_.entrySet()) {
            // System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            List<Double> _value = entry.getValue();

            _key = entry.getKey();



            if (area_subject.contains(_key) ){

                _area_ = _key;

                _subject_ = area_subject.substring(_area_.length()+1);

                _lat = _value.get(0);
                _lng = _value.get(1);
                _zoom = _value.get(2).intValue();
            }



        }// for



        //+++++++++++++++++++   mapbox map init ++++++++++++++++++++++

        // Mapbox access token is configured here.
        MapboxAccountManager.start(this, getString(R.string.access_token));

        locationServices = LocationServices.getLocationServices(CheeseDetailActivity.this);


         position = new CameraPosition.Builder()
                .target(new LatLng(_lat, _lng)) // Sets the new camera position
                .zoom(_zoom) // Sets the zoom
               // .bearing(180) // Rotate the camera
              //  .tilt(30) // Set the camera tilt
                .build(); // Creates a CameraPosition from the builder






        mapView = (MapView) findViewById(R.id.mapView);
       // mapView = new MapView(this, options);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {

                // Customize map with markers, polylines, etc.
                map = mapboxMap;

                map.setStyleUrl(Area.getMapBoxStyleUrl(area_subject));

                map.animateCamera(CameraUpdateFactory
                        .newCameraPosition(position), 7000);



                // *****   query rendered feature on click **************


                mapboxMap.setOnMapClickListener(new MapboxMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng point) {

                        if (selectedPolygon != null) {
                            mapboxMap.removePolygon(selectedPolygon);
                        }


                        if (featureMarker != null) {
                            mapboxMap.removeMarker(featureMarker);
                        }

                        final PointF pixel = mapboxMap.getProjection().toScreenLocation(point);
                        List<Feature> features = mapboxMap.queryRenderedFeatures(pixel);

                        if (features.size() > 0) {
                            Feature feature = features.get(0);
                            String featureId = features.get(0).getId();



                            // ========= highlight selected feature =================





                            for (int a = 0; a < features.size(); a++) {
                                if (featureId.equals(features.get(a).getId())) {


                                    //++++++++++ polygon ++++++++++++
                                    if (features.get(a).getGeometry() instanceof Polygon) {

                                        List<LatLng> list = new ArrayList<>();
                                        for (int i = 0; i < ((Polygon) features.get(a).getGeometry()).getCoordinates().size(); i++) {
                                            for (int j = 0;
                                                 j < ((Polygon) features.get(a).getGeometry()).getCoordinates().get(i).size(); j++) {
                                                list.add(new LatLng(
                                                        ((Polygon) features.get(a).getGeometry()).getCoordinates().get(i).get(j).getLatitude(),
                                                        ((Polygon) features.get(a).getGeometry()).getCoordinates().get(i).get(j).getLongitude()
                                                ));
                                            }
                                        }

                                        selectedPolygon = mapboxMap.addPolygon(new PolygonOptions()
                                                .addAll(list)
                                                .fillColor(Color.parseColor("#8A8ACB"))
                                        );
                                    }// if polygon
                                    // +++++++++ End polygon +++++++++++++++++



                                    //++++++++++ polyline ++++++++++++
                                    if (features.get(a).getGeometry() instanceof MultiLineString) {

                                        List<LatLng> list = new ArrayList<>();
                                        for (int i = 0; i < ((MultiLineString) features.get(a).getGeometry()).getCoordinates().size(); i++) {
                                            for (int j = 0;
                                                 j < ((Polygon) features.get(a).getGeometry()).getCoordinates().get(i).size(); j++) {
                                                list.add(new LatLng(
                                                        ((MultiLineString) features.get(a).getGeometry()).getCoordinates().get(i).get(j).getLatitude(),
                                                        ((MultiLineString) features.get(a).getGeometry()).getCoordinates().get(i).get(j).getLongitude()
                                                ));
                                            }
                                        }

                                        selectedPolyline = mapboxMap.addPolyline(new PolylineOptions()
                                                .addAll(list)
                                                .color(Color.parseColor("#8A8ACB"))
                                        );
                                    }// if polygon
                                    // +++++++++ End polyline +++++++++++++++++



                                }
                            }









                            //========== end highlight selected feature ===================





                            //--------- get properties--------------
                            String property;

                            StringBuilder stringBuilder = new StringBuilder();
                            if (feature.getProperties() != null) {
                                for (Map.Entry<String, JsonElement> entry : feature.getProperties().entrySet()) {
                                    stringBuilder.append(String.format("%s - %s", entry.getKey(), entry.getValue()));
                                    stringBuilder.append(System.getProperty("line.separator"));
                                }

                                featureMarker = mapboxMap.addMarker(new MarkerViewOptions()
                                        .position(point)
                                        .title("Properties:")
                                        .snippet(stringBuilder.toString())
                                );

                            } else {
                                property = "No feature properties found";
                                featureMarker = mapboxMap.addMarker(new MarkerViewOptions()
                                        .position(point)
                                        .snippet(property)
                                );
                            }
                        } else {
                            featureMarker = mapboxMap.addMarker(new MarkerViewOptions()
                                    .position(point)
                                    .snippet("No feature properties found")
                            );
                        }//else if features.size > 0

                        mapboxMap.selectMarker(featureMarker);

                        //------------ end get properties ---------------

                    }// on map click
                });// set on map click listener

                // ************ end  query rendered feature on click **************

            }// on map ready
        }); // get map async



     //***************    my location button ********************
        floatingActionButton = (FloatingActionButton) findViewById(R.id.location_toggle_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    toggleGps(!map.isMyLocationEnabled());
                }
            }
        });//***************  End  ******  my location button ********************


        //+++++++++++++++++++ End ++++++++++    mapbox map init ++++++++++++++++++++++





        AppBarLayout _appbar = (AppBarLayout) findViewById(R.id.appbar);
        _appbar.setExpanded(false);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(area_subject);




    }


    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }




//========= my location =================

    private void toggleGps(boolean enableGps) {
        if (enableGps) {
            // Check if user has granted location permission
            if (!locationServices.areLocationPermissionsGranted()) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_LOCATION);
            } else {
                enableLocation(true);
            }
        } else {
            enableLocation(false);
        }
    }

    private void enableLocation(boolean enabled) {
        if (enabled) {
            // If we have the last location of the user, we can move the camera to that position.
            Location lastLocation = locationServices.getLastLocation();
            if (lastLocation != null) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastLocation), 16));
            }

            locationServices.addLocationListener(new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        // Move the map camera to where the user location is and then remove the
                        // listener so the camera isn't constantly updating when the user location
                        // changes. When the user disables and then enables the location again, this
                        // listener is registered again and will adjust the camera once again.
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location), 16));
                        locationServices.removeLocationListener(this);
                    }
                }
            });
            floatingActionButton.setImageResource(R.drawable.ic_location_disabled_24dp);
        } else {
            floatingActionButton.setImageResource(R.drawable.ic_my_location_24dp);
        }
        // Enable or disable the location layer on the map
        map.setMyLocationEnabled(enabled);
    }





    private void enableLocationTracking() {

        // Disable tracking dismiss on map gesture
        map.getTrackingSettings().setDismissAllTrackingOnGesture(false);

        // Enable location and bearing tracking
        map.getTrackingSettings().setMyLocationTrackingMode(MyLocationTracking.TRACKING_FOLLOW);
        map.getTrackingSettings().setMyBearingTrackingMode(MyBearingTracking.COMPASS);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableLocationTracking();
            }
        }
    }

    //===================End =========== my location =================



}
