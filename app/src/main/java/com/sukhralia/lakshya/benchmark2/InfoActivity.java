package com.sukhralia.lakshya.benchmark2;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by lakshya on 18-11-2016.
 */

public class InfoActivity  extends Activity {

    public void onInfo(View view)
    {
        Intent intent = new Intent(InfoActivity.this, Device_Info.class);
        startActivity(intent);
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thirdactivity);

        Button button4 = (Button) findViewById(R.id.btn5);

        button4.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent emailIntent= new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"Lakshyasukhralia97@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Your Device Model Here");
                emailIntent.putExtra(Intent.EXTRA_TEXT,"Description");
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent,"Choose Email Client"));



            }

        });
    }



}
