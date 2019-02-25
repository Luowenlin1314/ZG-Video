package com.zom.zg.video.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public interface CopyFileListener {
        public void copyProgress(long porgress, long max);

    }

    public static void copyFile(File sourceFile, File targetFile, CopyFileListener copyFileListener) throws IOException {

        if(targetFile.exists() && sourceFile.exists()){
            targetFile.delete();
        }
        long max = sourceFile.length();

        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            long porgress = 0;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
                porgress += len;
                if(copyFileListener != null){
                    copyFileListener.copyProgress(porgress, max);
                }
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }


    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }
}