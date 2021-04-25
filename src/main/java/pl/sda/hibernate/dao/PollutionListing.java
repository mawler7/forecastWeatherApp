package pl.sda.hibernate.dao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PollutionListing {

    private static final String apiKey = "2b1e5d818b4502df2a272d1989370f4e";
  //  private static final String cityName =  PollutionListing.getSelectedCityName();
public   static  String lat;
public   static  String lon;

  //  static {
   //     try {
    //        WeatherListing.getLongitude();
    //    } catch (IOException | URISyntaxException e) {
    //        e.printStackTrace();
    //    }
   //     try {
   //         WeatherListing.getLatitude();
   //     } catch (IOException | URISyntaxException e) {
   //         e.printStackTrace();
   //     }
 //   }



public static void getCarbonMonoxide()throws IOException, URISyntaxException {
    JSONObject obj = new JSONObject(getPollutionListing());
    JSONArray list = obj.getJSONArray("list");
    JSONObject jsonObject = list.getJSONObject(0).getJSONObject("components");
    System.out.println(jsonObject.getDouble("co"));
}

    public static void getNitrogenMonoxide()throws IOException, URISyntaxException {
        JSONObject obj = new JSONObject(getPollutionListing());
        JSONArray list = obj.getJSONArray("list");
        JSONObject jsonObject = list.getJSONObject(0).getJSONObject("components");
        System.out.println(jsonObject.getDouble("no"));
    }

        public static void getNitrogenDioxide()throws IOException, URISyntaxException {
            JSONObject obj = new JSONObject(getPollutionListing());
            JSONArray list = obj.getJSONArray("list");
            JSONObject jsonObject = list.getJSONObject(0).getJSONObject("components");
            System.out.println(jsonObject.getDouble("no2"));
        }

            public static void getOzone()throws IOException, URISyntaxException {
                JSONObject obj = new JSONObject(getPollutionListing());
                JSONArray list = obj.getJSONArray("list");
                JSONObject jsonObject = list.getJSONObject(0).getJSONObject("components");
                System.out.println(jsonObject.getDouble("o3") );

            }
    public static void getSulphurDioxide()throws IOException, URISyntaxException {
        JSONObject obj = new JSONObject(getPollutionListing());
        JSONArray list = obj.getJSONArray("list");
        JSONObject jsonObject = list.getJSONObject(0).getJSONObject("components");
        System.out.println(jsonObject.getDouble("so2"));
    }

        public static void getAmmonia()throws IOException, URISyntaxException {
                JSONObject obj = new JSONObject(getPollutionListing());
                JSONArray list = obj.getJSONArray("list");
                JSONObject jsonObject = list.getJSONObject(0).getJSONObject("components");
                System.out.println(jsonObject.getDouble("nh3"));
            }

            public static void getFineParticulateMatter()throws IOException, URISyntaxException {
                JSONObject obj = new JSONObject(getPollutionListing());
                JSONArray list = obj.getJSONArray("list");
                JSONObject jsonObject = list.getJSONObject(0).getJSONObject("components");
                System.out.println(jsonObject.getDouble("pm2_5"));
            }
            public static void  getCoarseParticulateMatter()throws IOException, URISyntaxException {
                JSONObject obj = new JSONObject(getPollutionListing());
                JSONArray list = obj.getJSONArray("list");
                JSONObject jsonObject = list.getJSONObject(0).getJSONObject("components");
                System.out.println(jsonObject.getDouble("pm10"));
            }

                public static String getPollutionListing() throws IOException, URISyntaxException {
       String uri = "http://api.openweathermap.org/data/2.5/air_pollution/forecast";
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("lat", "34"));
        parameters.add(new BasicNameValuePair("lon","51"));
        parameters.add(new BasicNameValuePair("appid",apiKey));
        return makeAPICall(uri, parameters);
  }





        //  JSONObject obj = new JSONObject(getPollutionList());
     //     JSONArray list = obj.getJSONArray("list");
        //  JSONObject jsonObject = list.getJSONObject(1);
       //   System.out.println(jsonObject.getString("lat"));


    public static String makeAPICall (String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }

    public static void setLat(String lat) {
        PollutionListing.lat = lat;
    }

    public static void setLon(String lon) {
        PollutionListing.lon = lon;
    }
}

