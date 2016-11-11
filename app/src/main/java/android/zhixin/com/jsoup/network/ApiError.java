package android.zhixin.com.jsoup.network;

/**
 * Created by admin on 2016/8/26.
 */
public class ApiError {
    private int code;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
