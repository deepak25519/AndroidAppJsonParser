package com.example.i306851.androidappjsonparser;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    JSONParser jsonParser = new JSONParser();
    private static final String TAG_NAME = "Name";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private ProgressDialog pDialog;
    String message = "";
    int success;

    Button btn ;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);
        send = (Button) findViewById(R.id.send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadAllCategoryQues().execute();
            }
        });
        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                new SendAllData().execute();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class LoadAllCategoryQues extends AsyncTask<String, String, String> {

//		int lastViewedPosition;
//		View v;
//		int topOffset;

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading data... Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters

            //int counter = -1;

            try {

                //int count = 0;
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                //params.add(new BasicNameValuePair("data", "Deepak"));
                // Log.d("request!", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        "http://jpfruitshop.net76.net/book1.php", "POST", params);

                Log.d("", "Sourav");
                // json success tag
                success = json.getInt(TAG_SUCCESS);

                if(success == 1) {
                    JSONArray jsonArray = json.getJSONArray("item");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject names = jsonArray.getJSONObject(i);
                        message += " " + names.getString(TAG_NAME);
                    }
                }
            }
            catch(Exception e) {
                e.printStackTrace();
                //Toast.makeText(Subjects.this, e.toString(), 60).show();
                //tv.setText(e.toString());
            }

            //===================================================


            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();

            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }

    }

    class SendAllData extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading data... Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters

            //int counter = -1;

            try {

                //int count = 0;
                //int id = 25;
                //String name = "Test JSON";
                //JSONObject jsonObject = new JSONObject();
                //jsonObject.put("id", id);
                //jsonObject.put("name",name);

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                //params.add(new BasicNameValuePair("json", jsonObject.toString()));
                params.add(new BasicNameValuePair("id", "21"));
                params.add(new BasicNameValuePair("name", "Test Json"));
                // Log.d("request!", "starting");

                jsonParser.makeHttpResponse("http://jpfruitshop.net76.net/book2.php", params);

            }
            catch(Exception e) {
                e.printStackTrace();
                //Toast.makeText(Subjects.this, e.toString(), 60).show();
                //tv.setText(e.toString());
            }

            //===================================================


            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();

            Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_LONG).show();
        }


    }
}
