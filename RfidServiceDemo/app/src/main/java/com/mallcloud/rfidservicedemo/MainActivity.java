package com.mallcloud.rfidservicedemo;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.mallcloud.base.view.BaseActivity;
import com.mallcloud.utils.ToastUtil;
import com.supoin.rfidservice.sdk.DataUtils;
import com.supoin.rfidservice.sdk.ModuleController;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 * @author xzk
 * @data 2019/10/28
 * @email xiezhengkun@beyondsoft.com
 * @remark 首页
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView textStatus;
    private TextView txtLog;
    private TextView textOp;
    private Button butStart;
    private Button butReadTag;
    private Button butWriteTag;
    private Button butLockTag;
    private Button butSetPower;
    private Button butSetReg;
    private Button butSession;
    private Button butSetQ;
    private Button butGetParamter;
    private ModuleController moduleController;

    @Override
    public int getLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    public void initializeData(Bundle savedInstanceState) {
        butStart = findViewById(R.id.but_start);
        butStart.setOnClickListener(this);
        textStatus = findViewById(R.id.text_status);
        txtLog = findViewById(R.id.txt_log);
        butReadTag = findViewById(R.id.but_read_tag);
        butReadTag.setOnClickListener(this);
        butWriteTag = findViewById(R.id.but_write_tag);
        butWriteTag.setOnClickListener(this);
        butLockTag = findViewById(R.id.but_lock_tag);
        butLockTag.setOnClickListener(this);
        textOp = findViewById(R.id.text_op);
        butSetPower = findViewById(R.id.but_set_power);
        butSetPower.setOnClickListener(this);
        butSetReg = findViewById(R.id.but_set_reg);
        butSetReg.setOnClickListener(this);
        butSession = findViewById(R.id.but_session);
        butSession.setOnClickListener(this);
        butSetQ = findViewById(R.id.but_set_q);
        butSetQ.setOnClickListener(this);
        butGetParamter = findViewById(R.id.but_get_paramter);
        butGetParamter.setOnClickListener(this);

        moduleController = ModuleController.getInstance(this, new ModuleController.DataListener() {
            @Override
            public void onServiceStarted() {
                super.onServiceStarted();
                ToastUtil.showLong("服务启动成功");
            }

            @Override
            public void onError() {
                super.onError();
                ToastUtil.showLong("服务不存在");
            }

            @Override
            public void onConnect(boolean isSuccess) {
                super.onConnect(isSuccess);
                textStatus.setText(String.format(getString(R.string.str_status_f), "连接成功"));
            }

            @Override
            public void onDisConnect(boolean isSuccess) {
                super.onDisConnect(isSuccess);
                textStatus.setText(String.format(getString(R.string.str_status_f), "断开连接"));
            }

            @Override
            public void onInventoryTag(byte[] epcTid, byte[] pc, byte count, float rssi, float freq) {
                //盘点标签数据
                StringBuilder strEpcList = new StringBuilder();
                for (Map<String, String> data:moduleController.tagList) {
                    String strEpc = data.get(DataUtils.KEY_EPC_TID);
                    String strPc = data.get(DataUtils.KEY_PC);
                    strEpcList.append(strEpc).append(",").append(strPc).append("\r\n");
                }

                txtLog.setText(strEpcList);
            }

            @Override
            public void onReadTag(byte[] tagData) {
                super.onReadTag(tagData);
                textStatus.setText(String.format(getString(R.string.str_status_f), "读取标签"));
                if(tagData == null) return;

                txtLog.setText(Arrays.toString(tagData));
            }

            @Override
            public void onWriteTag(boolean isSuccess) {
                super.onWriteTag(isSuccess);
                //写入标签
                if(isSuccess){
                    textStatus.setText(String.format(getString(R.string.str_status_f), "写入成功"));
                }else {
                    textStatus.setText(String.format(getString(R.string.str_status_f), "写入失败"));
                }
            }

            @Override
            public void onLockTag(int lockType) {
                super.onLockTag(lockType);
                textOp.setText("锁定状态"+lockType);
            }

            @Override
            public void onSetParameters(int paraKey, boolean isSuccess) {
                super.onSetParameters(paraKey, isSuccess);
                textOp.setText("设置状态："+isSuccess);
            }

            @Override
            public void onGetParameters(int paraKey, int paraValue) {
                super.onGetParameters(paraKey, paraValue);
                textOp.setText(getParamterRate(paraKey) + paraValue);
            }
        });

    }

    private int key = 0;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_read_tag:
                //读标签
                moduleController.moduleReadTag(DataUtils.BANK_EPC,2,2,null,null);
                break;
            case R.id.but_write_tag:
                //写标签
                Random r = new Random();
                byte[] data = new byte[4];
                for (int i = 0; i < data.length; i++) {
                    data[i] = (byte)r.nextInt();
                }
                moduleController.moduleWriteTag(DataUtils.BANK_EPC,2, data,null,null);
                break;
            case R.id.but_lock_tag:
                //锁定标签
                byte[] pwd = new byte[4];
                for (int i = 0; i < pwd.length; i++) {
                    pwd[i] = 0;
                }
                moduleController.moduleLockTag(DataUtils.MEMBANK_EPC,DataUtils.LOCK_TYPE_LOCK, pwd,null);
                break;
            case R.id.but_start:
                break;
            case R.id.but_set_power:
                textStatus.setText(String.format(getString(R.string.str_status_f), "设置射频输出功率"));
                moduleController.moduleSetParameters(DataUtils.PARA_POWER,26);
                break;
            case R.id.but_set_reg:
                textStatus.setText(String.format(getString(R.string.str_status_f), "设置射频频谱"));
                moduleController.moduleSetParameters(DataUtils.PARA_REGION,DataUtils.REG_NORTH_AMERICAN);
                break;
            case R.id.but_session:
                textStatus.setText(String.format(getString(R.string.str_status_f), "设置session"));
                moduleController.moduleSetParameters(DataUtils.PARA_SESSION,1);
                break;
            case R.id.but_set_q:
                textStatus.setText(String.format(getString(R.string.str_status_f), "设置q值"));
                moduleController.moduleSetParameters(DataUtils.PARA_QVALUE,1);
                break;
            case R.id.but_get_paramter:
                //获取设置的参数  射频输出功率
                    int lastT=key%4;
                    moduleController.moduleGetParameters(lastT);
                    key++;
                break;
                default:
                    break;
        }
    }

    public String getParamterRate(int tag){
        switch (tag){
            case DataUtils.PARA_POWER:
                return "获取射频输出功率";
            case DataUtils.PARA_REGION:
                return "获取射频频谱";
            case DataUtils.PARA_SESSION:
                return "获取session值";
            case DataUtils.PARA_QVALUE:
                return "获取q值";
                default:
                    return "无";
        }
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if(moduleController != null){
//            //此刷新在模块更换之后调用（未重启设备），一般来讲，模块更换后是要重启设备的，所以慎用
//            moduleController.moduleRefreshModule();
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(moduleController != null){
            //关闭模块连接，一般在退出应用不再使用时调用
            moduleController.close();
        }
    }
}
