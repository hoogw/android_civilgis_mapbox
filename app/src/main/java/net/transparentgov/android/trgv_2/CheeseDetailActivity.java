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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;




/*
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;*/





import java.util.List;
import java.util.Map;
import java.util.Random;

public class CheeseDetailActivity extends AppCompatActivity {


   // MapView mMapView;
  //  private GoogleMap googleMap;

    private String area_subject;
    private String _area_;
    private String _subject_;

    private String _geometry_type;
    private String _key;
    private int _i;
    private double _lat;
    private double _lng;
    private int _zoom;


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






        AppBarLayout _appbar = (AppBarLayout) findViewById(R.id.appbar);
        _appbar.setExpanded(false);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(area_subject);




    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }
}
