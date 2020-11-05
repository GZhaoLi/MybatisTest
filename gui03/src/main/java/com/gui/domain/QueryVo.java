package com.gui.domain;

import java.util.List;

public class QueryVo {
    private User user;
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QueryVo(User user, List<Integer> ids) {
        this.user = user;
        this.ids = ids;
    }

    public QueryVo() {
    }

    public QueryVo(User user) {
        this.user = user;
    }
}
