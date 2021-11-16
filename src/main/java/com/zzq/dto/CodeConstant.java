package com.zzq.dto;

public enum CodeConstant {
    SUCCESS("成功", 200),
    FAIL("失败",404);

    private String msg;
    private int index;


    CodeConstant(String msg, int index) {
        this.msg = msg;
        this.index = index;
    }

    public String getMsg() {
        return msg;
    }

    public CodeConstant setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public CodeConstant setIndex(int index) {
        this.index = index;
        return this;
    }
}
