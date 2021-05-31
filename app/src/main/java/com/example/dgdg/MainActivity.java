package com.example.dgdg;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.database.sqlite.SQLiteDatabase; //내부 db
import static android.content.ContentValues.TAG;


import com.example.dgdg.data.DBHelper;
import com.example.dgdg.ui.mission.MissionActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.http.DELETE;

public class MainActivity extends AppCompatActivity {

    public static Context context_main;
    public ArrayList<String> username=new ArrayList<>();
    public  ArrayList<String> userrecord=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.



        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("DGDG");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);



        DBHelper helper;
        SQLiteDatabase db;
        helper = new DBHelper(MainActivity.this, "newdb2.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        db.execSQL("DELETE FROM newtable2");



        String sql = "INSERT INTO newtable2(UserName,record) values('김미소',20);";
        db.execSQL(sql);

        String sql2 = "INSERT INTO newtable2(UserName,Record) values('이가경',30);";
        db.execSQL(sql2);



        Cursor c = db.query("newtable2",null,null,null,null,null,null,null);
        while(c.moveToNext()){
            System.out.println("username : "+c.getString(c.getColumnIndex("UserName"))+"record :"+c.getString(c.getColumnIndex("Record")));
            username.add(c.getString(c.getColumnIndex("UserName")));
            userrecord.add(c.getString(c.getColumnIndex("Record")));
        }

        context_main = this;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mission_menu:
                Intent missionActivity = new Intent(getApplicationContext(), MissionActivity.class);
                startActivity(missionActivity);
                break;
        }

        return true;
        //return super.onOptionsItemSelected(item);
    }

}