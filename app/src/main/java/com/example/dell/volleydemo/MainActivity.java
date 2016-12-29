package com.example.dell.volleydemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView server_response;
    Button get_response,get_image,get_JSON,get_JSONArray;
    ImageView profile_image;

    String server_url="http://172.16.0.2/greetings.php";
    String server_url1="http://172.16.0.2/nikhil1.jpg";
    String json_url="http://172.16.0.2/getinfo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        server_response=(TextView)findViewById(R.id.tv_server_response);
        get_response=(Button)findViewById(R.id.button_get_resp);
        get_image=(Button)findViewById(R.id.button_get_image);
        profile_image=(ImageView)findViewById(R.id.iv_server_img);
        get_JSON=(Button)findViewById(R.id.button_get_json);
        get_JSONArray=(Button)findViewById(R.id.button_show_jsonarray);


        //Show JSON Array on Recycler View
        get_JSONArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get JSON Array from server
                Intent intent=new Intent(MainActivity.this,ShowJsonArray.class);
                startActivity(intent);

            }
        });


        //Get JSON response from server
        get_JSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//Null is used for request body
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, json_url, (String)null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Log.d("json response","-"+response.getString("Name"));
                                    server_response.setText("Name - "+response.getString("Name")+"\n"+
                                            "User Name - "+response.getString("User Name")+"\n"+
                                            "User Pass - "+response.getString("User Pass"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Something went wrong...",Toast.LENGTH_LONG).show();
                        error.printStackTrace();

                    }
                });

                //Add request to Request Queue
                // requestQueue.add(stringRequest);
                Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

            }
        });



        //Get text response from Server
        get_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create request queue
                //final RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);

                //Create String Request(1st argument -Method type,2-URL,3, Response Listner , Error Listner
                StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                server_response.setText(response);
                                //stop request queue
                                //requestQueue.stop();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        server_response.setText("Something went wrong...");
                        error.printStackTrace();
                        //requestQueue.stop();

                    }
                });

                //Add request to Request Queue
               // requestQueue.add(stringRequest);
                Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

            }
        });


//Get image from Serrver
        get_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ImageRequest imageRequest=new ImageRequest(server_url1,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                profile_image.setImageBitmap(response);

                            }
                        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(MainActivity.this,"Something went wrong...",Toast.LENGTH_LONG).show();
                                error.printStackTrace();

                            }


                        }); //0 represnt the exact size of the Image

                //Add request to Request Queue
                Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(imageRequest);

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
}
