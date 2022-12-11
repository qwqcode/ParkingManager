package com.qwqcode.parkingmanager.model.res;

import com.qwqcode.parkingmanager.entity.Rec;

import java.util.List;

public class UserCarRecsResp {
    private List<Rec> recs;
    private int total;

    public List<Rec> getRecs() {
        return recs;
    }

    public void setRecs(List<Rec> recs) {
        this.recs = recs;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
