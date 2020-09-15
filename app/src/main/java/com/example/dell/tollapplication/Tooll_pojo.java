package com.example.dell.tollapplication;

import java.io.Serializable;

/**
 * Created by dell on 12-Feb-18.
 */
public class Tooll_pojo implements Serializable{
    String toollname,tid,rid,tow,fw,tw;

    public String getToollname() {
        return toollname;
    }

    public void setToollname(String toollname) {
        this.toollname = toollname;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getTow() {
        return tow;
    }

    public void setTow(String tow) {
        this.tow = tow;
    }

    public String getFw() {
        return fw;
    }

    public void setFw(String fw) {
        this.fw = fw;
    }

    public String getTw() {
        return tw;
    }

    public void setTw(String tw) {
        this.tw = tw;
    }
}
