package com.example.dell.volleydemo.logindemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.dell.volleydemo.MainActivity;
import com.example.dell.volleydemo.Mysingleton;
import com.example.dell.volleydemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText rname,remail,ruser_name,ruser_pass;
    Button register,rgoto_login;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String register_url="http://172.16.0.2/logindemo/register.php";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        builder=new AlertDialog.Builder(RegisterActivity.this);
        rname=(EditText)findViewById(R.id.et_rname);
        remail=(EditText)findViewById(R.id.et_remail);
        ruser_name=(EditText)findViewById(R.id.et_ruser_name);
        ruser_pass=(EditText)findViewById(R.id.et_ruser_pass);
        register=(Button) findViewById(R.id.button_register);
        rgoto_login=(Button) findViewById(R.id.button_rgoto_login);


        rgoto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rname.getText().toString().equals("")||remail.getText().toString().equals("")||ruser_name.getText().toString().equals("")||ruser_pass.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Invalid input" ,Toast.LENGTH_LONG).show();
                }
                else {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, register_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String code=jsonObject.getString("code");
                                    String message = jsonObject.getString("message");
                                     builder.setTitle("Server Response...");
                                    builder.setMessage(message);
                                     displayAlert(code);
                                    //Toast.makeText(RegisterActivity.this, "*" + message, Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Some thing wrong with registration...", Toast.LENGTH_LONG).show();
                        Log.d("regerror", ": " + error);

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> prams = new HashMap<String, String>();
                        prams.put("name", rname.getText().toString());
                        prams.put("email", remail.getText().toString());
                        prams.put("user_name", ruser_name.getText().toString());
                        prams.put("user_pass", ruser_pass.getText().toString());
                        return prams;
                    }
                };

                //Add request to Request Queue
                Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

            }
            }
        });
    }


    public void displayAlert(final String code)
    {
builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(code.equals("reg failed.."))
        {
            rname.setText("");
            remail.setText("");
            ruser_name.setText("");
            ruser_pass.setText("");

        }
        else if(code.equals("reg success.."))
        {
            //Toast.makeText(RegisterActivity.this,""+code,Toast.LENGTH_LONG).show();
            finish();
        }
    }
});

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
