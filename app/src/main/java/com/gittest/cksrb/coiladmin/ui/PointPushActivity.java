package com.gittest.cksrb.coiladmin.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gittest.cksrb.coiladmin.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PointPushActivity extends Activity {
    private EditText editText_add_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_point_push);


        Intent intent = getIntent();

        final TextView txt_title = (TextView)findViewById(R.id.txt_title);
        txt_title.setText(intent.getExtras().getString("push_id"));

        TextView text_current_point=(TextView)findViewById(R.id.text_current_point);
        text_current_point.setText(intent.getExtras().getInt("current_point")+"");


        editText_add_point = (EditText)findViewById(R.id.editText_add_point);
        //editText_add_point.getText();

        Button button_left = (Button)findViewById(R.id.button_left);
        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //포인트 발급
                //서버에 저장할 포인트

                JSONObject pointObj = new JSONObject();
                try {
                    pointObj.put("user_id",txt_title.getText());
                    pointObj.put("add_point",Integer.parseInt(editText_add_point.getText().toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "포인트를 입력하여 주세요.", Toast.LENGTH_SHORT).show();
                }

                /*요기도 url이 없어요ㅠ
                JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.POST,
                        SystemMain.URL.URL_ADD_POINT,
                        pointObj,
                        networkSuccessListener(),
                        networkErrorListener());

                final RequestQueue queue = MyVolley.getInstance(getApplicationContext()).getRequestQueue();
                queue.add(myReq);
*/
                //+푸쉬 요청메시지====success 에다가 넣어버릴까

                //요기부터 테스트
                Toast.makeText(getApplicationContext(), "포인트 발급 완료!", Toast.LENGTH_SHORT).show();
                finish();
                //요기까지
            }
        });

        Button button_right = (Button)findViewById(R.id.button_right);
        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private Response.Listener<JSONObject> networkSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "포인트 발급 완료!", Toast.LENGTH_SHORT).show();
                //다이얼로그 종료
                finish();
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
