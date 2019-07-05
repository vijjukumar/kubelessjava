package io.kubeless;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import io.kubeless.Event;
import io.kubeless.Context;

/**
 * Created by e088740 on 7/5/2019.
 */
public class CallRestService {

    public String foo(io.kubeless.Event event, io.kubeless.Context context) {
        StringBuffer response = new StringBuffer();
        try {

            URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return response.toString();
    }
}
