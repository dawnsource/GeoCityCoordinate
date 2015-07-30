/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package com.brett.http.geo.baidu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * This example demonstrates the use of the {@link ResponseHandler} to simplify
 * the process of processing the HTTP response and releasing associated resources.
 */
public class GeoRequestHttpClient {


  public final static void main(String[] args) throws Exception {



    geoAcquire("北京市");


  }

  public static JsonNode geoAcquire(String city) throws Exception {
    CloseableHttpClient httpclient = HttpClients.createDefault();
//    DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
//    httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);
    try {

      String address = URLEncoder.encode(city, "utf-8");

      String url = "http://api.map.baidu.com/geocoder/v2/?address={address}&output=json&ak=E4805d16520de693a3fe707cdc962045";

      url = url.replaceFirst("\\{address\\}", address);


      HttpGet httpget = new HttpGet(url);
      httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
      httpget.setHeader("Accept-Encoding", "gzip, deflate, sdch");
      httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
      httpget.setHeader("Cache-Control", "no-cache");
      httpget.setHeader("Connection", "keep-alive");
      httpget.setHeader("Referer", "http://developer.baidu.com/map/index.php?title=webapi/guide/webservice-geocoding");
      httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.93 Safari/537.36");

      System.out.println("Executing request （httpget.getRequestLine()）： " + httpget.getRequestLine());


      // Create a custom response handler
      ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

        public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
          int status = response.getStatusLine().getStatusCode();
          if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity, "utf-8") : null;
          } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
          }
        }

      };
      String responseBody = httpclient.execute(httpget, responseHandler);
      System.out.println("----------------------------------------");
      System.out.println(HtmlDecoder.decode(responseBody));
      System.out.println(responseBody);

      System.out.println("----------------------------------------");


      ObjectMapper mapper = new ObjectMapper();
      mapper.readValue(responseBody, AddressCoord.class);
//      JsonNode root = mapper.readTree(responseBody.substring("showLocation&&showLocation(".length(), responseBody.length()-1));

      // {"status":0,"result":{"location":{"lng":116.30783584945,"lat":40.056876296398},"precise":1,"confidence":80,"level":"\u5546\u52a1\u5927\u53a6"}}
      JsonNode root = mapper.readTree(responseBody);

      System.out.println("result : " + city + " = " + root.get("result"));

      return root;

    } finally {
      httpclient.close();
    }
  }

}
