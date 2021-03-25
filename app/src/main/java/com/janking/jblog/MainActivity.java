package com.janking.jblog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        startIntenet();
    }
    private void loadWeb(){
       webView.loadUrl("http://jankinghuang.gitee.io");
    }

    private void startIntenet(){
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, 1);
        }else{
            loadWeb();
            Toast.makeText(this, "有网络权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    loadWeb();
                    Toast.makeText(this, "有网络权限", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "没有网络权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}