package com.example.nykaatestapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    SwipeRefreshLayout swipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe = findViewById(R.id.swipeContainer);
        webView = findViewById(R.id.webView);
        swipe.setDistanceToTriggerSync(200);

        swipe.setOnChildScrollUpCallback((parent, child) -> {
            Log.i("Test", "Can child scroll up method ");
            Log.i("Test", "Scroll View Y position" + webView.getScrollY());
            return true;
        }
        );
        swipe.setOnRefreshListener(() -> LoadWeb());
        LoadWeb();
    }


    public void LoadWeb(){
        webView =  findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        //webView.loadUrl("https://nykaa.clickpost.in/?waybill=1339095367332");
        webView.loadUrl("https://www.nykaa.com/brands/lakme/c/604?id=604&ptype=brand&root=brand_menu,top_brands,Lakme&popularity_algo=conversion");
        swipe.setRefreshing(true);

        webView.setWebViewClient(new WebViewClient()
        {
            public  void  onPageFinished(WebView view, String url){
                swipe.setRefreshing(false);
            }

        });
    }
    @Override
    public void onBackPressed(){

        if (webView.canGoBack()){
            webView.goBack();
        }else {
            finish();
        }
    }




}