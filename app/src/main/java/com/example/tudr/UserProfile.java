package com.example.tudr;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.*;

import java.util.ArrayList;

import cz.msebera.android.httpclient.entity.mime.Header;

import android.content.SharedPreferences;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        SharedPreferences prefs = getSharedPreferences("preferences", MODE_PRIVATE);
        int userId = prefs.getInt("USER_ID", 0);
        if (userId == 0) {
            logIn();
        }

        Button signout = findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               logOut();
           }
        });

        User usr = new User(userId);
        LinearLayout profile = findViewById(R.id.profile);
        if (usr.getRole() == 2) {
            LinearLayout classList = getTutoredClasses(usr);
            profile.addView(classList);
        } else if (usr.getRole() == 1) {

        }
        ImageView img = findViewById(R.id.imageView);
        TextView name = findViewById(R.id.name);
        name.setText("Tomer Singal");
        name.setTextSize(20);

        RequestParams params = new RequestParams();
        params.put("student_id", 5);
        params.put("course_num", "CSC335");
        params.put("location", "TCNJ lib");
        LocalServerClient.post("submit_request.php", params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }
        });

//        RequestParams params = new RequestParams();
//        params.put("key", "your name");
//        params.put("more", "data");
//        //------------------------------------------------------//
//        LocalServerClient.post("server.php", params , new JsonHttpResponseHandler() {
//
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                System.out.println("--SUCCESS--++++++++++++++++++++++++++++++++");
//            }
//        });
//        //------------------------------------------------------//
    }

    private LinearLayout getTutoredClasses(User user) {
        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linLayout.setOrientation(LinearLayout.VERTICAL);

        ArrayList<String> classes =  user.getClasses();
        ListView clist = new ListView(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, classes);
        clist.setAdapter(adapter);

        TextView view = new TextView(this);
        view.setText("Classes Tutoring");

        for (int i = 0; i < clist.getChildCount(); i++) {
            final String course = classes.get(i);
            System.out.println("=========================" + course);
            View v = clist.getChildAt(i);
            v.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (v instanceof TextView) {
                        RequestParams params = new RequestParams();
                        params.put("name", course);
                        LocalServerClient.post("getRequests.php", params, new JsonHttpResponseHandler() {
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                            }
                        });
                    }
                }
            });
        }

        linLayout.addView(view);
        linLayout.addView(clist);
        return linLayout;
    }

    private void logIn() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }

    private void logOut() {
        SharedPreferences prefs = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.clear();
        edit.apply();
        logIn();
    }

    private void getRequests(String courseName) {

    }
}
