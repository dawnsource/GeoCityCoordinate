package com.brett.http.geo.baidu;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by brett on 2015/1/25.
 */
public class GeoCoordinate {

  public static void main(String[] args) throws Exception{

    allProvinces();

    allCity();

//    allCityGeo();

  }
  public static void allCityGeo() throws Exception{


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
    reader.close();

  }

  public static void allProvinces() throws IOException {

    String SQL = "insert into t_china_province(name) values('{placeholder}');";

    // ja['0100']='北京';
    Pattern pattern = Pattern.compile("ja\\['\\d{2}00']='(.{2,9})';");

    BufferedReader reader = new BufferedReader(new InputStreamReader(GeoCoordinate.class.getClassLoader().getResourceAsStream("city_arr.js")));
    String str =reader.readLine();
    Matcher matcher;

    int idx = 1;
    while( str != null ){

      matcher = pattern.matcher(str);
      if(matcher.find()) {

        String provinceName = matcher.group(1);

//        JsonNode root = GeoRequestHttpClient.geoAcquire(city);

        System.out.println("-- " + idx++ + ": " + provinceName);
        System.out.println(SQL.replaceFirst("\\{placeholder}", provinceName));

      }


      str =reader.readLine();

    }
    reader.close();
  }

  public static void allCity() throws IOException {

    String SQL = "insert into t_china_city(province, city) values('{province}', '{city}');";

    // ja['0100']='北京';
    Pattern patternProvince = Pattern.compile("ja\\['\\d{2}00']='(.{2,9})';");
    Pattern patternCity = Pattern.compile("ja\\['\\d{2}[1-9]{2}']='(.{2,9})';");

    BufferedReader reader = new BufferedReader(new InputStreamReader(GeoCoordinate.class.getClassLoader().getResourceAsStream("city_arr.js")));
    String str =reader.readLine();
    Matcher matcherProvince;
    Matcher matcherCity;
    String provinceName = null;

    int idx = 1;
    while( str != null ){

      matcherProvince = patternProvince.matcher(str);

      if(matcherProvince.find()) {

        provinceName = matcherProvince.group(1);

//        JsonNode root = GeoRequestHttpClient.geoAcquire(city);

        System.out.println("-- " + idx++ + ": " + provinceName);

        str =reader.readLine();
        continue;
      }

      matcherCity = patternCity.matcher(str);
      if(provinceName!=null && !"".equals(provinceName) && matcherCity.find()) {

        String cityName = matcherCity.group(1);

//        JsonNode root = GeoRequestHttpClient.geoAcquire(city);

        System.out.println("-- " + provinceName + " - " + cityName);
        System.out.println(SQL.replaceFirst("\\{province}", provinceName).replaceFirst("\\{city}", cityName));

      }


      str =reader.readLine();

    }

    reader.close();

  }


}
