package com.mallcloud.utils.common;

import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    /**
     * 判断当前手机系统语言是否是中文
     *
     * @return
     */
    public static boolean isZh() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else locale = Locale.getDefault();
        String language = locale.getLanguage() + "-" + locale.getCountry();
        if (language.contains("zh"))
            return true;
        else
            return false;
    }
    public static CharSequence formatTimeYearMonDayHourMin(long date) {
        Date dateFrom = new Date(date);
        DateFormat dateFormat = new DateFormat();
//        if(isZh()){
//            CharSequence s = dateFormat.format("yyyy-MM-d HH:mm", dateFrom);
//            return s;
//        }else {
            CharSequence s = dateFormat.format("MMM d, yyyy HH:mm", dateFrom);
            return s;
//        }

    }

    public static long hourMinuteToLong(String time, boolean isPm) {
        Calendar calendar = Calendar.getInstance();
        String[] array = time.split(":");
        calendar.set(Calendar.HOUR, Integer.parseInt(array[0]) == 12 ? 0 : Integer.parseInt(array[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(array[1]));
        calendar.set(Calendar.AM_PM, isPm ? Calendar.PM : Calendar.AM);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static final String DATE_YMDHMS="yyyy-MM-dd HH:mm:ss";
    public static final String DATE_YMDHMS_DIAN="yyyy.MM.dd HH:mm:ss";
    public static final String DATE_YMD_DIAN="yyyy.MM.dd";
    public static final String DATE_YMDHM="yyyy-MM-dd HH:mm";
    public static final String DATE_YMD="yyyy-MM-dd";
    public static final String DATE_YMD_N="yyyy年MM月dd日 HH时mm分";
    public static final String DATE_YMD_NS="yyyy年MM月dd日";
    public static final String DATE_HMS="HH:mm:ss";
    public static final String DATE_HM="HH:mm";
    public static final String DATE_MD="MM/dd";
    public static final String DATE_YMD_spead="yyyy/MM/dd HH:mm";

    /**
     *
     * @param operaTime  2019-01-02 00:00:00   ->  2019-01-02
     * @return
     */
    public static final String changeYMDHMS2YMD(String operaTime){
        //转化为date   年月日时分秒
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_YMDHMS, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(operaTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //转化为年月日
        SimpleDateFormat sf = new SimpleDateFormat(DATE_YMD);
        return  sf.format(date);
    }
    /**
     *
     * @param operaTime  2019-01-02 00:00:00   ->  2019-01-02 00：00
     * @return
     */
    public static final String changeYMDHMS2YMDHM(String operaTime){
        //转化为date   年月日时分秒
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_YMDHMS, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(operaTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //转化为年月日
        SimpleDateFormat sf = new SimpleDateFormat(DATE_YMDHM);
        return  sf.format(date);
    }

    /**
     *
     * @param operaTime    2019-01-02 00：00 ->  2019-01-02 00:00:00
     * @return
     */
    public static final String changeYMDH2YMDHMS(String operaTime){
        //转化为date   年月日
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_YMD, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(operaTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //转化为年月日时分秒
        SimpleDateFormat sf = new SimpleDateFormat(DATE_YMDHMS);
        return  sf.format(date);
    }

    /**
     * 格式化事件对象
     * @param date
     * @param format
     * @return
     */
    public static String getStringForDate(Date date, String format){
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return  sf.format(date);
    }

    /**
     * 格式化时间戳
     * @param times
     * @param format
     * @return
     */
    public static String getStringForMillis(Long times, String format){
        Date date ;
        if(times == null){
            date = new Date();
        }else {
            date = new Date(times);
        }
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return  sf.format(date);
    }

    /**
     * 由24小时变为12小时，并想显示am pm
     */
    public static String get12TimeForAM(long milles){
        Date date = new Date();
        date.setTime(milles);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
        String format = sdf.format(date);
        return format;
    }

    /**
     * 日期转为 日 英文月 年
     * 10 Jan 2019
     */
    public static String getEnglishDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int moon = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new String(day + " " + monthArr[moon - 1] + " " + year);
    }

    public static final String[] monthArr=new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"
        , "Aug", "Sep", "Oct", "Nov", "Dec"};

    /**
     * 时间转时间戳
     *
     * @param serverTime  2019-04-16 17:36:18
     * @param format    yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parseServerTime(String serverTime, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(serverTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 最多显示两个时间单位
     * 在单位实际上还是存在误差的，月份统一按照30天算的
     *
     * @param timers
     * @param format
     * @return
     */
    public static String getDiffTime(String timers, String format){
        Date serverTime = parseServerTime(timers, format);
        if(serverTime == null){
            return 0+"s";
        }
        long oldTime =serverTime.getTime();
        long nowTime = System.currentTimeMillis();
        long ldiff = nowTime - oldTime;
        long allS = ldiff / 1000;
        long nowS = ldiff % 1000;
        long allMin = allS / 60;
        long nowMin = allS % 60;
        long allH = allMin / 60;
        long nowH = allMin % 60;
        long allD = allH / 24;
        long nowD = allH % 24;
        long allM = allD / 30;
        long nowM = allD % 30;
        long ally = allM / 12;
        long nowy = allM % 12;
        System.out.println(nowS + "豪秒" + nowMin + "秒" + nowH + "分" + nowD + "时" + nowM + "日" + nowy + "月" + ally + "年");

        StringBuilder sbr = new StringBuilder();
        int length=0;
        if(ally>0){
            sbr.append(ally+"y");
            length++;
            if(isMore(length)) return sbr.toString();
        }
        if(nowy>0){
            sbr.append(nowy+"M");
            length++;
            if(isMore(length)) return sbr.toString();
        }
        if(nowM>0){
            sbr.append(nowM+"d ");
            length++;
            if(isMore(length)) return sbr.toString();
        }
        if(nowD>0){
            sbr.append(nowD+"H");
            length++;
            if(isMore(length)) return sbr.toString();
        }
        if(nowH>0){
            sbr.append(nowH+"m");
            length++;
            if(isMore(length)) return sbr.toString();
        }
        if(nowMin>0){
            sbr.append(nowMin+"s");
            length++;
            if(isMore(length)) return sbr.toString();
        }
        return sbr.toString();
    }
    /**
     * 最多显示一个时间单位 支持切换语言
     * @param timers
     * @param format
     * @return
     */
    public static String getDiffTime(String timers, String format, boolean isChina){
        Date serverTime = parseServerTime(timers, format);
        if(serverTime == null){
            String str= 0 + "s";
            if(isChina){
                str=0+"天";
            }
            return str;
        }
        long oldTime = serverTime.getTime();
        long nowTime = System.currentTimeMillis();
        long ldiff = nowTime - oldTime;
        long allS = ldiff / 1000;
        long nowS = ldiff % 1000;
        long allMin = allS / 60;
        long nowMin = allS % 60;
        long allH = allMin / 60;
        long nowH = allMin % 60;
        long allD = allH / 24;
        long nowD = allH % 24;
        long allM = allD / 30;
        long nowM = allD % 30;
        long ally = allM / 12;
        long nowy = allM % 12;
        StringBuilder sbr = new StringBuilder();
        int length=0;
        if(ally>0){
            String str="y";
            if(isChina){
                str="年";
            }
            sbr.append(ally+str);
            length++;
            return sbr.toString();
        }
        if(nowy>0){
            String str="M";
            if(isChina){
                str="月 ";
            }
            sbr.append(nowy+str);
            length++;
            return sbr.toString();
        }
        if(nowM>0){
            String str="d ";
            if(isChina){
                str="天 ";
            }
            sbr.append(nowM+str);
            length++;
            return sbr.toString();
        }
        if(nowD>0){
            String str="H";
            if(isChina){
                str="小时";
            }
            sbr.append(nowD+str);
            length++;
            return sbr.toString();
        }
        if(nowH>0){
            String str="m";
            if(isChina){
                str="分";
            }
            sbr.append(nowH+str);
            length++;
            return sbr.toString();
        }
        if(nowMin>0){
            String str="s";
            if(isChina){
                str="秒";
            }
            sbr.append(nowMin+str);
            length++;
            return sbr.toString();
        }
        return sbr.toString();
    }
    /**
     * 获取年
     * @return
     */
    public static int getYear(Date date){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return  cd.get(Calendar.YEAR);
    }
    /**
     * 获取月
     * @return
     */
    public static int getMonth(Date date){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return cd.get(Calendar.MONTH) + 1;
    }
    /**
     * 获取日
     * @return
     */
    public static int getDay(Date date){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return  cd.get(Calendar.DATE);
    }
    /**
     * 获取时
     * @return
     */
    public static int getHour(Date date){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return  cd.get(Calendar.HOUR);
    }
    /**
     * 获取分
     * @return
     */
    public static int getMinute(Date date){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return  cd.get(Calendar.MINUTE);
    }
    /**
     * 获取秒
     * @return
     */
    public static int getSecond(Date date){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return  cd.get(Calendar.SECOND);
    }
    private static boolean isMore(int length){
        if(length>=2){
            return true;
        }
        return false;
    }

    /**
     * 将2019-07-09 00:00:00 数据转换为  2019.07.09
     * @param time
     * @return
     */
    public static String formatTimeSplit(String time){
        if(TextUtils.isEmpty(time)){
            return time;
        }
        Date date = parseServerTime(time, DATE_YMDHMS);
        return getStringForDate(date, DateUtil.DATE_YMD_DIAN);
    }

    /**
     * 获取当月的天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
//    /**
//     * 获取当天的周数
//     *
//     * @param date
//     * @return
//     */
//    public static String getDaysOfWeek(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        int i = calendar.get(Calendar.DAY_OF_WEEK);
//        switch (i) {
//            case 1:
//                return StringUtil.getStringByResource(R.string.str_week) ;
//            case 2:
//                return StringUtil.getStringByResource(R.string.str_monday);
//            case 3:
//                return StringUtil.getStringByResource(R.string.str_tuesday);
//            case 4:
//                return StringUtil.getStringByResource(R.string.str_wednesday);
//            case 5:
//                return StringUtil.getStringByResource(R.string.str_thursday);
//            case 6:
//                return StringUtil.getStringByResource(R.string.str_friday);
//            case 7:
//                return StringUtil.getStringByResource(R.string.str_saturday);
//            default:
//                return "";
//        }
//    }
//    /**
//     * 获取当天的周数
//     *
//     * @param date
//     * @return
//     */
//    public static String getDaysOfWeek(int date) {
//        switch (date) {
//            case 1:
//                return StringUtil.getStringByResource(R.string.str_week) ;
//            case 2:
//                return StringUtil.getStringByResource(R.string.str_monday);
//            case 3:
//                return StringUtil.getStringByResource(R.string.str_tuesday);
//            case 4:
//                return StringUtil.getStringByResource(R.string.str_wednesday);
//            case 5:
//                return StringUtil.getStringByResource(R.string.str_thursday);
//            case 6:
//                return StringUtil.getStringByResource(R.string.str_friday);
//            case 7:
//                return StringUtil.getStringByResource(R.string.str_saturday);
//            default:
//                return "";
//        }
//    }

    /**
     * 获取当前月的前月
     * @param date
     * @return
     */
    public static Date getTargetMouthLastMouth(Date date){
//        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        date = (Date) calendar.getTime();
        return date;
    }

    /**
     * 获取当前月的后月
     * @param date
     * @return
     */
    public static Date getTargetMouthNextMouth(Date date){
//        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +1);
        date = (Date) calendar.getTime();
        return date;
    }


    /**
     * 获取当前一天的前一天
     * @param date
     * @return
     */
    public static Date getTargetDayLastDay(Date date){
//        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = (Date) calendar.getTime();
        return date;
    }

    /**
     * 获取当前一天的后一天
     * @param date
     * @return
     */
    public static Date getTargetDayNextDay(Date date){
//        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        date = (Date) calendar.getTime();
        return date;
    }

    /**
     * 日期数字转为Date对象
     * @param year
     * @param mouth
     * @param day
     * @return
     */
    public static Date dateChangeDate(int year, int mouth, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,mouth,day);
        return calendar.getTime();
    }

    /**
     * 返回年月日，初始化时分秒000
     * @return
     */
    public static final Calendar getCalenderYMD000(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return calendar;
    }

    /**
     * 返回年月日时分，初始化秒0
     * @return
     */
    public static final Calendar getCalenderYMDHM0(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
            , calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), 0);
        return calendar;
    }

}
