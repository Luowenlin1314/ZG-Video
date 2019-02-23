package com.zom.zg.video;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.zom.zg.video.base.ActivityFragmentInject;
import com.zom.zg.video.base.BaseActivity;
import com.zom.zg.video.util.MediaFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ActivityFragmentInject(
        contentViewId = R.layout.activity_main,
        hasNavigationView = false,
        hasToolbar = false
)
public class MainActivity extends BaseActivity {

    private String MATERIAL_PATH = "/zvideo";
    private VideoView videoView;
    private ImageView imageView;
    private int playIndex = 0;
    private List<String> filePaths = new ArrayList<>();

    @Override
    protected void toHandleMessage(Message msg) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState, persistentState);
        Log.e("MainActivity","onCreate");
    }

    @Override
    protected void findViewAfterViewCreate() {
        videoView = (VideoView) findViewById(R.id.videoViw);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    protected void initDataAfterFindView() {
        onComplete();

    }

    /**
     * 获取视频文件列表
     */
    private void getFileList(){
        String localPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/zvideo";
        File dirFile = new File(localPath);
        if(dirFile.isDirectory()){
            File[] files = dirFile.listFiles();
            if(files != null && files.length > 0){
                for (File file : files) {
                    MediaFile.MediaFileType mediaFileType = MediaFile.getFileType(file.getAbsolutePath());
                    if(MediaFile.isVideoFileType(mediaFileType.fileType)){
                        filePaths.add(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    private void onComplete(){
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playIndex++;
                if(playIndex > (filePaths.size() - 1)){
                    playIndex = 0;
                }
                videoView.setVideoPath(filePaths.get(playIndex));
                videoView.start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity","onPause");
        if(videoView.isPlaying()){
            videoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity","onResume");
        getFileList();
        if(filePaths.size() == 0){
            imageView.setVisibility(View.VISIBLE);
            return;
        }
        playIndex = 0;
        imageView.setVisibility(View.GONE);
        videoView.setVideoPath(filePaths.get(playIndex));
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();
    }
}
