package com.sukhralia.lakshya.benchmark2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends Activity {

    private WebView myWebView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        myWebView3=(WebView)findViewById(R.id.webView3);
        WebSettings webSettings=myWebView3.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView3.loadUrl("http://192.168.43.228/Chart.png");
        myWebView3.setWebViewClient(new WebViewClient());
    }
}
