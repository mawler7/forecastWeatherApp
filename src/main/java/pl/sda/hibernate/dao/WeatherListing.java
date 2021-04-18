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
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherListing {

    private static String apiKey = "2b1e5d818b4502df2a272d1989370f4e";

    public static void getWeather() throws IOException, URISyntaxException {
        JSONObject obj = new JSONObject(getWeatherListing());
        JSONArray list = obj.getJSONArray("list");
        JSONObject jsonObject = list.getJSONObject(0);
        System.out.println(jsonObject.getString("name"));

    }

    public static String getWeatherListing() throws IOException, URISyntaxException {
        String uri = "https://api.openweathermap.org/data/2.5/find";
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("lat","55.5"));
        parameters.add(new BasicNameValuePair("lon","37.5"));
        parameters.add(new BasicNameValuePair("cnt","10"));
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
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }

}
