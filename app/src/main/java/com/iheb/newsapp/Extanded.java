package com.iheb.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Extanded extends AppCompatActivity {
    String sectionName;
    String webPublicationDate;
    String webTitle;
    String pillarName;
    String webUrl;
    Button button;
    TextView sectionNameText;
    TextView webPublicationDateText;
    TextView webTitleText;
    TextView pillarNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extanded);
        sectionName = getIntent().getStringExtra("sectionName");
        webPublicationDate = getIntent().getStringExtra("webPublicationDate");
        webTitle = getIntent().getStringExtra("webTitle");
        webUrl = getIntent().getStringExtra("webUrl");
        pillarName = getIntent().getStringExtra("pillarName");
        sectionNameText = findViewById(R.id.sectionname);
        webPublicationDateText = findViewById(R.id.exdate);
        webTitleText = findViewById(R.id.extitle);
        pillarNameText = findViewById(R.id.pillar_name);
        sectionNameText.setText(sectionName);
        webPublicationDateText.setText(webPublicationDate);
        webTitleText.setText(webTitle);
        pillarNameText.setText(pillarName);
        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openWebsite = new Intent(Intent.ACTION_VIEW);
                openWebsite.setData(Uri.parse(webUrl));
                startActivity(openWebsite);
            }
        });
    }
}
