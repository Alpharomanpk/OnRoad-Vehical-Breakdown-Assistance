package com.example.vehiclegarageapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Vehicle_policy extends AppCompatActivity
{

    WebView wbView;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        wbView = (WebView)findViewById(R.id.webView_mindmap);

        pb = (ProgressBar)findViewById(R.id.progressBar);

        final SwipeRefreshLayout swipeView =(SwipeRefreshLayout)findViewById(R.id.swipe);

        wbView.getSettings().setJavaScriptEnabled(true);
        wbView.getSettings().setLoadWithOverviewMode(true);
        wbView.getSettings().setUseWideViewPort(true);
        wbView.getSettings().setBuiltInZoomControls(true);
        pb.setVisibility(View.VISIBLE);

        wbView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                //pb.setVisibility(View.VISIBLE);

                if (progress == 100 && pb.isShown()) {
                    pb.setVisibility(View.GONE);
                    swipeView.setRefreshing(false);
                }
            }

        });

        wbView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //pb.setVisibility(View.VISIBLE);
            }


            @Override
            public void onPageFinished(WebView view, String url) {

                pb.setVisibility(View.GONE);

                swipeView.setRefreshing(false);
                String webUrl = wbView.getUrl();

            }

        });

        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);

                swipeView.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        wbView.reload();

                    }
                }, 1000);
            }
        });


        wbView.loadUrl("https://my.royalsundaram.in/health-insurance/arogya-sanjeevani");


    }


}

