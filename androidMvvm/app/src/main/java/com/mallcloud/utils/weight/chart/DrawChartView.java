package com.mallcloud.utils.weight.chart;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mallcloud.androidmvvm.R;
import com.mallcloud.utils.common.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xzk
 * @data 2019/4/12
 * @email xiezhengkun@beyondsoft.com
 * @remark  能耗view封装类
 */
public class DrawChartView extends LinearLayout {

    private DrawChart lineChartEnergy;
    private TextView txtEnergyTitle;
    private View viewToplan;
    /**
     * 默认生成7天的折线数据
     */
    private int mostDay = 7;

    public DrawChartView(Context context) {
        super(context);
        initView(context);
    }
    public DrawChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    public DrawChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(Context context){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.util_chart_energy, this);
        initTyped();
    }

    private void initTyped() {
        txtEnergyTitle = findViewById(R.id.txt_energy_title);
        lineChartEnergy = findViewById(R.id.line_chart_energy);
        viewToplan = findViewById(R.id.view_toplan);
    }

    public void setData(List<DrawChartBean> drawChartList, String name,
                        SpannableString unit, String color, boolean showLan, Integer mostDay){
        if(mostDay !=null){
            this.mostDay = mostDay;
        }
        if(showLan){
            viewToplan.setVisibility(VISIBLE);
        }else {
            viewToplan.setVisibility(GONE);
        }
        txtEnergyTitle.setText(unit);
        txtEnergyTitle.setTextColor(Color.parseColor(color));
//        txtEnergyTitle.setText(String.format(getResources().getString(R.string.str_energy_type),name,unit));
        List<Double> points=create7Amount(drawChartList);

        List<String> date = create7Date(drawChartList);
        lineChartEnergy.setdateArr(date);
        lineChartEnergy.setPoints(points);
        lineChartEnergy.setLineColor(color);
        //重新绘制
        lineChartEnergy.invalidate();
    }

    /**
     * 处理成数据最少7天，最多不限制的数据
     *
     * @param drawChartList
     * @return
     */
    private List<Double> create7Amount(List<DrawChartBean> drawChartList) {
        List<Double> doubleList = new ArrayList<>();
        if(drawChartList != null){
            for (int i = (drawChartList.size()-1); i >-1; i--) {
                DrawChartBean chartBean = drawChartList.get(i);
                doubleList.add(0,chartBean.getDegree());
            }
        }
        if(doubleList.size()<mostDay){
            int length=mostDay-doubleList.size();
            for (int i = 0; i < length; i++) {
                doubleList.add(0,0.00);
            }
        }
        return doubleList;
    }

    /**
     * 将时间，按照最近总时间的量进行触发，
     * 近7天，30天，180天等，处理成7个大致相对的时间
     * @param drawChartList
     * @return
     */
    public List<String> create7Date(List<DrawChartBean> drawChartList){
        int mouthSize = mostDay;
        int moreDistance=mouthSize/7;
        if(moreDistance <1){
            moreDistance = 1;
        }
        List<String> dateArr=new ArrayList<>();
        long oneDay=24*60*60*1000;
        //起始时间为昨天
        long lastMillis= System.currentTimeMillis() - oneDay;
        if(drawChartList != null){
            lastMillis= DateUtil.parseServerTime(drawChartList.get(drawChartList.size() - 1).getXtime(), DateUtil.DATE_YMD).getTime();
        }
        //增加一个相隔单位的刻度，实现，如果是7填，30天，3年的时候，7个下标的距离
        if(mostDay ==30){
            moreDistance+=1;
        }
        oneDay *= (moreDistance);
        for (int i = 0; i < 7; i++) {
            long times=lastMillis-oneDay*i;
            dateArr.add(0, DateUtil.getStringForMillis(times,DateUtil.DATE_MD));
        }
        return dateArr;
    }

}
