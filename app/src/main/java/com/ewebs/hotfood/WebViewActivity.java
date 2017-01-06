package com.ewebs.hotfood;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.index_progressBar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("URL");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                (findViewById(R.id.progress_bar)).setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {

                    if (url.startsWith("mailto:")) {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse(url));
                        startActivity(emailIntent);
                        return true;
                    } else if (url.startsWith("tel:")) {
                        Intent telIntent = new Intent();
                        telIntent.setAction(Intent.ACTION_DIAL);
                        telIntent.setData(Uri.parse(url));
                        startActivity(telIntent);
                        return true;
                    } else if (url.startsWith("intent://")) {
                        try {
                            Context context = view.getContext();
                            Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);

                            if (intent != null) {
                                view.stopLoading();

                                PackageManager packageManager = context.getPackageManager();
                                ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                                if (info != null) {
                                    context.startActivity(intent);
                                } else {
                                    String fallbackUrl = intent.getStringExtra("browser_fallback_url");
                                    view.loadUrl(fallbackUrl);

                                }

                                return true;
                            }
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    return false;

                } catch (ActivityNotFoundException e) {
                    return false;
                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                new AlertDialog.Builder(view.getContext()).setMessage(message).setCancelable(true).show();
                result.confirm();
                return true;
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {//进度
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    if (progressBar.getVisibility() == View.GONE)
                        progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }
}
