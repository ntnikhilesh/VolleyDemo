package com.example.dell.volleydemo.logindemo;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dell.volleydemo.Mysingleton;
import com.example.dell.volleydemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

EditText luser_name,luser_pass;
    Button login,goto_reg;
    AlertDialog.Builder builder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

final String login_url="http://172.16.0.2/logindemo/login.php";
        builder=new AlertDialog.Builder(LoginActivity.this);
        luser_name=(EditText) findViewById(R.id.et_luser_name);
        luser_pass=(EditText) findViewById(R.id.et_luser_pass);
        login=(Button) findViewById(R.id.button_login);
        goto_reg=(Button) findViewById(R.id.button_goto_register_page);

        goto_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (luser_name.getText().toString().equals("")||luser_pass.getText().toString().equals(""))
                {
                    builder.setTitle("Something went wrong...");
                    displayAlert("er1");
                }

                else {


                    StringRequest stringRequest=new StringRequest(Request.Method.POST, login_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONArray jsonArray=new JSONArray(response);
                                        //get JSON Object from JSON array
                                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                                        String code=jsonObject.getString("code");
                                        if(code.equals("login failed"))
                                        {
                                            builder.setTitle("login error");
                                            displayAlert(jsonObject.getString("message"));
                                        }
                                        else {
                                            Toast.makeText(LoginActivity.this,"Login success,Welcome "+jsonObject.getString("name"),Toast.LENGTH_LONG).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(LoginActivity.this,"Something wrong with login..",Toast.LENGTH_LONG).show();


                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> prams = new HashMap<String, String>();

                            prams.put("user_name", luser_name.getText().toString());
                            prams.put("user_pass", luser_pass.getText().toString());
                            return prams;
                        }
                    };

                    //Add request to Request Queue
                    Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

                }

            }
        });
    }

    private void displayAlert(final String s1) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(s1.equals("er1"))
                {
                   // builder.setMessage("Invalid input");
                }

            }
        });

        android.support.v7.app.AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}
