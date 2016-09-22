package net.transparentgov.android.trgv_2;

/**
 * Created by HU_J on 9/15/2016.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Area {


    public static String getMapBoxStyleUrl(String _area, String area_subject) {




        /*
        Map<String,String> mapbox_style_url = new HashMap<String, String>();
        mapbox_style_url.put("city_all","mapbox://styles/hoogw/ciqck140k0015g0nl5z1uq2v7");
        mapbox_style_url.put("city_address","mapbox://styles/hoogw/ciqcn42gx003ebzm9v86elejr");
        mapbox_style_url.put("city_parcels","mapbox://styles/hoogw/ciqcn7oww001ag0nlbi22gj8i");
        mapbox_style_url.put("city_streets","mapbox://styles/hoogw/ciqcnhx0m003acrnfdg2be0v7");
        return mapbox_style_url.get(area_subject);
        */



       /* mapbox_style_url.put("city_address","http://166.62.80.50:10/tilejson/city/city_address.json");
        mapbox_style_url.put("city_parcels","http://166.62.80.50:10/tilejson/city/city_parcels.json");*/

        String _style_url = "http://166.62.80.50:10/tilejson/" + _area + "/" + area_subject + ".json";



        return _style_url;
    }


    public static Map<String,List<Double>> getAreaInitLocation() {


        Map<String, List<Double>> area_hmap = new HashMap<String, List<Double>>();

        List<Double> city_list = new ArrayList<Double>();
        city_list.add(33.65992448007282);
        city_list.add(-117.91505813598633);
        city_list.add(13.0);
        area_hmap.put("city", city_list);



        List<Double> Santa_Monica_list = new ArrayList<Double>();
        Santa_Monica_list.add(34.023143);
        Santa_Monica_list.add(-118.475275);
        Santa_Monica_list.add(14.0);
        area_hmap.put("Santa_Monica", Santa_Monica_list);



        List<Double> Newport_Beach_list = new ArrayList<Double>();
        Newport_Beach_list.add(33.616478);
        Newport_Beach_list.add(-117.875748);
        Newport_Beach_list.add(13.0);
        area_hmap.put("Newport_Beach", Newport_Beach_list);



        List<Double> county_list = new ArrayList<Double>();
        county_list.add(33.693495);
        county_list.add(-117.793350);
        county_list.add(11.0);
        area_hmap.put("county", county_list);


        List<Double> Los_Angeles_list = new ArrayList<Double>();
        Los_Angeles_list.add(34.043556504127444);
        Los_Angeles_list.add(-118.24928283691406);
        Los_Angeles_list.add(11.0);
        area_hmap.put("Los_Angeles", Los_Angeles_list);


        List<Double> San_Francisco_list = new ArrayList<Double>();
        San_Francisco_list.add(37.77559951996456);
        San_Francisco_list.add(-122.41722106933594);
        San_Francisco_list.add(13.0);
        area_hmap.put("San_Francisco", San_Francisco_list);


        List<Double> New_York_list = new ArrayList<Double>();
        New_York_list.add(40.753499070431374);
        New_York_list.add(-73.98948669433594);
        New_York_list.add(11.0);
        area_hmap.put("New_York", New_York_list);


        List<Double> Chicago_list = new ArrayList<Double>();
        Chicago_list.add(41.874673839758024);
        Chicago_list.add(-87.63175964355469);
        Chicago_list.add(11.0);
        area_hmap.put("Chicago", Chicago_list);



        List<Double> Denver_list = new ArrayList<Double>();
        Denver_list.add(39.74336227378035);
        Denver_list.add(-104.99101638793945);
        Denver_list.add(12.0);
        area_hmap.put("Denver", Denver_list);


        List<Double> Los_Angeles_County_list = new ArrayList<Double>();
        Los_Angeles_County_list.add(34.05607276338366);
        Los_Angeles_County_list.add(-118.26370239257812);
        Los_Angeles_County_list.add(10.0);
        area_hmap.put("Los_Angeles_County", Los_Angeles_County_list);


        List<Double> New_York_Bronx_list = new ArrayList<Double>();
        New_York_Bronx_list.add(40.85537053192496);
        New_York_Bronx_list.add(-73.87687683105469);
        New_York_Bronx_list.add(13.0);
        area_hmap.put("New_York_Bronx", New_York_Bronx_list);


        List<Double> New_York_Brooklyn_list = new ArrayList<Double>();
        New_York_Brooklyn_list.add(40.65433643720422);
        New_York_Brooklyn_list.add(-73.95206451416016);
        New_York_Brooklyn_list.add(13.0);
        area_hmap.put("New_York_Brooklyn", New_York_Brooklyn_list);


        List<Double> New_York_Manhattan_list = new ArrayList<Double>();
        New_York_Manhattan_list.add(40.764421348741976);
        New_York_Manhattan_list.add(-73.97815704345703);
        New_York_Manhattan_list.add(13.0);
        area_hmap.put("New_York_Manhattan", New_York_Manhattan_list);


        List<Double> New_York_Queens_list = new ArrayList<Double>();
        New_York_Queens_list.add(40.72280306615735);
        New_York_Queens_list.add(-73.79997253417969);
        New_York_Queens_list.add(13.0);
        area_hmap.put("New_York_Queens", New_York_Queens_list);


        List<Double> New_York_Staten_Island_list = new ArrayList<Double>();
        New_York_Staten_Island_list.add(40.60300547512703);
        New_York_Staten_Island_list.add(-74.1353988647461);
        New_York_Staten_Island_list.add(13.0);
        area_hmap.put("New_York_Staten_Island", New_York_Staten_Island_list);


        List<Double> Arura_list = new ArrayList<Double>();
        Arura_list.add(39.723296392333026);
        Arura_list.add(-104.84081268310547);
        Arura_list.add(13.0);
        area_hmap.put("Arura", Arura_list);


        List<Double> Bakersfield_list = new ArrayList<Double>();
        Bakersfield_list.add(39.818557296839344);
        Bakersfield_list.add(-104.501953125);
        Bakersfield_list.add(13.0);
        area_hmap.put("Bakersfield", Bakersfield_list);


        List<Double> Baltimore_list = new ArrayList<Double>();
        Baltimore_list.add(35.44808511462123);
        Baltimore_list.add(-118.78177642822266);
        Baltimore_list.add(13.0);
        area_hmap.put("Baltimore", Baltimore_list);


        List<Double> Orlando_list = new ArrayList<Double>();
        Orlando_list.add(39.90657598772841);
        Orlando_list.add(-104.59259033203125);
        Orlando_list.add(13.0);
        area_hmap.put("Orlando", Orlando_list);


        List<Double> Palo_Alto_list = new ArrayList<Double>();
        Palo_Alto_list.add(37.4426999532675);
        Palo_Alto_list.add(-122.15492248535156);
        Palo_Alto_list.add(13.0);
        area_hmap.put("Palo_Alto", Palo_Alto_list);


        List<Double> Shoreline_list = new ArrayList<Double>();
        Shoreline_list.add(47.75479043701335);
        Shoreline_list.add(-122.34392166137695);
        Shoreline_list.add(13.0);
        area_hmap.put("Shoreline", Shoreline_list);









        return area_hmap;
    }


}
