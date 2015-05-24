package com.example.i306851.androidappjsonparser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jagmeet on 5/24/2015.
 */
public class DatabaseController {

    private static DatabaseController dbController;
    JSONParser jsonParser = new JSONParser();


    private DatabaseController() {}

    public static DatabaseController getInstance() {
        if (dbController != null) {
            return dbController;
        }
        dbController = new DatabaseController();
        return dbController;

    }


    public void sendAllData(Context context,TransactionData transactionData ,SendDataListener sendDataListener) {
        new SendAllData(transactionData,sendDataListener).execute();
    }

    public void loadAllData(Context context, LoadDataListener loadDataListener) {
        new LoadAllCategoryQues(loadDataListener).execute();
    }

    class LoadAllCategoryQues extends AsyncTask<String, String, String> {


        LoadDataListener loadDataListener;
        JSONObject json;

        public LoadAllCategoryQues(LoadDataListener loadDataListener) {
            this.loadDataListener = loadDataListener;
        }

//		int lastViewedPosition;
//		View v;
//		int topOffset;

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        /**
         * getting All products from url
         */
        protected String doInBackground(String... args) {
            // Building Parameters

            //int counter = -1;

            //int count = 0;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //params.add(new BasicNameValuePair("data", "Deepak"));
            // Log.d("request!", "starting");

            json = jsonParser.makeHttpRequest(
                    "http://jpfruitshop.net76.net/book1.php", "POST", params);


            //===================================================


            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            loadDataListener.loadComplete(json);
        }

    }

    class SendAllData extends AsyncTask<String, String, String> {

        SendDataListener sendDataListener;
        TransactionData transactionData;

        public SendAllData(TransactionData transactionData,SendDataListener sendDataListener) {
            this.sendDataListener = sendDataListener;
            this.transactionData = transactionData;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        /**
         * getting All products from url
         */
        protected String doInBackground(String... args) {
            // Building Parameters

            //int counter = -1;

            try {

                //int count = 0;
                int id = 25;
                String name = "Test JSON";
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", id);
                jsonObject.put("name", name);
                Log.d("test", "Jagmeet");

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("json", jsonObject.toString()));
                // Log.d("request!", "starting");

                jsonParser.makeHttpResponse("http://jpfruitshop.net76.net/book2.php", params);

            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(Subjects.this, e.toString(), 60).show();
                //tv.setText(e.toString());
            }

            //===================================================


            return null;
        }

        protected void onPostExecute(String file_url) {
            sendDataListener.sendComplete();

        }

    }

    public interface SendDataListener {
        public void sendComplete();
    }

    public interface LoadDataListener {
        public void loadComplete(JSONObject jsonObject);
    }
}
