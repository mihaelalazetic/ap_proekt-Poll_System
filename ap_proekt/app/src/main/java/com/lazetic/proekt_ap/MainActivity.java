package com.lazetic.proekt_ap;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDb();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_fragment_container ,new MainFragment());
        fragmentTransaction.commit();
    }

    public void createDb(){

        db = openOrCreateDatabase("poll_system", MODE_PRIVATE, null);
        //TODO add location to db
        db.execSQL("CREATE TABLE IF NOT EXISTS user_details(id INTEGER primary key autoincrement , email VARCHAR,password VARCHAR(1000),location_name VARCHAR);");
        db.execSQL("INSERT INTO user_details(email,password,location_name) VALUES('test@email.com','123','Poll Place1')");
        db.execSQL("INSERT INTO user_details(email,password,location_name) VALUES('user2@yahoo.com','P@ss','Poll Place2')");

        db.execSQL("CREATE TABLE IF NOT EXISTS locations(id INTEGER primary key autoincrement , lat VARCHAR,long VARCHAR,location_name VARCHAR);");

        db.execSQL("CREATE TABLE IF NOT EXISTS polls(poll_name VARCHAR, q1 VARCHAR,a1 VARCHAR, q2 VARCHAR,a2 VARCHAR, q3 VARCHAR,a3 VARCHAR);");
        db.execSQL("INSERT INTO polls(poll_name,q1,a1,q2,a2,q3,a3) VALUES('Default Poll'," +
                "'What’s the best pizza topping?','Pepperoni;Pineapple;Just cheese;Mushrooms'," +
                "'What’s the cutest animal?','Kittens;Puppies;Rabbits;'," +
                "'What was the best series?','Breaking Bad;The Sopranos;Mr. Robot')");
        db.execSQL("INSERT INTO polls(poll_name,q1,a1,q2,a2,q3,a3) VALUES('Poll1'," +
                "'q1','Pepperoni;Pineapple;Just cheese;Mushrooms'," +
                "'q2','Kittens;Puppies;Rabbits;'," +
                "'q3','Breaking Bad;The Sopranos;Mr. Robot')");
        db.execSQL("CREATE TABLE IF NOT EXISTS poll_logs(poll_name VARCHAR , active VARCHAR CHECK (active IN ('0', '1')));");
        db.execSQL("INSERT INTO poll_logs(poll_name,active) VALUES('Default Poll','1');");
    }
    public ArrayList<String> database_get_pollList(View view) {
        ArrayList<String> pollNames = new ArrayList<>();
        int i=0;
        Cursor cursor  = db.rawQuery("SELECT poll_name from polls",null);
        cursor.moveToFirst();
        do {
            pollNames.add(cursor.getString(cursor.getColumnIndex("poll_name")));
            System.out.println("pollNames: " + pollNames.get(i));
            i++;
        } while (cursor.moveToNext());
        cursor.close();
        return pollNames;
    }

}