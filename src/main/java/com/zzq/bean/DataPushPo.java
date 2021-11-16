package com.zzq.bean;

public class DataPushPo {
    
    private String table_name;
    private String prov_id;
    private String if_push;
    private String push_time;
    private String cycle;

    public String getTableName() {
        return table_name;
    }

    public DataPushPo setTableName(String tableName) {
        this.table_name = tableName;
        return this;
    }

    public String getProv_id() {
        return prov_id;
    }

    public DataPushPo setProv_id(String prov_id) {
        this.prov_id = prov_id;
        return this;
    }

    public String getIf_push() {
        return if_push;
    }

    public DataPushPo setIf_push(String if_push) {
        this.if_push = if_push;
        return this;
    }

    public String getPush_time() {
        return push_time;
    }

    public DataPushPo setPush_time(String push_time) {
        this.push_time = push_time;
        return this;
    }

    public String getCycle() {
        return cycle;
    }

    public DataPushPo setCycle(String cycle) {
        this.cycle = cycle;
        return this;
    }

    @Override
    public String toString() {
        return "DataPushPo{" +
                "tableName='" + table_name + '\'' +
                ", prov_id='" + prov_id + '\'' +
                ", if_push='" + if_push + '\'' +
                ", push_time='" + push_time + '\'' +
                ", cycle='" + cycle + '\'' +
                '}';
    }
}
