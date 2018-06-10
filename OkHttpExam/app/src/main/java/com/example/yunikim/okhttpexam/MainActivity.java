package com.example.yunikim.okhttpexam;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static ListView mWeatherListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeatherListView = findViewById(R.id.list_view);

        new HttpAsyncTask().execute("https://goo.gl/eIXu9l");
    }

    private static class HttpAsyncTask extends AsyncTask<String, Void, List<Weather>> {
        OkHttpClient client = new OkHttpClient();

        @Override
        protected List<Weather> doInBackground(String... strings) {
            List<Weather> weatherList = new ArrayList<>();
            String strUrl = strings[0];

            try {
                Request request = new Request.Builder().url(strUrl).build();
                Response response = client.newCall(request).execute();

                Gson gson = new Gson();

                Type listType = new TypeToken<ArrayList<Weather>>(){}.getType();
                weatherList = gson.fromJson(response.body().string(), listType);

//                JSONArray jsonArray = new JSONArray(response.body().string());
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    String country = jsonObject.getString("country");
//                    String weather = jsonObject.getString("weather");
//                    String temperature = jsonObject.getString("temperature");
//                    Weather w = new Weather(country, weather, temperature);
//                    weatherList.add(w);
//                }
                Log.d(TAG, weatherList.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return weatherList;
        }

        @Override
        protected void onPostExecute(List<Weather> weathers) {
            super.onPostExecute(weathers);
            if(weathers != null) {
                Log.d(HttpAsyncTask.class.getSimpleName(), weathers.toString());
                WeatherAdapter adapter = new WeatherAdapter(weathers);

                mWeatherListView.setAdapter(adapter);
            }
        }
    }
}
