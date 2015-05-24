package com.example.i306851.androidappjsonparser;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {


    private ProgressDialog pDialog;
    String message = "";

    private DatabaseController dbDatabaseController;

    private Context context;

    private static final String TAG_NAME = "Name";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private int success;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        btn = (Button) findViewById(R.id.btn);
        Button send = (Button) findViewById(R.id.send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Loading data... Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();



                dbDatabaseController.loadAllData(context, loadDataListener);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Loading data... Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
                EditText itemName = (EditText)findViewById(R.id.item_name);
                TransactionData transactionData = new TransactionData();
                transactionData.setItmeName(itemName.getText().toString());
                dbDatabaseController.sendAllData(context,transactionData, sendDataListener);

            }
        });
        dbDatabaseController = DatabaseController.getInstance();
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


    DatabaseController.LoadDataListener loadDataListener = new DatabaseController.LoadDataListener() {
        @Override
        public void loadComplete(JSONObject json) {

            // json success tag

            try {
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    JSONArray jsonArray = json.getJSONArray("item");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject names = jsonArray.getJSONObject(i);
                        message += " " + names.getString(TAG_NAME);
                    }
                }

                pDialog.dismiss();

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(Subjects.this, e.toString(), 60).show();
                //tv.setText(e.toString());
            }
        }
    };

    DatabaseController.SendDataListener sendDataListener = new DatabaseController.SendDataListener() {
        @Override
        public void sendComplete() {

            pDialog.dismiss();

            Toast.makeText(MainActivity.this, "send complete", Toast.LENGTH_LONG).show();

        }
    };


}
