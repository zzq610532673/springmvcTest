package com.zzq.serviceImpl;


import com.zzq.bean.DataPushPo;
import com.zzq.service.DataPushPoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class DataPushPoServiceImplTest {
    @Autowired
    private DataPushPoService dataPushPoService;

    @Test
    public void getInfoByTableName() {
        List<DataPushPo> info = dataPushPoService.getInfoByTableName("DWA_V_M_CUS_CBB_MAX_SPEED");
        for (DataPushPo dataPushPo : info) {
            System.out.println(dataPushPo.toString());
        }
    }

    @Test
    public void ifPush() {
        boolean ifPush = dataPushPoService.ifPush("DWA_V_M_CUS_CBB_MAX_SPEED", "099");
        System.out.println(ifPush);
    }
}