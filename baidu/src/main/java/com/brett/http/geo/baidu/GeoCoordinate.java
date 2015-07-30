package com.brett.http.geo.baidu;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by brett on 2015/1/25.
 */
public class GeoCoordinate {

  public static void main(String[] args) throws Exception{


    // ja['0100']='北京';
    Pattern pattern = Pattern.compile("='(.{2,9})';");

    BufferedReader reader = new BufferedReader(new InputStreamReader(GeoCoordinate.class.getClassLoader().getResourceAsStream("city_arr.js")));
    String str =reader.readLine();
    Matcher matcher;

    while( str != null ){

      matcher = pattern.matcher(str);
      if(matcher.find()) {

        String city = matcher.group(1);

        JsonNode root = GeoRequestHttpClient.geoAcquire(city);

        System.out.println(city);
      }


      str =reader.readLine();

    }






  }


}
