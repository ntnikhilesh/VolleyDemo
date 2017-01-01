package com.example.dell.volleydemo.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.dell.volleydemo.R;

public class LoginActivity extends AppCompatActivity {

EditText luser_name,luser_pass;
    Button login,goto_reg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

final String login_url="http://172.16.0.2/logindemo/register.php";
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


               // StringRequest stringRequest=new StringRequest(Request.Method.POST,login_url)

            }
        });
    }
}
