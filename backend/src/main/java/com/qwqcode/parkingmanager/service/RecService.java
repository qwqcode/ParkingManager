package com.qwqcode.parkingmanager.service;

import com.qwqcode.parkingmanager.entity.Rec;
import com.qwqcode.parkingmanager.mapper.RecMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecService {
    @Autowired
    private RecMapper recMapper;

    /** 新增停车记录 */
    public boolean createRec(Rec rec) {
        return recMapper.createRec(rec) != 0;
    }

    /** 记录出停车场时间 */
    public boolean updateRecOutTime(Rec rec) {
        return recMapper.updateRecOutTime(rec.getId()) != 0;
    }
}
