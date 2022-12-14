package com.qwqcode.parkingmanager.model.req;

public class AdminRecParams {
    private int offset = 0;
    private int limit = 15;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
