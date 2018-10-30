package com.nuaa.compliation.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 */
public class Grammar {
    /**
     * vT:终结符集合
     * vN:非终结符集合
     * s：开始符
     * p:推导式集合
     */
    List<String> vT;
    List<String> vN;
    String s;

    List<String> p;

    public Grammar() {
        vT = new ArrayList<>();
        vN = new ArrayList<>();
        p = new ArrayList<>();
    }


    public List<String> getvT() {
        return vT;
    }

    public void setvT(List<String> vT) {
        this.vT = vT;
    }

    public List<String> getvN() {
        return vN;
    }

    public void setvN(List<String> vN) {
        this.vN = vN;
    }

    public String getS() {
        return s;
    }

    public List<String> getP() {
        return p;
    }

    public void setP(List<String> p) {
        this.p = p;
    }

    public void setS(String s) {
        this.s = s;
    }

}
