package com.example.Infecto.ImprintMx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PrincipalUserActivity extends AppCompatActivity {

    private TextView tvName, tvPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principaluser);

        tvName = findViewById(R.id.tvusername);
        tvPass = findViewById(R.id.tvsecondName);

        tvName.setText(LoginActivity.firstName);
        tvPass.setText(LoginActivity.secondName);

    }
}
