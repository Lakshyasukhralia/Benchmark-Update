package com.sukhralia.lakshya.benchmark2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;
import android.widget.TextView;


public class Device_Info extends Activity {

    // get System info.
    public static String OSNAME = System.getProperty("os.name");
    public static String OSVERSION = System.getProperty("os.version");
    public static String RELEASE = android.os.Build.VERSION.RELEASE;
    public static String DEVICE = android.os.Build.DEVICE;
    public static String MODEL = android.os.Build.MODEL;
    public static String PRODUCT = android.os.Build.PRODUCT;
    public static String BRAND = android.os.Build.BRAND;
    public static String DISPLAY = android.os.Build.DISPLAY;
    public static String UNKNOWN = android.os.Build.UNKNOWN;
    public static String HARDWARE = android.os.Build.HARDWARE;
    public static String ID = android.os.Build.ID;
    public static String MANUFACTURER = android.os.Build.MANUFACTURER;
    public static String SERIAL = android.os.Build.SERIAL;
    public static String USER = android.os.Build.USER;
    public static String HOST = android.os.Build.HOST;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device__info);
        textView=(TextView) findViewById(R.id.textViewi);
        textView.setText("OSName: "+OSNAME+"\nOSVersion: "+OSVERSION+"\nRelease: "+RELEASE+"\nDevice: "+DEVICE+"\nModel: "+MODEL+"\nProduct: "+PRODUCT+"\nBrand: "+BRAND
        +"\nDisplay: "+DISPLAY+"\nHardware: "+HARDWARE+"\nId: "+ID+"\nManufacturer: "+MANUFACTURER+"\nSerial: "+SERIAL+"\nUser: "+USER+"\nHost: "+HOST);

    }
}






