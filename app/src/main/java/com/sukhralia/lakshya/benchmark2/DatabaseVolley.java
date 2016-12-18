package com.sukhralia.lakshya.benchmark2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import java.util.HashMap;
import java.util.Map;
import static com.sukhralia.lakshya.benchmark2.R.string.teststring;

/**
 * Created by lakshya on 18-11-2016.
 */

public class DatabaseVolley extends Activity {

    Button button;
    EditText Device, Score;
    String server_url = "http://192.168.43.228/update_info.php";
    AlertDialog.Builder builder;


    public void onClick2(View view)
    {
        Intent intent = new Intent(DatabaseVolley.this, WebActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);
        Device=(EditText)findViewById(R.id.Device);
        Score=(EditText)findViewById(R.id.Score);
        builder = new AlertDialog.Builder(DatabaseVolley.this);
        button = (Button) findViewById(R.id.btn2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DatabaseVolley.this, "Thanks For Sharing", Toast.LENGTH_LONG).show();
                final String device,score;
                device = Device.getText().toString();
                score = Score.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response: "+response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Device.setText("");
                                Score.setText("");
                            }
                        });
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DatabaseVolley.this,"Error!",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params=new HashMap<String, String>();
                        params.put("Device",device);
                        params.put("Score",score);
                        return params;
                    }
                };

                MySingleton.getmInstance(DatabaseVolley.this).addTorequestQue(stringRequest);
            }

        });

    }

}

