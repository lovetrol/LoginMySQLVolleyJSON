package com.example.Infecto.ImprintMx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private String URLline = "http://infecto.mx/imprint/v1/login.php";

    private EditText etUname, etPass;
    private Button btn;
    public static String firstName, secondName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUname = findViewById(R.id.etusername);
        etPass = findViewById(R.id.etpassword);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }

    private void loginUser(){

      final String username = etUname.getText().toString().trim();
      final String password = etPass.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();

                        parseData(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username);
                params.put("password",password);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void parseData(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            String error = jsonObject.getString("error");
            if (error.equals("false")) {
                Intent intent = new Intent(LoginActivity.this, PrincipalUserActivity.class);
                startActivity(intent);
                //Toast.makeText(LoginActivity.this,"Bienvenido: "+ firstName ,Toast.LENGTH_LONG).show();

                JSONArray dataArray = jsonObject.getJSONArray(response);
                //JSONArray dataArray = new JSONArray(response);
                for (int i = 0; i < dataArray.length(); i++) {

                    JSONObject dataobj = dataArray.getJSONObject(i);
                    firstName = dataobj.getString("usuario_nickname");
                    secondName = dataobj.getString("usuario_apellimat");

                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
