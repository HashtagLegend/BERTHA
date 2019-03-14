package com.example.bertha.Model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SendData implements Serializable {

    private Timestamp time;
    private Data data;


    public SendData(Timestamp time, Data data) {
        this.time = time;
        this.data = data;
    }
}
