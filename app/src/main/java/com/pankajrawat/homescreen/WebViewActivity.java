package com.pankajrawat.homescreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        progress=findViewById(R.id.p);
        Intent intent=getIntent();
        String linked=intent.getStringExtra("links");
        String p="http://"+linked;

        webView=findViewById(R.id.webviewview);
        WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);
      //  webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
      //  webView.setWebViewClient(new WebViewClient());
String linkurl="http://www.google.com";

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                progress.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });
        webView.loadUrl(p);
    }
}
