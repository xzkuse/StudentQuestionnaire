package com.mallcloud.rfiddemo_c.Tags;
/*
 * EPC+TID tags
 * Created by skye
 * on 2018/09/03
 */
public class TIDTags {
    private int Number;
    private String EPC;
    private String TID;
    private int Count;

    public TIDTags(int number, String EPC, String TID, int count) {
        Number = number;
        this.EPC = EPC;
        this.TID = TID;
        Count = count;
    }

    public void setNumber(int number) {
        this.Number = number;
    }

    public void setEPC(String EPC) {
        this.EPC = EPC;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getNumber() {
        return Number;
    }

    public String getEPC() {
        return EPC;
    }

    public String getTID() {
        return TID;
    }

    public int getCount() {
        return Count;
    }
}
