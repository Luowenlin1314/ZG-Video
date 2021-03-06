package com.zom.zg.video.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 作者：Sky on 2018/3/5.
 * 用途：基类
 */

public abstract class BaseActivity extends Activity {

    protected static final int DEFAULT_LOADING_TIME = 2000;
    protected static final int WHAT_LOAD = 1;
    protected static final int WHAT_LOAD_SUCCESS = 2;
    protected static final int WHAT_LOAD_FAIL = 2;
    protected static final int WHAT_REFRESH = 3;

    protected ActivityFragmentInject annotation;
    protected Context baseContext;

    protected Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            toHandleMessage(msg);
        }
    };

    /**
     * handler消息处理
     * @param msg
     */
    protected abstract void toHandleMessage(Message msg);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
            throw new RuntimeException("must use ActivityFragmentInitParams.class");
        }
        annotation = getClass().getAnnotation(ActivityFragmentInject.class);
        setContentView(annotation.contentViewId());
        baseContext = this;
        findViewAfterViewCreate();
        initDataAfterFindView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * view初始化
     */
    protected abstract void findViewAfterViewCreate();

    /**
     * 数据初始化
     */
    protected abstract void initDataAfterFindView();

}
