package com.k_beta.android.app.data;

/**
 * @author: Kevin Dong
 * @date:2016/4/5
 * @email:dongkai@nodescm.com
 */
public class MyResponse {

    public static final int CODE_NETWORK_ERROR = Integer.MIN_VALUE;
    public static final int CODE_UNKNOWN_ERROR = Integer.MAX_VALUE;
    public static final int CODE_OK = 1;
    public static final int CODE_ERROR = 0;

    private int code;

    private String msg;

    private String datas;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String content) {
        this.datas = content;
    }
}
