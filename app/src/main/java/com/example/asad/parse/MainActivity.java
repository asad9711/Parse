package com.example.asad.parse;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
//    TextView tv;
    ListView lv;
    int count=0;StringBuffer sb2=null;
    public String TAG = "asd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("asd", "calling call");
//    String jsonText=getData("http://api.androidhive.info/json/movies.json");
        new GetJson(this).execute("http://api.androidhive.info/json/movies.json");
    }
    public static String getData(String uri) {
        BufferedReader reader = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("asd","url problem");
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
    public class Holder
    {
        String[] title=new String[50];
        String genre;
        URL[] imageurl=new URL[50];
//        Bitmap image;
//        ImageView imageView;
    }
    public class GetJson extends AsyncTask<String, Holder, Void>
    {
        Context mcontext;
        private GetJson(Context context)
        {
            mcontext=context;
        }
        @Override
        protected Void doInBackground(String... params) {
            Holder holder = new Holder();
            String jsonText=getData("http://api.androidhive.info/json/movies.json");
            try {
                Log.d("asd","inside call");
                Log.d("asd","returned:" + jsonText);
                  JSONArray ja = new JSONArray(jsonText);
//                StringBuffer sb2=new StringBuffer();
                for (int i = 0; i < ja.length(); i++) {
//                for (int i = 0; i < 5; i++) {
                    JSONObject jo = (JSONObject) ja.get(i);
                    Log.d("asd",jo.getString("title"));
                    holder.title[i] = jo.getString("title");
                    holder.genre = jo.getString("genre");
                    URL geturl = new URL(jo.getString("image"));
//                    holder.image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                     holder.imageView=(ImageView) findViewById(R.id.imageView);
//                    holder.imageView=null;
                    holder.imageurl[i]=geturl;
                    publishProgress(holder);
                }
//                tv=(TextView)findViewById(R.id.textView);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("asd", "I am in catch");
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Holder... values) {
            super.onProgressUpdate(values);
            Holder holder = new Holder();
            holder = values[0];
            lv = (ListView)findViewById(R.id.listView);
            lv.setAdapter(new CstmAdapter(mcontext,holder));
        }
        public void getSystemService(String layoutInflaterService) {
        }
    }
}
