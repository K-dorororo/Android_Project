package com.iot.matzip_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    public static final String TAG_MSG = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //openHelper = new DBOpenHelper(this,DB_NAME,null, DB_VERSION);

        EditText search_text = (EditText) findViewById(R.id.searchMatZip);
        Button search_btn = (Button) findViewById(R.id.searchButton);
        Button total_btn = (Button) findViewById(R.id.total);
        Button yuseong = (Button) findViewById(R.id.yuseong);
        Button daeduck = (Button) findViewById(R.id.daeduck);
        Button west = (Button) findViewById(R.id.west);
        Button joonggu = (Button) findViewById(R.id.joonggu);
        Button east = (Button) findViewById(R.id.east);

        TextView kaisen = (TextView) findViewById(R.id.kaisen);
        TextView meat = (TextView) findViewById(R.id.meat);
        TextView kalnoodle = (TextView) findViewById(R.id.kalgooksu);
        TextView kukbab = (TextView) findViewById(R.id.kukbob);
        TextView susi = (TextView) findViewById(R.id.susi);
        TextView bunsik = (TextView) findViewById(R.id.bunsik);
        TextView yukke = (TextView) findViewById(R.id.yukke);
        TextView chige = (TextView) findViewById(R.id.chige);
        TextView chikin = (TextView) findViewById(R.id.chikin);
        TextView dakgui = (TextView) findViewById(R.id.dakgui);
        TextView shabu = (TextView) findViewById(R.id.shabu);


        Button button = (Button)findViewById(R.id.writeButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        Category_activity.class);
                startActivity(intent);
            }
        });
        total_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MainActivity.this, MatZip_Info.class);
                startActivity(intent);
            }
        });

        yuseong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, yuseong_activity.class);
                startActivity(intent);
            }
        });

        daeduck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Daeduck_activity.class);
                startActivity(intent);
            }
        });
        west.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, West_activity.class);
                startActivity(intent);
            }
        });
        joonggu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Joonggu_activity.class);
                startActivity(intent);
            }
        });
        east.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, East_activity.class);
                startActivity(intent);
            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MainActivity.this, Search_activity.class);
                String search_words = search_text.getText().toString();
                search_text.setText("");
                intent.putExtra(TAG_MSG, search_words);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        kaisen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kaisen_activity.class);
                String msg = kaisen.getText().toString();
                intent.putExtra(TAG_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kaisen_activity.class);
                String msg = meat.getText().toString();
                intent.putExtra(TAG_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        kalnoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kaisen_activity.class);
                String msg = kalnoodle.getText().toString();
                intent.putExtra(TAG_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        kukbab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kaisen_activity.class);
                String msg = kukbab.getText().toString();
                intent.putExtra(TAG_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        susi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kaisen_activity.class);
                String msg = susi.getText().toString();
                intent.putExtra(TAG_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        bunsik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kaisen_activity.class);
                String msg = bunsik.getText().toString();
                intent.putExtra(TAG_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        shabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kaisen_activity.class);
                String msg = shabu.getText().toString();
                intent.putExtra(TAG_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        yukke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kaisen_activity.class);
                String msg = yukke.getText().toString();
                intent.putExtra(TAG_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        chige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kaisen_activity.class);
                String msg = chige.getText().toString();
                intent.putExtra(TAG_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        chikin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kaisen_activity.class);
                String msg = chikin.getText().toString();
                intent.putExtra(TAG_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        dakgui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Kaisen_activity.class);
                String msg = dakgui.getText().toString();
                intent.putExtra(TAG_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
}