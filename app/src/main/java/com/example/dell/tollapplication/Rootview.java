package com.example.dell.tollapplication;

import java.io.Serializable;

/**
 * Created by dell on 12-Feb-18.
 */
public class Rootview implements Serializable{
    String rid;
    String source;
    String destination;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    String name;
}
