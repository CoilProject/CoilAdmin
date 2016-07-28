package com.gittest.cksrb.coiladmin.util;

import android.content.Context;
import android.util.Log;

import com.gittest.cksrb.coiladmin.CoilAdminApplication;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cksrb on 2016. 7. 27..
 */
public class CoilAdminBuilder {

    private final String TAG = "CoilAdminBuilder";

    private final String ATTR_KEY_DEBUG_MODE = "debug_mode";
    private final String ATTR_KEY_BUILD_VERSION = "build_version";

    private JSONObject mRequestBody;
    private CoilAdminApplication app;

    public CoilAdminBuilder(){
        //Using PointPushActivity
    }

    public CoilAdminBuilder(Context context) {
        app = (CoilAdminApplication) context.getApplicationContext();
        mRequestBody = new JSONObject();
        try {
            mRequestBody.put(ATTR_KEY_DEBUG_MODE, app.debug_mode);
            mRequestBody.put(ATTR_KEY_BUILD_VERSION, app.version_code);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public CoilAdminBuilder setCustomAttribute(String key, Object value){
        try {
            mRequestBody.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void showInside(){
        Log.d(TAG, mRequestBody.toString());
    }

    public JSONObject build(){
        return this.mRequestBody;
    }
}
