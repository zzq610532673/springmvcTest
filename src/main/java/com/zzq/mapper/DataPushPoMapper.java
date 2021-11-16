package com.zzq.mapper;

import com.zzq.bean.DataPushPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataPushPoMapper {
    
    List<DataPushPo> getInfoByTable(@Param("tableName") String tableName);
    
    DataPushPo isPush(@Param("tableName") String tableName, @Param("provId") String provId);
    
}
