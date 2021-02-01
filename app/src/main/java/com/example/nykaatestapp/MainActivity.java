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
    MyWebview mWebView;
    SwipeRefreshLayout swipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe = findViewById(R.id.swipeContainer);
        mWebView = findViewById(R.id.webView);
        mWebView.setViewGroup(swipe);
        initListener(swipe);
        swipe.setOnRefreshListener(() -> LoadWeb());
        LoadWeb();
    }


    public void LoadWeb(){
        mWebView =  findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.loadUrl("https://nykaa.clickpost.in/?waybill=1339095367332");
        swipe.setRefreshing(true);

        mWebView.setWebViewClient(new WebViewClient()
        {
            public  void  onPageFinished(WebView view, String url){
                swipe.setRefreshing(false);
            }

        });
    }
    @Override
    public void onBackPressed(){

        if (mWebView.canGoBack()){
            mWebView.goBack();
        }else {
            finish();
        }
    }

    private void initListener(SwipeRefreshLayout mSwipe) {
        mSwipe.setOnRefreshListener(() -> {
            mWebView.reload();
            mSwipe.setRefreshing(false);
        });
        mSwipe.setOnChildScrollUpCallback((parent, child) -> mWebView.getScrollY()>0);
    }


}