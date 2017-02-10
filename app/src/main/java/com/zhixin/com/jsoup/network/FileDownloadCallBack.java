package com.zhixin.com.jsoup.network;

import android.util.Log;

import com.zhixin.com.jsoup.base.application.BaseApplication;
import com.zhixin.com.jsoup.rx.RxBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by zhangstar on 2016/12/9.
 */

public abstract class FileDownloadCallBack {
    int i = 1;

    public FileDownloadCallBack() {
        subscribe();
    }

    public abstract void updateProgress(long total, long progress);

    private void subscribe() {
        Subscription sub = RxBus.getInstance().doSubscribe(FileDownload.class, new Action1<FileDownload>() {
            @Override
            public void call(FileDownload fileDownload) {
                updateProgress(fileDownload.getTotal(), fileDownload.getBytesLoaded());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
            }
        });

        RxBus.getInstance().addSubscription(BaseApplication.getInstance(), sub);
    }

    private String destFileDir;
    private String destFileName;

    public void setPathAndName(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
    }

    public void saveFile(ResponseBody body) {
        Log.e("saveFile", "开始保存");
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            is = body.byteStream();
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            Log.e("saveFile", "保存结束");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("saveFile", e.getMessage());
        } catch (IOException e) {
            Log.e("saveFile", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                RxBus.getInstance().unSubscribe(BaseApplication.getInstance());
                if (is != null) is.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                Log.e("saveFile", e.getMessage());
            }
        }
    }
}
