package com.closet.anusha.dresser;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main22Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textView = (TextView) findViewById(R.id.mainTv1);
        ImageView imageView = (ImageView) findViewById(R.id.mainScreenImage);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Billabong.ttf");
        textView.setTypeface(face);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main22Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
