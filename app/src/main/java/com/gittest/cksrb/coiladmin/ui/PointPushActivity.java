package com.gittest.cksrb.coiladmin.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.gittest.cksrb.coiladmin.CoilAdminApplication;
import com.gittest.cksrb.coiladmin.R;

public class PointPushActivity extends AppCompatActivity {
    private CoilAdminApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_point_push);

        //android:theme="@android:style/Theme.DeviceDefault.Light.Dialog"

        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        app = new CoilAdminApplication();

        TextView txt_title = (TextView)findViewById(R.id.txt_title);
        //Log.d("app.pushpush",app.push_id);
        txt_title.setText(app.push_id);
    }
}
