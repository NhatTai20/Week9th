package com.example.rss_news;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class Channels extends AppCompatActivity {

    String[] channel={"VNEXPRESS","TUỔI TRẺ","THANH NIÊN", "VIETNAMNET"};

    GridView myGridView ; TextView txtMsg;
    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_channels);
            myGridView = (GridView) findViewById(R.id.channels);
            txtMsg = (TextView) findViewById(R.id.txtMsg);
            ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, // R.layout.my_text, //try this later...
                channel);
            myGridView .setAdapter(aa);
            myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView av, View v, int position, long id) {
                  //  txtMsg.setText("Position: " + position + "\nData: " + channel[position]);
                    Intent myIntentA1A2 = new Intent (Channels.this, MainActivity.class);
                    Bundle myBundle1 = new Bundle();
                    myBundle1.putInt ("val1", position);
                    myIntentA1A2.putExtras(myBundle1);
                    startActivityForResult(myIntentA1A2, 1122);
                }
            });
        }//onCreate
}
