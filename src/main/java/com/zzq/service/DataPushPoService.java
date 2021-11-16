package com.zzq.service;

import com.zzq.bean.DataPushPo;

import java.util.List;

public interface DataPushPoService {
    
     List<DataPushPo> getInfoByTableName(String tableName);
     
     boolean ifPush(String tableName,String provId);
    
}
