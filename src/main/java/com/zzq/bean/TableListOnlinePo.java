package com.zzq.bean;

public class TableListOnlinePo {
    private String table_name;
    private String if_needs_prov;
    private String need_date_prov;

    public String getTable_name() {
        return table_name;
    }

    public TableListOnlinePo setTable_name(String table_name) {
        this.table_name = table_name;
        return this;
    }

    public String getIf_needs_prov() {
        return if_needs_prov;
    }

    public TableListOnlinePo setIf_needs_prov(String if_needs_prov) {
        this.if_needs_prov = if_needs_prov;
        return this;
    }

    public String getNeed_date_prov() {
        return need_date_prov;
    }

    public TableListOnlinePo setNeed_date_prov(String need_date_prov) {
        this.need_date_prov = need_date_prov;
        return this;
    }

    @Override
    public String toString() {
        return "TableListOnlinePo{" +
                "table_name='" + table_name + '\'' +
                ", if_needs_prov='" + if_needs_prov + '\'' +
                ", need_date_prov='" + need_date_prov + '\'' +
                '}';
    }
}
