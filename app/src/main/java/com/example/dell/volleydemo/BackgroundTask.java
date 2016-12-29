package com.example.dell.volleydemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DELL on 12/28/2016.
 */

public class BackgroundTask {
    private final OnSuccess onSuccess;
    Context context;
    ArrayList<Contact> arrayList;

    String json_url="http://172.16.0.2/getinfoarray.php";
    private boolean k;

    public BackgroundTask(Context context,OnSuccess onSuccess)
    {
        this.context=context;
        this.onSuccess=onSuccess;
    }

    public ArrayList<Contact> getList()
    {
        arrayList=new ArrayList<Contact>();
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, json_url, (String)null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("ntresponse","-"+response);
                        int count=0;
                        while (count<response.length())
                        {
                            try {
                                JSONObject jsonObject=response.getJSONObject(count);
                                Contact contact=new Contact(jsonObject.getString("name"),jsonObject.getString("user_name"),jsonObject.getString("user_pass"));
                                Log.d("ntresponse1","-"+contact);
                                arrayList.add(contact);
                                onSuccess.getResult(arrayList);
                                count++;
                                Log.d("ntresponsesize","-"+arrayList.size());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Something went wrong...",Toast.LENGTH_LONG).show();
                Log.d("nterror","-"+error.getMessage());

            }
        });

        Log.d("ntresponse2","-"+arrayList.size());
        //Use Singleton pattern for request queue
       Mysingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);

        return arrayList;


}}
