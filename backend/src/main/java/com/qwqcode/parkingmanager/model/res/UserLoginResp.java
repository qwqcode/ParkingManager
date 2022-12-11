package com.qwqcode.parkingmanager.model.res;

import com.qwqcode.parkingmanager.entity.User;
import com.qwqcode.parkingmanager.entity.VipCard;

public class UserLoginResp {
    private User user;

    private VipCard vip_card;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VipCard getVip_card() {
        return vip_card;
    }

    public void setVip_card(VipCard vip_card) {
        this.vip_card = vip_card;
    }
}
