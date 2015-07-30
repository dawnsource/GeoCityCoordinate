package com.brett.http.geo.baidu;

/**
 * showLocation&&showLocation({"status":0,"result":
 * {"location":{"lng":112.25009284837,"lat":32.229168591538},"precise":0,"confidence":14,"level":"\u533a\u53bf"}})
 *
 *
 * //不带回调函数的返回值
   {
     status: 0,
     result:
     {
       location:
       {
         lng: 116.30814954222,
         lat: 40.056885091681
       },
       precise: 1,
       confidence: 80,
       level: "商务大厦"
     }
   }


 status

 Int

 返回结果状态值， 成功返回0，其他值请查看下方返回码状态表。

 location

 object

 经纬度坐标

 lat

 float

 纬度值

 lng

 float

 经度值

 precise

 Int

 位置的附加信息，是否精确查找。1为精确查找，0为不精确。

 confidence

 Int

 可信度

 level

 string

 地址类型


 * Created by brett on 2015/1/28.
 *
 *
 * 请见 百度官网 API ： http://developer.baidu.com/map/index.php?title=webapi/guide/webservice-geocoding
 */
public class AddressCoord {

  private int status;
  private Result result;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public static class Result {
    private int precise;
    private int confidence;
    private String level;
    private Location location;

    public Result() {
    }

    public int getPrecise() {
      return precise;
    }

    public void setPrecise(int precise) {
      this.precise = precise;
    }

    public int getConfidence() {
      return confidence;
    }

    public void setConfidence(int confidence) {
      this.confidence = confidence;
    }

    public String getLevel() {
      return level;
    }

    public void setLevel(String level) {
      this.level = level;
    }

    public Location getLocation() {
      return location;
    }

    public void setLocation(Location location) {
      this.location = location;
    }

    public  class Location {
      private String lng;
      private String lat;

      public String getLng() {
        return lng;
      }

      public void setLng(String lng) {
        this.lng = lng;
      }

      public String getLat() {
        return lat;
      }

      public void setLat(String lat) {
        this.lat = lat;
      }
    }

  }


}
