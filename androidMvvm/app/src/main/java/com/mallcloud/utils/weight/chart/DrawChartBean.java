package com.mallcloud.utils.weight.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xzk
 * @data 2019/6/13
 * @email xiezhengkun@beyondsoft.com
 * @remark  图标实体类
 */
public class DrawChartBean {
    //当日总用量
    private double degree;
    //当日总费用
    private double amount;
    //下标时间
    private String xtime;

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getXtime() {
        return xtime;
    }

    public void setXtime(String xtime) {
        this.xtime = xtime;
    }

    public DrawChartBean() {
    }

    public DrawChartBean(double degree, double amount, String xtime) {
        this.degree = degree;
        this.amount = amount;
        this.xtime = xtime;
    }
//
//    public void mergeElectricity(HomePageDataBean.DataBean.ElectricityTotalDataBean.DeviceVoListBean listBean){
//        this.amount=listBean.getAmount();
//        this.degree=listBean.getDegree();
//        this.xtime=listBean.getXtime();
//    }
//
//    public void mergeWater(HomePageDataBean.DataBean.WaterTotalDataBean.DeviceVoListBeanX listBean){
//        this.amount=listBean.getAmount();
//        this.degree=listBean.getDegree();
//        this.xtime=listBean.getXtime();
//    }
//
//    public static List<DrawChartBean> buildWaiterData30(){
//        List<DrawChartBean> list = new ArrayList<>();
//        String string = DateUtil.getStringForMillis(System.currentTimeMillis(), DateUtil.DATE_YMD);
//        for (int i = 0; i < 30; i++) {
//            list.add(new DrawChartBean(Math.random() * 100, Math.random() * 100, string));
//        }
//        return list;
//    }
//
//    public static List<DrawChartBean> buildWaiterData30For(List<Double> entryList){
//        List<DrawChartBean> list = new ArrayList<>();
//        String string = DateUtil.getStringForMillis(System.currentTimeMillis(), DateUtil.DATE_YMD);
//        for (int i = 0; i < entryList.size(); i++) {
//            Double integer = entryList.get(i);
//            list.add(new DrawChartBean( integer, integer,string));
//        }
//        return list;
//    }
//
//    public static List<DrawChartBean> buildElectryData30(){
//        List<DrawChartBean> list = new ArrayList<>();
//        String string = DateUtil.getStringForMillis(System.currentTimeMillis(), DateUtil.DATE_YMD);
//        for (int i = 0; i < 30; i++) {
//            list.add(new DrawChartBean(Math.random() * 100, Math.random() * 100, string));
//        }
//        return list;
//    }

}
