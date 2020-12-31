package com.akashwebapps.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.akashwebapps.retrofitsample.retrofit.interfaces.OnCallBackListner;
import com.akashwebapps.retrofitsample.retrofit.models.Coummunication;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnCallBackListner {
    private Coummunication coummunication;
    private EditText uname,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=findViewById(R.id.uame);
        pass=findViewById(R.id.pass);
        coummunication=new Coummunication(this,this);

      //  apicall();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String,String> map=new HashMap<>();
                map.put("username",uname.getText().toString());
                map.put("password",pass.getText().toString());
                
                coummunication.callPOSTwithUrl("http://45.77.244.128:8089/dpboss_api/api/User/login.php",map,"login");

            }
        });







    }

    private void apicall() {

        final String URL = "http://45.77.244.128:8089/dpboss_api/api/User/login.php";
// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
     //   params.put("token", "token_value");
     //   params.put("login_id", "login_id_value");
        params.put("username", "admin");
        params.put("password", "12345");

        JsonObjectRequest request_json = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Process os success response

                        try {
                            Log.d("error",""+response);
                            Toast.makeText(MainActivity.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Log.d("error",""+error.getMessage());

            }
        });

// add the request object to the queue to be executed
        Volley.newRequestQueue(this).add(request_json);

    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {

        if (tag.equalsIgnoreCase("login")){

            try {
                Toast.makeText(this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        
    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }
}