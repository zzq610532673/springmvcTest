package com.zzq.serviceImpl;

import com.zzq.aop.dataSourceAop.DataSource;
import com.zzq.bean.DataPushPo;
import com.zzq.mapper.DataPushPoMapper;
import com.zzq.service.DataPushPoService;
import com.zzq.utils.DataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataPushPoServiceImpl implements DataPushPoService {
    
    @Autowired
    private DataPushPoMapper dataPushPoMapper;

        @DataSource("dataSourceTwo")
        @Override
        public List<DataPushPo> getInfoByTableName(String tableName) {
            //DataSourceContextHolder.setCustomerType("dataSourceTwo");
            List<DataPushPo> info = dataPushPoMapper.getInfoByTable(tableName);
            return info;
        }

    
    @Override
    public boolean ifPush(String tableName, String provId) {
        DataSourceContextHolder.setCustomerType("dataSourceTwo");
        DataPushPo dataPushPo = dataPushPoMapper.isPush(tableName, provId);
        if (dataPushPo == null) {
            return false;
        }
        String if_push = dataPushPo.getIf_push();
        if (if_push.equals("1")) {
            return true;
        }
        return false;
    }
}