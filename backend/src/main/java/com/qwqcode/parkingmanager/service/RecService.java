package com.qwqcode.parkingmanager.service;

import com.qwqcode.parkingmanager.entity.Rec;
import com.qwqcode.parkingmanager.entity.RecPay;
import com.qwqcode.parkingmanager.mapper.RecMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecService {
    @Autowired
    private RecMapper recMapper;

    /** 查询停车记录 */
    public Rec findRecByID(int rec_id) {
        return recMapper.findRecByID(rec_id);
    }

    /** 新增停车记录 */
    public boolean createRec(Rec rec) {
        return recMapper.createRec(rec) != 0;
    }

    /** 记录出停车场时间 */
    public boolean updateRecOutTime(Rec rec) {
        return recMapper.updateRecOutTime(rec.getId()) != 0;
    }

    /** 更新缴费 ID */
    public boolean updateRecPayID(Rec rec, RecPay recPay) {
        return recMapper.updateRecPayID(rec.getId(), recPay.getId()) != 0;
    }

    public Rec findLatestRecByCarID(int car_id) {
        return recMapper.findLatestRecByCarID(car_id);
    }

    /** 获取停车记录列表（分页）*/
    public List<Rec> findAllRecs(int offset, int limit) {
        return recMapper.findAllRecs(offset, limit);
    }

    /** 获取数据表中的所有停车记录总数 */
    public int countAllRecs() {
        return recMapper.countAllRecs();
    }

    /** 获取用户停车记录列表（分页）*/
    public List<Rec> findUserRecs(int user_id, int offset, int limit) {
        return recMapper.findUserRecs(user_id, offset, limit);
    }

    /** 获取用户停车记录总数 */
    public int countUserRecs(int user_id) {
        return recMapper.countUserRecs(user_id);
    }

    /** 获取指定车辆所有停车记录（分页）*/
    public List<Rec> findCarRecs(int car_id, int offset, int limit) {
        return recMapper.findCarRecs(car_id, offset, limit);
    }

    /** 获取指定车辆所有停车记录总数 */
    public int countCarRecs(int car_id) {
        return recMapper.countCarRecs(car_id);
    }

    /** 创建支付记录 */
    public boolean createRecPay(RecPay pay) {
        return recMapper.createRecPay(pay) != 0;
    }
}
