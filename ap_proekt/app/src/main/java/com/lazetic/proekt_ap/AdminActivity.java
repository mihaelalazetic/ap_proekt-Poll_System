package com.lazetic.proekt_ap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.admin_fragment_container ,new StartPollFragment());
        fragmentTransaction.commit();
    }

    public ArrayList<String> getActivePolls(View view) {
        db = openOrCreateDatabase("poll_system", MODE_PRIVATE, null);
        ArrayList<String> activePolls = new ArrayList<>();
        int i=0;
        Cursor cursor  = db.rawQuery("SELECT * from poll_logs",null);
        cursor.moveToFirst();
        do {
            activePolls.add(cursor.getString(cursor.getColumnIndex("poll_name")));
            System.out.println("activePolls: " + activePolls.toString());
            i++;
        } while (cursor.moveToNext());
        cursor.close();
        return activePolls;
    }

    public void insertInDB(String sql){
        db = openOrCreateDatabase("poll_system", MODE_PRIVATE, null);
        db.execSQL(sql);
    }
}