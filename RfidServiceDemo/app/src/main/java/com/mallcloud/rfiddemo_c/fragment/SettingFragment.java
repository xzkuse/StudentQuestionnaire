package com.mallcloud.rfiddemo_c.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.mallcloud.rfidservicedemo.R;
import com.supoin.rfidservice.sdk.DataUtils;
import com.supoin.rfidservice.sdk.ModuleController;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class SettingFragment extends Fragment implements View.OnClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private View setView;
    private FrameLayout setPageView;
    private EditText etSetPowerData;
    private Button btnSetPower;
    private Button btnReadPower;
    private RadioGroup rgFrequencyType;
    private RadioButton rbFrequencyFCC;
    private RadioButton rbFrequencyETSI;
    private RadioButton rbFrequencyCHN;
    private Button btnGetFrequency;
    private Button btnSetFrequency;
    //    private RadioGroup rgInventoryType;
    //    private RadioButton rbInventoryGen2;
    private RadioButton rbInventory6B;
    private Button btnResetModule;
    private Activity context;
    private ModuleController moduleController;
    private int frequencyType = DataUtils.REG_NORTH_AMERICAN;
    private TextView mTvFrquencyRange;
    private RadioGroup mRgSessionType;
    private RadioButton mRbSessionS0;
    private RadioButton mRbSessionS1;
    private RadioButton mRbSessionS2;
    private RadioButton mRbSessionS3;
    private Button mBtnSessionSet;
    private Button mBtnSessionGet;
    private int sessionType = DataUtils.SESSION0;
    private EditText mEtQValue;
    private Button mBtnSetQValue;
    private Button mBtnGetQValue;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SettingFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SettingFragment newInstance(int index) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COLUMN_COUNT, String.valueOf(index));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moduleController = ModuleController.getInstance(getContext(), new ModuleController.DataListener() {

            @Override
            public void onConnect(boolean isSuccess) {
                super.onConnect(isSuccess);
                try {
                    moduleController.moduleGetParameters(DataUtils.PARA_POWER);
                    Thread.sleep(50);
                    moduleController.moduleGetParameters(DataUtils.PARA_REGION);
                    Thread.sleep(50);
                    moduleController.moduleGetParameters(DataUtils.PARA_SESSION);
                    Thread.sleep(50);
                    moduleController.moduleGetParameters(DataUtils.PARA_QVALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onGetParameters(int paraKey, int paraValue) {
                super.onGetParameters(paraKey, paraValue);
                switch (paraKey) {
                    case DataUtils.PARA_POWER:
                        etSetPowerData.setText(paraValue + "");
                        break;
                    case DataUtils.PARA_REGION:
                        getAntReg(paraValue);
                        break;
                    case DataUtils.PARA_SESSION:
                        Log.d("PARA_SESSION", "=========== getSessionValue====");
                        getSessionValue(paraValue);
                        break;
                    case DataUtils.PARA_QVALUE:
                        mEtQValue.setText(paraValue + "");
                        break;
                }
            }

            @Override
            public void onSetParameters(int paraKey, boolean isSuccess) {
                super.onSetParameters(paraKey, isSuccess);
                if (isSuccess) {
                    Toast.makeText(getContext(), "set success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "set failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onServiceStarted() {
                super.onServiceStarted();

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    //获取频率
    private void getAntReg(int paraValue) {
        switch (paraValue) {
            case DataUtils.REG_NORTH_AMERICAN:
                mTvFrquencyRange.setText(DataUtils.FREQ_POINT_NORTH_AMERICA);
                rbFrequencyFCC.setChecked(true);
                break;

            case DataUtils.REG_CHINA:
                mTvFrquencyRange.setText(DataUtils.FREQ_POINT_CHINA);
                rbFrequencyCHN.setChecked(true);
                break;

            case DataUtils.REG_EUROPE:
                mTvFrquencyRange.setText(DataUtils.FREQ_POINT_EUROPE);
                rbFrequencyETSI.setChecked(true);
                break;
        }
    }

    //获取session
    private void getSessionValue(int paraValue) {
        switch (paraValue) {
            case DataUtils.SESSION0:
                mRbSessionS0.setChecked(true);
                break;
            case DataUtils.SESSION1:
                mRbSessionS1.setChecked(true);
                break;
            case DataUtils.SESSION2:
                mRbSessionS2.setChecked(true);
                break;
            case DataUtils.SESSION3:
                mRbSessionS3.setChecked(true);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setPageView = new FrameLayout(getContext());
        setView = inflater.inflate(R.layout.set_page, null);
        setPageView.addView(setView);
        initView();
        return setPageView;
    }

    /*初始化*/
    private void initView() {
        etSetPowerData = setPageView.findViewById(R.id.et_setPowerData);
        btnSetPower = setPageView.findViewById(R.id.btn_setPower);
        btnReadPower = setPageView.findViewById(R.id.btn_readPower);
        btnReadPower.setOnClickListener(this);
        btnSetPower.setOnClickListener(this);
        mTvFrquencyRange = setPageView.findViewById(R.id.tv_frquencyRange);

        rgFrequencyType = setPageView.findViewById(R.id.rg_frequencyType);
        rbFrequencyFCC = setPageView.findViewById(R.id.rb_frequency_fcc);
        rbFrequencyETSI = setPageView.findViewById(R.id.rb_frequency_etsi);
        rbFrequencyCHN = setPageView.findViewById(R.id.rb_frequency_chn);
        rgFrequencyType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_frequency_fcc://美国
                        frequencyType = DataUtils.REG_NORTH_AMERICAN;
                        break;
                    case R.id.rb_frequency_etsi: //欧洲
                        frequencyType = DataUtils.REG_EUROPE;
                        break;
                    case R.id.rb_frequency_chn: //中国
                        frequencyType = DataUtils.REG_CHINA;
                        break;
                }
            }
        });

        btnGetFrequency = setPageView.findViewById(R.id.btn_getFrequency);
        btnSetFrequency = setPageView.findViewById(R.id.btn_setFrequency);
        btnGetFrequency.setOnClickListener(this);
        btnSetFrequency.setOnClickListener(this);


        mRgSessionType = setPageView.findViewById(R.id.rg_session_type);
        mRbSessionS0 = setPageView.findViewById(R.id.rb_session_s0);
        mRbSessionS1 = setPageView.findViewById(R.id.rb_session_s1);
        mRbSessionS2 = setPageView.findViewById(R.id.rb_session_s2);
        mRbSessionS3 = setPageView.findViewById(R.id.rb_session_s3);
        mBtnSessionGet = setPageView.findViewById(R.id.btn_session_get);
        mBtnSessionSet = setPageView.findViewById(R.id.btn_session_set);
        mBtnSessionGet.setOnClickListener(this);
        mBtnSessionSet.setOnClickListener(this);

        mRgSessionType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_session_s0:
                        sessionType = DataUtils.SESSION0;
                        mRbSessionS0.setChecked(true);
                        break;
                    case R.id.rb_session_s1:
                        sessionType = DataUtils.SESSION1;
                        mRbSessionS1.setChecked(true);
                        break;
                    case R.id.rb_session_s2:
                        sessionType = DataUtils.SESSION2;
                        mRbSessionS2.setChecked(true);
                        break;
                    case R.id.rb_session_s3:
                        sessionType = DataUtils.SESSION3;
                        mRbSessionS2.setChecked(true);
                        break;
                }
            }
        });

        mEtQValue = setPageView.findViewById(R.id.et_QValue);
        mBtnGetQValue = setPageView.findViewById(R.id.btn_getQValue);
        mBtnSetQValue = setPageView.findViewById(R.id.btn_setQValue);
        mBtnGetQValue.setOnClickListener(this);
        mBtnSetQValue.setOnClickListener(this);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_setPower:
                int setPowerData = Integer.parseInt(etSetPowerData.getText().toString());
                if (setPowerData > 5 && setPowerData < 30){
                    moduleController.moduleSetParameters(DataUtils.PARA_POWER, setPowerData);
                }else{
                    Toast.makeText(context, "The power range is 5-30!", Toast.LENGTH_SHORT).show();
                }
                
                break;
            case R.id.btn_readPower:
                moduleController.moduleGetParameters(DataUtils.PARA_POWER);
                break;
            case R.id.btn_getFrequency:
                moduleController.moduleGetParameters(DataUtils.PARA_REGION);
                break;
            case R.id.btn_setFrequency:
                if (frequencyType == DataUtils.REG_EUROPE) {
                    moduleController.moduleSetParameters(DataUtils.PARA_REGION, DataUtils.REG_EUROPE);
                } else if (frequencyType == DataUtils.REG_NORTH_AMERICAN) {
                    moduleController.moduleSetParameters(DataUtils.PARA_REGION, DataUtils.REG_NORTH_AMERICAN);
                } else if (frequencyType == DataUtils.REG_CHINA) {
                    moduleController.moduleSetParameters(DataUtils.PARA_REGION, DataUtils.REG_CHINA);
                }
                break;
            case R.id.btn_session_get:
                moduleController.moduleGetParameters(DataUtils.PARA_SESSION);
                break;
            case R.id.btn_session_set:
                if (sessionType == DataUtils.SESSION0) {
                    moduleController.moduleSetParameters(DataUtils.PARA_SESSION, 0);
                } else if (sessionType == DataUtils.SESSION1) {
                    moduleController.moduleSetParameters(DataUtils.PARA_SESSION, 1);
                } else if (sessionType == DataUtils.SESSION2) {
                    moduleController.moduleSetParameters(DataUtils.PARA_SESSION, 2);
                } else if (sessionType == DataUtils.SESSION3) {
                    moduleController.moduleSetParameters(DataUtils.PARA_SESSION, 3);
                }
                break;
            case R.id.btn_getQValue:
                moduleController.moduleGetParameters(DataUtils.PARA_QVALUE);
                break;
            case R.id.btn_setQValue:
                int setQValue = Integer.parseInt(mEtQValue.getText().toString());
                moduleController.moduleSetParameters(DataUtils.PARA_QVALUE, setQValue);
                break;
        }

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
