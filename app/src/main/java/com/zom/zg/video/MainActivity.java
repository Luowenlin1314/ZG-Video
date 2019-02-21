package com.zom.zg.video;

import android.graphics.Color;
import android.os.Message;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zom.zg.video.base.ActivityFragmentInject;
import com.zom.zg.video.base.BaseActivity;

@ActivityFragmentInject(
        contentViewId = R.layout.activity_main,
        hasNavigationView = false,
        hasToolbar = false
)
public class MainActivity extends BaseActivity {

    private PercentRelativeLayout percentRelativeLayout;

    @Override
    protected void toHandleMessage(Message msg) {

    }

    @Override
    protected void findViewAfterViewCreate() {

    }

    @Override
    protected void initDataAfterFindView() {
        initView();
    }

    private void initView(){
        percentRelativeLayout = (PercentRelativeLayout) findViewById(R.id.root);

        ImageView view = new ImageView(this);
        view.setBackgroundColor(Color.BLACK);
        PercentRelativeLayout.LayoutParams params = new PercentRelativeLayout.LayoutParams(0,0);
        PercentLayoutHelper.PercentLayoutInfo percentLayoutInfo = params.getPercentLayoutInfo();
        percentLayoutInfo.widthPercent = 0.25f;
        percentLayoutInfo.heightPercent = 0.25f;
        percentLayoutInfo.topMarginPercent = 0f;
        percentLayoutInfo.leftMarginPercent = 0f;
        percentRelativeLayout.addView(view,params);

        ImageView view2 = new ImageView(this);
        view2.setBackgroundColor(Color.BLUE);
        PercentRelativeLayout.LayoutParams params2 = new PercentRelativeLayout.LayoutParams(0,0);
        PercentLayoutHelper.PercentLayoutInfo percentLayoutInfo2 = params2.getPercentLayoutInfo();
        percentLayoutInfo2.widthPercent = 0.25f;
        percentLayoutInfo2.heightPercent = 0.25f;
        percentLayoutInfo2.topMarginPercent = 0.75f;
        percentLayoutInfo2.leftMarginPercent = 0.75f;
        percentRelativeLayout.addView(view2,params2);

        ImageView view3 = new ImageView(this);
        view3.setBackgroundColor(Color.RED);
        PercentRelativeLayout.LayoutParams params3 = new PercentRelativeLayout.LayoutParams(0,0);
        PercentLayoutHelper.PercentLayoutInfo percentLayoutInfo3 = params3.getPercentLayoutInfo();
        percentLayoutInfo3.widthPercent = 0.25f;
        percentLayoutInfo3.heightPercent = 0.25f;
        percentLayoutInfo3.topMarginPercent = 0.375f;
        percentLayoutInfo3.leftMarginPercent = 0.375f;
        percentRelativeLayout.addView(view3,params3);

    }

}
