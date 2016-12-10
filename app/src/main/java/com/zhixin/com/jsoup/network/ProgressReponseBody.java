package com.zhixin.com.jsoup.network;

import com.zhixin.com.jsoup.rx.RxBus;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by zhangstar on 2016/12/9.
 */

public class ProgressReponseBody extends ResponseBody {
    private ResponseBody mResponseBody;
    private BufferedSource bufferedSource;

    public ProgressReponseBody(ResponseBody mResponseBody) {
        android.util.Log.e("test", "mResponseBody");
        this.mResponseBody = mResponseBody;
    }

    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(mResponseBody.source()));
        }

        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long bytesReaded = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                android.util.Log.e("test", "source");
                long bytesRead = super.read(sink, byteCount);
                bytesReaded += bytesRead == -1 ? 0 : bytesRead;
                android.util.Log.e("test", "body" + android.os.Process.myTid());
                RxBus.getInstance().post(new FileDownload(contentLength(), bytesReaded));
                return bytesRead;
            }
        };
    }


}
