package com.tvs.maintenance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tvs.maintenance.R;
import com.tvs.maintenance.util.constants.Constantes;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=findViewById(R.id.user);
        findViewById(R.id.ingresar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constantes.CurrentUser=user.getText().toString().equalsIgnoreCase("INDIAN")? Constantes.MenuUser: Constantes.MenuSTA;
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }



}
