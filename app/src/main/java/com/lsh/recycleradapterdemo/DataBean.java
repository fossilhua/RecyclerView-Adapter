package com.lsh.recycleradapterdemo;

/**
 * Created by hua on 2016/6/23.
 */
public class DataBean {
    private String icon;
    private String des;

    public DataBean(String icon, String des) {
        this.icon = icon;
        this.des = des;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
