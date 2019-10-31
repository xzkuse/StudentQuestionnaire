package com.mallcloud.rfiddemo_c.fragment.content;

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

import java.util.Objects;

public class LockFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private View lockTag;
    private EditText etLockPassword;
    private Button btnLockTag;
    private Activity context;
    private RadioButton rbLockOpen;
    private RadioButton rbLockAlwaysOpen;
    private RadioButton rbLockLocked;
    private RadioButton rbLockAlwaysLock;
    private RadioButton rbLockReserve;
    private RadioButton rbLockEpc;
    private RadioButton rbLockTid;
    private RadioButton rbLockUser;
    private RadioGroup rgLockBank;
    private RadioGroup rgLockType;

    private int lockBank = DataUtils.BANK_EPC;
    private int lockType = DataUtils.LOCK_TYPE_UNLOCK;
    private ModuleController moduleController;
    private String sPassword;

    public LockFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LockFragment newInstance(String index) {
        LockFragment fragment = new LockFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COLUMN_COUNT, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moduleController = ModuleController.getInstance(context,new ModuleController.DataListener(){
            @Override
            public void onLockTag(int lockType) {
                super.onLockTag(lockType);
                Log.d("onLockTag", "lockType=======" + lockType);
                switch (lockType) {
                    case DataUtils.LOCK_TYPE_LOCK:
                        Toast.makeText(context, "locked success", Toast.LENGTH_SHORT).show();
                        break;
                    case DataUtils.LOCK_TYPE_UNLOCK:
                        Toast.makeText(context, "unlocked success", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout view = new FrameLayout(Objects.requireNonNull(getContext()));

        //添加子布局
        lockTag = inflater.inflate(R.layout.lock_tags, null);
        view.addView(lockTag);
        // Inflate the layout 、for this fragment
        initView();
        return view;
    }

    private void initView() {
        //锁
        etLockPassword = lockTag.findViewById(R.id.et_lockPassword);

        rbLockOpen = lockTag.findViewById(R.id.rb_lockOpen);
       /* rbLockAlwaysOpen = lockTag.findViewById(R.id.rb_lockAlwaysOpen);
        rbLockAlwaysOpen.invalidate();*/
        rbLockLocked = lockTag.findViewById(R.id.rb_lockLocked);
//        rbLockAlwaysLock = lockTag.findViewById(R.id.rb_lockAlwaysLock);
        rbLockReserve = lockTag.findViewById(R.id.rb_lockReserve);
        rbLockEpc = lockTag.findViewById(R.id.rb_lockEpc);
        rbLockTid = lockTag.findViewById(R.id.rb_lockTid);
        rbLockUser = lockTag.findViewById(R.id.rb_lockUser);

        rbLockEpc.setChecked(true);
        rbLockOpen.setChecked(true);

        rgLockType = lockTag.findViewById(R.id.rg_lockType);
        rgLockType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_lockOpen:
                        lockType = DataUtils.LOCK_TYPE_UNLOCK;
                        break;
                    /*case R.id.rb_lockAlwaysOpen:
                        lockType = DataUtils.LOCK_TYPE_PERMANENT_UNLOCK;
                        break;*/
                    case R.id.rb_lockLocked:
                        lockType = DataUtils.LOCK_TYPE_LOCK;
                        break;
                    /*case R.id.rb_lockAlwaysLock:
                        lockType = DataUtils.LOCK_TYPE_PERMANENT_LOCK;
                        break;*/
                }

            }
        });


        rgLockBank = lockTag.findViewById(R.id.rg_lockBank);
        rgLockBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_lockReserve:
                        lockBank = DataUtils.BANK_RESERVED;
                        break;
                    case R.id.rb_lockEpc:
                        lockBank = DataUtils.BANK_EPC;
                        break;
                    case R.id.rb_lockTid:
                        lockBank = DataUtils.BANK_TID;
                        break;
                    case R.id.rb_lockUser:
                        lockBank = DataUtils.BANK_USER;
                        break;
                }
            }
        });


        btnLockTag = lockTag.findViewById(R.id.btn_lockTag);
        btnLockTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  byte[] pwd = new byte[4];
                for (int i = 0; i < 4; i++) {
                    pwd[i] = 0;
                }*/
                String pwd = etLockPassword.getText().toString();
                sPassword = etLockPassword.getText().toString();
                if (!sPassword.equals("")) {


                    if (lockType == DataUtils.LOCK_TYPE_UNLOCK) {
                        moduleController.moduleLockTag(DataUtils.MEMBANK_EPC, DataUtils.LOCK_TYPE_UNLOCK, DataUtils.hexStrToByte(pwd),null);
                    } /*else if (lockType == DataUtils.LOCK_TYPE_PERMANENT_UNLOCK) {
                        moduleController.moduleLockTag(context, DataUtils.MEMBANK_EPC, DataUtils.LOCK_TYPE_PERMANENT_UNLOCK, pwd);
                    }*/
                    else if (lockType == DataUtils.LOCK_TYPE_LOCK) {
                        moduleController.moduleLockTag(DataUtils.MEMBANK_EPC, DataUtils.LOCK_TYPE_LOCK, DataUtils.hexStrToByte(pwd),null);
                    }/* else if (lockType == DataUtils.LOCK_TYPE_PERMANENT_LOCK) {
                        moduleController.moduleLockTag(context, DataUtils.MEMBANK_EPC, DataUtils.LOCK_TYPE_PERMANENT_LOCK, pwd);
                    }*/

                } else {
                    Toast.makeText(context, "please input password!", Toast.LENGTH_SHORT).show();
                }

            }
        });

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


}
