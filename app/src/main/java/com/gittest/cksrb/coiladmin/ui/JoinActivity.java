package com.gittest.cksrb.coiladmin.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gittest.cksrb.coiladmin.R;
import com.gittest.cksrb.coiladmin.util.SystemMain;
import com.gittest.cksrb.coiladmin.volley.MyVolley;

import org.json.JSONException;
import org.json.JSONObject;

public class JoinActivity extends AppCompatActivity {

    private final String TAG = "JoinActivity";


    private JSONObject joinObj;

    //디버그,빌드정보
    private final String ATTR_KEY_DEBUG_MODE = "debug_mode";
    private final String ATTR_KEY_BUILD_VERSION = "build_version";

    // debug_mode
    private final boolean debug_mode = true;
    // version_code
    private final int version_code = 1;

    private String gcm_token = null;


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.join_email);
        //populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.join_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.join || id == EditorInfo.IME_NULL) {
                    attemptJoin();
                    return true;
                }
                return false;
            }
        });
    }

    private boolean attemptJoin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return false;
        } else {

            final RequestQueue queue = MyVolley.getInstance(getApplicationContext()).getRequestQueue();

            joinObj = new JSONObject();
            try {
                //디버그,빌드 정보
                joinObj.put(ATTR_KEY_DEBUG_MODE,debug_mode);
                joinObj.put(ATTR_KEY_BUILD_VERSION,version_code);

                //유저 정보
                joinObj.put("user_id", email);
                joinObj.put("user_pw",password);
                joinObj.put("gcm_token",gcm_token);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.POST,
                    SystemMain.URL.URL_JOIN,
                    joinObj,
                    networkSuccessListener(),
                    networkErrorListener());

            queue.add(myReq);

            return true;
        }
    }

    private boolean isEmailValid(String email) {

        if(!email.contains("010")||email.length()!=11)
            return false;

        return true;

        //return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public void doJoin(View v){
        attemptJoin();
    }
    private Response.Listener<JSONObject> networkSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    if(response.getBoolean("join")){
                        Toast.makeText(JoinActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(JoinActivity.this, response.getString("error_message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Intent intent  = new Intent(getApplicationContext(), MainActivity.class);

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
