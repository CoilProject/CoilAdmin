package com.gittest.cksrb.coiladmin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.gittest.cksrb.coiladmin.R;

public class PointPushDialog extends Dialog {

    private TextView mTitleView;
    //private TextView mContentView;
    private Button mLeftButton;
    private Button mRightButton;

    private String mTitle;
    private View.OnClickListener leftClickListener;
    private View.OnClickListener rightClickListener;

    public PointPushDialog(Context context,String mTitle,
                           View.OnClickListener leftClickListener,View.OnClickListener rightClickListener) {
        super(context);
        this.mTitle = mTitle;
        this.leftClickListener = leftClickListener;
        this.rightClickListener = rightClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_point_push_dialog);

        mTitleView = (TextView)findViewById(R.id.txt_title);
        //mContentView = (TextView) findViewById(R.id.txt_content);
        mLeftButton = (Button) findViewById(R.id.btn_left);
        mRightButton = (Button) findViewById(R.id.btn_right);

        // 제목과 내용을 생성자에서 셋팅한다.
        mTitleView.setText(mTitle);
        //mContentView.setText(mContent);

        //click이벤트 셋팅
        mLeftButton.setOnClickListener(leftClickListener);
        mRightButton.setOnClickListener(rightClickListener);
    }
}
