package com.example.rss_news;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {
    // Main GUI - A NEWS application based on National Public Radio RSS material
    ArrayAdapter<String> adapterMainSubjects;
    ListView myMainListView;
    Context context;
    SingleItem selectedNewsItem;
    TextView channel;
    // hard-coding main NEWS categories (TODO: use a resource file)
    String[][] myUrlCaptionMenu = {
            {"https://vnexpress.net/rss/the-gioi.rss", "THẾ GIỚI"},
            {"https://vnexpress.net/rss/thoi-su.rss", "THỜI SỰ"},
            {"https://vnexpress.net/rss/kinh-doanh.rss", "KINH DOANH"},
            {"https://vnexpress.net/rss/startup.rss", "STARTUP"},
            {"https://vnexpress.net/rss/giai-tri.rss", "GIẢI TRÍ"},
            {"https://vnexpress.net/rss/the-thao.rss", "THỂ THAO"},
            {"https://vnexpress.net/rss/phap-luat.rss", "PHÁP LUẬT"}
    };
    String[][] myUrlCaptionMenu1 = {
            {"https://tuoitre.vn/rss/the-gioi.rss", "THẾ GIỚI"},
            {"https://tuoitre.vn/rss/thoi-su.rss", "THỜI SỰ"},
            {"https://tuoitre.vn/rss/kinh-doanh.rss", "KINH DOANH"},
            {"https://tuoitre.vn/rss/suc-khoe.rss", "SỨC KHOẺ"},
            {"https://tuoitre.vn/rss/giai-tri.rss", "GIẢI TRÍ"},
            {"https://tuoitre.vn/rss/the-thao.rss", "THỂ THAO"},
            {"https://tuoitre.vn/rss/phap-luat.rss", "PHÁP LUẬT"}
    };
    String[][] myUrlCaptionMenu2 = {
            {"https://thanhnien.vn/rss/the-gioi.rss", "THẾ GIỚI"},
            {"https://thanhnien.vn/rss/van-hoa.rss", "VĂN HOÁ"},
            {"https://thanhnien.vn/rss/doi-song.rss", "ĐỜI SỐNG"},
            {"https://thanhnien.vn/rss/tai-chinh-kinh-doanh.rss", "TÀI CHÍNH - KINH DOANH"},
            {"https://thanhnien.vn/rss/giai-tri.rss", "GIẢI TRÍ"},
            {"https://thanhnien.vn/rss/cong-nghe.rss", "CÔNG NGHỆ"},
            {"https://game.thanhnien.vn/rss/home.rss", "GAME"}
    };
    String[][] myUrlCaptionMenu3 = {
            {"https://vietnamnet.vn/rss/the-gioi.rss", "THẾ GIỚI"},
            {"https://vietnamnet.vn/rss/thoi-su-chinh-tri.rss", "THỜI SỰ - CHÍNH TRỊ"},
            {"https://vietnamnet.vn/rss/kinh-doanh.rss", "KINH DOANH"},
            {"https://vietnamnet.vn/rss/suc-khoe.rss", "SỨC KHOẺ"},
            {"https://vietnamnet.vn/vn/giai-tri/index.html", "GIẢI TRÍ"},
            {"https://vietnamnet.vn/rss/the-thao.rss", "THỂ THAO"},
            {"https://vietnamnet.vn/rss/phap-luat.rss", "PHÁP LUẬT"}
    };
    //define convenient URL and CAPTIONs arrays
    String[] myUrlCaption = new String[myUrlCaptionMenu.length];
    String[] myUrlAddress = new String[myUrlCaptionMenu.length];
    String[] myUrlCaption1 = new String[myUrlCaptionMenu1.length];
    String[] myUrlAddress1 = new String[myUrlCaptionMenu1.length];
    String[] myUrlCaption2 = new String[myUrlCaptionMenu2.length];
    String[] myUrlAddress2 = new String[myUrlCaptionMenu2.length];
    String[] myUrlCaption3 = new String[myUrlCaptionMenu3.length];
    String[] myUrlAddress3 = new String[myUrlCaptionMenu3.length];
    public static String niceDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM d, yyyy",


                Locale.US);
        return sdf.format(new Date()); //Monday Apr 7, 2014
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        channel = (TextView) findViewById(R.id.channels);
        //====================
        Intent myCallerIntent = getIntent();
        Bundle myBundle = myCallerIntent.getExtras();
        int val1 = myBundle.getInt("val1");
        //===================
        if(val1 == 0)
        {
            channel.setText("CHANNELS IN VNEXPRESS");
            for (int i = 0; i < myUrlAddress.length; i++) {
                myUrlAddress[i] = myUrlCaptionMenu[i][0];
                myUrlCaption[i] = myUrlCaptionMenu[i][1];
            }
            context = getApplicationContext();
            //this.setTitle("Vnexpress Headline News\n" + niceDate());
            // user will tap on a ListView’s row to request category’s headlines
            myMainListView = (ListView) this.findViewById(R.id.myListView);
            myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> _av, View _v, int _index, long _id) {
                    String urlAddress = myUrlAddress[_index], urlCaption = myUrlCaption[_index];
                    //create an Intent to talk to activity: ShowHeadlines
                    Intent callShowHeadlines = new Intent(MainActivity.this, ShowHeadlines.class);
                    //prepare a Bundle and add the input arguments: url & caption
                    Bundle myData = new Bundle();
                    myData.putString("urlAddress", urlAddress);
                    myData.putString("urlCaption", urlCaption);
                    myData.putString("Channel", "VNEXPRESS");
                    callShowHeadlines.putExtras(myData);
                    startActivity(callShowHeadlines);
                }
            });
            // fill up the Main-GUI’s ListView with main news categories
            adapterMainSubjects = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myUrlCaption);
            myMainListView.setAdapter(adapterMainSubjects);
        }
        else if(val1 == 1){
            channel.setText("CHANNELS IN TUỔI TRẺ");
            for (int i = 0; i < myUrlAddress1.length; i++) {
                myUrlAddress1[i] = myUrlCaptionMenu1[i][0];
                myUrlCaption1[i] = myUrlCaptionMenu1[i][1];
            }
            context = getApplicationContext();
            //this.setTitle("Vnexpress Headline News\n" + niceDate());
            // user will tap on a ListView’s row to request category’s headlines
            myMainListView = (ListView) this.findViewById(R.id.myListView);
            myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> _av, View _v, int _index, long _id) {
                    String urlAddress = myUrlAddress1[_index], urlCaption = myUrlCaption1[_index];
                    //create an Intent to talk to activity: ShowHeadlines
                    Intent callShowHeadlines = new Intent(MainActivity.this, ShowHeadlines.class);
                    //prepare a Bundle and add the input arguments: url & caption
                    Bundle myData = new Bundle();
                    myData.putString("urlAddress", urlAddress);
                    myData.putString("urlCaption", urlCaption);
                    myData.putString("Channel", "TUỔI TRẺ");
                    callShowHeadlines.putExtras(myData);
                    startActivity(callShowHeadlines);
                }
            });
            // fill up the Main-GUI’s ListView with main news categories
            adapterMainSubjects = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myUrlCaption1);
            myMainListView.setAdapter(adapterMainSubjects);
        }
        else if(val1 == 2){
            channel.setText("CHANNELS IN THANH NIÊN");
            for (int i = 0; i < myUrlAddress2.length; i++) {
                myUrlAddress2[i] = myUrlCaptionMenu2[i][0];
                myUrlCaption2[i] = myUrlCaptionMenu2[i][1];
            }
            context = getApplicationContext();
            //this.setTitle("Vnexpress Headline News\n" + niceDate());
            // user will tap on a ListView’s row to request category’s headlines
            myMainListView = (ListView) this.findViewById(R.id.myListView);
            myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> _av, View _v, int _index, long _id) {
                    String urlAddress = myUrlAddress2[_index], urlCaption = myUrlCaption2[_index];
                    //create an Intent to talk to activity: ShowHeadlines
                    Intent callShowHeadlines = new Intent(MainActivity.this, ShowHeadlines.class);
                    //prepare a Bundle and add the input arguments: url & caption
                    Bundle myData = new Bundle();
                    myData.putString("urlAddress", urlAddress);
                    myData.putString("urlCaption", urlCaption);
                    myData.putString("Channel", "THANH NIÊN");
                    callShowHeadlines.putExtras(myData);
                    startActivity(callShowHeadlines);
                }
            });
            // fill up the Main-GUI’s ListView with main news categories
            adapterMainSubjects = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myUrlCaption2);
            myMainListView.setAdapter(adapterMainSubjects);
        }
        else{
            channel.setText("CHANNELS IN VIETNAMNET");
            for (int i = 0; i < myUrlAddress3.length; i++) {
                myUrlAddress3[i] = myUrlCaptionMenu3[i][0];
                myUrlCaption3[i] = myUrlCaptionMenu3[i][1];
            }
            context = getApplicationContext();
            //this.setTitle("Vnexpress Headline News\n" + niceDate());
            // user will tap on a ListView’s row to request category’s headlines
            myMainListView = (ListView) this.findViewById(R.id.myListView);
            myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> _av, View _v, int _index, long _id) {
                    String urlAddress = myUrlAddress3[_index], urlCaption = myUrlCaption3[_index];
                    //create an Intent to talk to activity: ShowHeadlines
                    Intent callShowHeadlines = new Intent(MainActivity.this, ShowHeadlines.class);
                    //prepare a Bundle and add the input arguments: url & caption
                    Bundle myData = new Bundle();
                    myData.putString("urlAddress", urlAddress);
                    myData.putString("urlCaption", urlCaption);
                    myData.putString("Channel", "VIETNAMNET");
                    callShowHeadlines.putExtras(myData);
                    startActivity(callShowHeadlines);
                }
            });
            // fill up the Main-GUI’s ListView with main news categories
            adapterMainSubjects = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myUrlCaption3);
            myMainListView.setAdapter(adapterMainSubjects);
        }
    }//onCreate
} // MainActivity