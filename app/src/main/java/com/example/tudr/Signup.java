package com.example.tudr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

import cz.msebera.android.httpclient.Header;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView name = findViewById(R.id.name);
                TextView pass = findViewById(R.id.password);
                TextView role = findViewById(R.id.role);

                RequestParams params = new RequestParams();

                params.put("name", name.getText());
                params.put("password", pass.getText());
                params.put("role", role.getText());

                LocalServerClient.post("server.php", params , new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        System.out.println("--------()()()()()(_+_+#+@#$)+@#%(@#+%(@#$+)!#$+)!@#%+)!@#$+!#$+)!#$}+)!#$}+)!)+}");
                        try {
                            int id = response.getInt("id");
                            String name = response.getString("name");
                            int role = response.getInt("role");
                            SharedPreferences prefs = getSharedPreferences("preferences", MODE_PRIVATE);
                            SharedPreferences.Editor prefEdit = prefs.edit();
                            prefEdit.putInt("USER_ID", id);
                            prefEdit.putString("USER_NAME", name);
                            prefEdit.putInt("USER_ROLE", role);
                            prefEdit.apply();
                            Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            System.out.println("oops");
                        }
                        System.out.println("--------()()()()()(_+_+#+@#$)+@#%(@#+%(@#$+)!#$+)!@#%+)!@#$+!#$+)!#$}+)!#$}+)!)+}");
                    }
                });
            }
        });
    }
}
