package com.gittest.cksrb.coiladmin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gittest.cksrb.coiladmin.CoilAdminApplication;
import com.gittest.cksrb.coiladmin.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ShopActivity extends AppCompatActivity {

    private CoilAdminApplication app;

    private TextView textview_id;
    private EditText textview_user_id;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativelayout);

        app = new CoilAdminApplication();
        textview_user_id =(EditText) findViewById(R.id.textview_user_id);

        ImageButton imagebutton = (ImageButton)findViewById(R.id.imagebutton);
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //포인트 발급화면
                intent = new Intent(getApplicationContext(),PointPushActivity.class);
                intent.putExtra("push_id",textview_user_id.getText().toString());

                //포인트 점수 받아오는 작업 추가
                JSONObject obj = new JSONObject();
                try {
                    obj.put("user_id",textview_user_id.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                /* 아직 url이 없어요...
                JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.POST,
                        SystemMain.URL.URL_ADD_POINT,
                        obj,
                        networkSuccessListener(),
                        networkErrorListener());

                final RequestQueue queue = MyVolley.getInstance(getApplicationContext()).getRequestQueue();
                queue.add(myReq);
                */

                //

                //요기는 테스트용
                int user_current_point=100;
                intent.putExtra("current_point",user_current_point);
                startActivity(intent);
                //요기까지
            }
        });


        //원래 있던거....
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private Response.Listener<JSONObject> networkSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    intent.putExtra("current_point",response.getInt("user_point"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        };
    }
    private Response.ErrorListener networkErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), R.string.volley_network_fail, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
