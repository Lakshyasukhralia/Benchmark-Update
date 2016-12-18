package com.sukhralia.lakshya.benchmark2;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class ReadWriteActivity extends Activity {



    EditText txtData;
    TextView result;
    TextView result2;
    private Integer WR=0;
    private Long TWS;
    private Long TRS;
    private Integer score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write);
        result=(TextView)findViewById(R.id.W);
        result2=(TextView)findViewById(R.id.R);
        txtData = (EditText) findViewById(R.id.txtData);
        txtData.setHint("Enter data to Input");

    }


    Handler handlerW = new Handler() {
        @Override
       public void handleMessage(Message msg) {
           final Long ttw = (Long) msg.obj;
            TWS=ttw;
            result.setText("Writing Complete! Time Taken: \n" + ttw.toString());
            WR++;

       }
   };

    Handler handlerR = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            final Long ttr = (Long) msg.obj;
            TRS=ttr;
            result2.setText("Reading Complete! Time Taken: \n" + ttr.toString());
            WR++;
        }
    };


    public void Output(View view)
    {
        //Long FResult=(TWS+TRS/2);
        //score=Math.round(FResult);
        if(WR>0)
        Toast.makeText(ReadWriteActivity.this,"Optimum SD Speed", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(ReadWriteActivity.this,"Dead SD Card", Toast.LENGTH_LONG).show();
    }

    public void Clear(View view)
    {
            txtData.setText("");
            result.setText("");
            result2.setText("");
    }

    public void Write(View view)
    {
        Runnable r = new Runnable() {
            public void run() {


                try {
                    Long tsLongw = System.nanoTime();
                    File myFile = new File("/sdcard/mysdfile.txt");
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter =
                            new OutputStreamWriter(fOut);
                    myOutWriter.append(txtData.getText());
                    myOutWriter.close();
                    fOut.close();
                    Long ttLongw = System.nanoTime() - tsLongw;
                    Message msg = Message.obtain();
                    msg.obj = ttLongw;
                    msg.setTarget(handlerW);

                    msg.sendToTarget();


                } catch (Exception e) {

                }
            }
        };
        Thread newThread = new Thread(r);
        newThread.start();
    }




    public void Read(View view) {
       Runnable r = new Runnable() {
           public void run() {
                try {
                    Long tsLongr = System.nanoTime();
                    File myFile = new File("/sdcard/mysdfile.txt");
                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(
                            new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null) {
                        aBuffer += aDataRow + "\n";
                    }
                    txtData.setText(aBuffer);
                    myReader.close();

                    Long ttLongr = System.nanoTime() - tsLongr;
                    Message msg = Message.obtain();
                    msg.obj = ttLongr;
                    msg.setTarget(handlerR);
                    msg.sendToTarget();

                } catch (Exception e) {

                }
            }
        };
        Thread newThread = new Thread(r);
        newThread.start();
    }

}