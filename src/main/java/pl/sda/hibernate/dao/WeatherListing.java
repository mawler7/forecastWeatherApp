package pl.sda.hibernate.dao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherListing {

    private static final String apiKey = "2b1e5d818b4502df2a272d1989370f4e";
    private static  String cityName = WeatherListing.getSelectedCityName();

    public static void getCity() throws IOException, URISyntaxException {
        JSONObject obj = new JSONObject(getWeatherListing());
        JSONArray list = obj.getJSONArray("list");
        JSONObject jsonObject = list.getJSONObject(0);
        System.out.println(jsonObject.getString("name"));

    }

    public static void getTemperature() throws IOException, URISyntaxException {
        JSONObject obj = new JSONObject(getWeatherListing());
        JSONArray list = obj.getJSONArray("list");
        JSONObject jsonObject = list.getJSONObject(0).getJSONObject("main");
        System.out.println(jsonObject.getInt("temp") + " Fahrenheit");

    }

    public static void getPressure() throws IOException, URISyntaxException {
        JSONObject obj = new JSONObject(getWeatherListing());
        JSONArray list = obj.getJSONArray("list");
        JSONObject jsonObject = list.getJSONObject(0).getJSONObject("main");
        System.out.println(jsonObject.getDouble("pressure") + " hPa");

    }

    public static void getHumidity() throws IOException, URISyntaxException {
        JSONObject obj = new JSONObject(getWeatherListing());
        JSONArray list = obj.getJSONArray("list");
        JSONObject jsonObject = list.getJSONObject(0).getJSONObject("main");
        System.out.println(jsonObject.getDouble("humidity") + " % Rh");

    }

    public static void getWindSpeed() throws IOException, URISyntaxException {
        JSONObject obj = new JSONObject(getWeatherListing());
        JSONArray list = obj.getJSONArray("list");
        JSONObject jsonObject = list.getJSONObject(0).getJSONObject("wind");
        System.out.println(jsonObject.getDouble("speed" ) + " mph");

    }
    public static void getWindDirection() throws IOException, URISyntaxException {
        JSONObject obj = new JSONObject(getWeatherListing());
        JSONArray list = obj.getJSONArray("list");
        JSONObject jsonObject = list.getJSONObject(0).getJSONObject("wind");
        System.out.println(jsonObject.getDouble("deg" ) + " degrees");

    }

    //public static void getLatitude()throws IOException, URISyntaxException {
    //    JSONObject obj = new JSONObject(getWeatherListing());
     //   JSONArray list = obj.getJSONArray("list");
     //   JSONObject jsonObject = list.getJSONObject(0).getJSONObject("coord");
      //  System.out.println(jsonObject.getInt("lat" ) + " degrees" + " >> | LATITUDE");
  //  }
  //  public static void getLongitude()throws IOException, URISyntaxException {
 //       JSONObject obj = new JSONObject(getWeatherListing());
  ////      JSONArray list = obj.getJSONArray("list");
    //    JSONObject jsonObject = list.getJSONObject(0).getJSONObject("coord");
    //    System.out.println(jsonObject.getInt("lon" ) + " degrees" + " >> | LONGITUDE");

   // }



 public static String getSelectedCityName() {
     Scanner value = new Scanner(System.in);
     System.out.println("Select a City");
     return value.nextLine();

 }






    public static String getWeatherListing() throws IOException, URISyntaxException {
        String uri = "https://api.openweathermap.org/data/2.5/find";
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("q",cityName));
        parameters.add(new BasicNameValuePair("units","imperial"));
        parameters.add(new BasicNameValuePair("appid",apiKey));
        return makeAPICall(uri, parameters);
    }

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


}
