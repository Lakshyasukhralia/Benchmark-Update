package com.sukhralia.lakshya.benchmark2;


import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//Notification
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.content.Context;



public class MainActivity extends Activity {

    private TextView result;
    private Button compute;
    private String teststring;
    private String HashValue;
    private String MD5Value;
    private Long ttShared;
    private Long tt2Shared;
    private Long tt3Shared;
    private Integer jobsdone;
    private String output;
    private Integer floor;
    private Integer floor2;
    private Integer floor3;
    private Integer code = 9999999;
    MediaPlayer mySound;
    MediaPlayer myStop;






    public void sendMessage(View view)
    {
        Intent intent = new Intent(MainActivity.this, DatabaseVolley.class);
        startActivity(intent);
    }

    public void sendMessage2(View view)
    {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);
    }

    public void BeginIO(View view)
    {
        Intent intent = new Intent(MainActivity.this, ReadWriteActivity.class);
        startActivity(intent);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            final Long tt3 = (Long) msg.obj;
            result.setText("Brute Force Complete! Time Taken: \n" + tt3.toString());
            jobsdone++;
            tt3Shared = tt3;
            if (jobsdone == 3) {
                printout();
            }

        }
    };
    Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            final Long tt2 = (Long) msg.obj;
            result.setText("MD5 Complete! Time Taken: \n" + tt2.toString());
            jobsdone++;
            tt2Shared = tt2;
            if (jobsdone == 3) {
                printout();
            }
        }
    };

    Handler handler3 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            final Long tt = (Long) msg.obj;
            result.setText("SHA-1 Complete! Time Taken: \n" + tt.toString());
            jobsdone++;
            ttShared = tt;
            if (jobsdone == 3) {
                printout();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compute = (Button) findViewById(R.id.btn1);
        result = (TextView) findViewById(R.id.textView2);
        teststring = getResources().getString(R.string.teststring);
        Toast.makeText(MainActivity.this, "Please close all running apps for best results!", Toast.LENGTH_LONG).show();
        mySound=MediaPlayer.create(this,R.raw.record);
        myStop=MediaPlayer.create(this,R.raw.stop);
    }




//SNAPSHOT
    public void onShot(View view)

    {
        takeScreenshot();
        Toast.makeText(MainActivity.this, "Saved to Root Directory", Toast.LENGTH_LONG).show();
    }


    private void takeScreenshot() {


        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(intent);


        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {

            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }




    public void printout() {
        myStop.start();
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        compute.setText("REINITIATE SEQUENCE");
        Long average = (tt2Shared + tt3Shared + ttShared) / 3;
        Integer score =150-Math.round(average / 100000000);
        result.setText("SHA-1 Time Taken: \n" + ttShared.toString() + "\nMD5 Time Taken: \n" + tt2Shared.toString() + "\nBrute Force Time Taken: \n" + tt3Shared.toString() + "\n \nScore: " + score.toString());
        if(score<=50)
        Toast.makeText(MainActivity.this, "You need a serious upgrade!", Toast.LENGTH_LONG).show();
        else
            if(score>50&&score<125)
            Toast.makeText(MainActivity.this, "Mediocre Device", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Excellent Device!", Toast.LENGTH_LONG).show();

    }

    public void onBeginClick(View view) {
        mySound.start();
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);


        Intent intent = new Intent(MainActivity.this, DatabaseVolley.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder.setContentIntent(pendingIntent);

                        mBuilder.setSmallIcon(R.drawable.noti);
                        mBuilder.setContentTitle("Benchmark");
                        mBuilder.setContentText("Tap to Share!");

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(001, mBuilder.build());


        jobsdone = 0;
        computeSHAHash(teststring);
        computeMD5Hash(teststring);
        compute.setText("CALCULATING");
        bruteForce();

    }



    public void bruteForce() {

        Runnable r = new Runnable() {
            public void run() {
                Long tsLong3 = System.nanoTime();
                Integer successfulcode = 0;
                for (Integer i = 0; i < code; i++) {
                    successfulcode = i;
                }
                Long ttLong3 = System.nanoTime() - tsLong3;
                Message msg = Message.obtain();
                msg.obj = ttLong3;
                msg.setTarget(handler);
                if (successfulcode != 0) {
                    msg.sendToTarget();
                }

            }
        };
        Thread newThread = new Thread(r);
        newThread.start();

    }


    public void computeMD5Hash(String password) {
        Runnable r = new Runnable() {
            public void run() {
                Long tsLong = System.nanoTime();
                for (Integer s = 0; s < 25000; s++) {
                    try {
                        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
                        digest.update("The big bad wolf".getBytes());
                        byte messageDigest[] = digest.digest();

                        StringBuffer MD5Hash = new StringBuffer();
                        for (int i = 0; i < messageDigest.length; i++) {
                            String h = Integer.toHexString(0xFF & messageDigest[i]);
                            while (h.length() < 2)
                                h = "0" + h;
                            MD5Hash.append(h);
                        }

                        MD5Value = MD5Hash.toString();

                    } catch (NoSuchAlgorithmException e) {
                        Log.e("Benchmark", "Error initializing MD5");
                    }
                }
                Long ttLong2 = System.nanoTime() - tsLong;
                Message msg = Message.obtain();
                msg.obj = ttLong2;
                msg.setTarget(handler2);
                msg.sendToTarget();

            }
        };
        Thread newThread = new Thread(r);
        newThread.start();

    }


    public void computeSHAHash(String password) {
        Runnable r = new Runnable() {
            public void run() {
                Long tsLong = System.nanoTime();
                for (Integer i = 0; i < 25000; i++) {
                    MessageDigest mdSha1 = null;
                    try {
                        mdSha1 = MessageDigest.getInstance("SHA-1");
                    } catch (NoSuchAlgorithmException e1) {
                        Log.e("Benchmark", "Error initializing SHA1");
                    }
                    try {
                        mdSha1.update("The big bad wolf".getBytes("ASCII"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    byte[] data = mdSha1.digest();
                    StringBuffer sb = new StringBuffer();
                    String hex = null;
                    hex = Base64.encodeToString(data, 0, data.length, 0);

                    sb.append(hex);
                    HashValue = sb.toString();
                }

                Long ttLong3 = System.nanoTime() - tsLong;
                Message msg = Message.obtain();
                msg.obj = ttLong3;
                msg.setTarget(handler3);
                msg.sendToTarget();

            }

        };
        Thread newThread = new Thread(r);
        newThread.start();


    }

}


