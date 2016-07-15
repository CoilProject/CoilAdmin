package com.gittest.cksrb.coiladmin.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.gittest.cksrb.coiladmin.CoilAdminApplication;
import com.gittest.cksrb.coiladmin.R;

public class PointPushActivity extends Activity {
    private CoilAdminApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_point_push);

        TextView txt_title = (TextView)findViewById(R.id.txt_title);
        txt_title.setText(app.push_id);
        //txt_title.setText("HIHIHIIHI");
    }
}
