package com.mallcloud.rfiddemo_c.fragment.content;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.mallcloud.rfidservicedemo.R;
import com.supoin.rfidservice.sdk.DataUtils;
import com.supoin.rfidservice.sdk.ModuleController;

import java.util.Objects;

public class ReadFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private View readTag;
    private EditText etReadStartAddress;
    private EditText etReadBlock;
    private EditText etReadData;
    private Button btnReadTag;
    private Activity context;
    private RadioGroup rgReadDataType;
    private RadioGroup rgReadOperateBlock;
    private String chooseDataType;
    private RadioButton rbOperateBlock;
    private RadioButton rbUtf;
    private RadioButton rbGbk;
    private RadioButton rbAscii;
    private RadioButton rbHex;
    private RadioButton rbReserve;
    private RadioButton rbEpc;
    private RadioButton rbTid;
    private RadioButton rbUser;
    private String chooseBank;

    private static int bank = 1;
    private static int type = 0;
    private ModuleController moduleController;

    public ReadFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static ReadFragment newInstance(String index) {
        ReadFragment fragment = new ReadFragment();
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
            public void onReadTag(byte[] tagData) {
                if (tagData == null) return;
                String typeTagData = null;
                if (type == 0) {
                    typeTagData = DataUtils.byteToHexStr(tagData);
                } else if (type == 1) {
                    typeTagData = DataUtils.byteToASCIIStr(tagData);
                } else if (type == 2) {
                    typeTagData = DataUtils.byteToGBKStr(tagData);
                } else if (type == 3) {
                    typeTagData = DataUtils.byteToUTFStr(tagData);
                }
                etReadData.setText(typeTagData);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout view = new FrameLayout(Objects.requireNonNull(getContext()));

        //添加子布局
        readTag = inflater.inflate(R.layout.read_tags, null);
        view.addView(readTag);
        // Inflate the layout for this fragment
        initView();
        return view;
    }

    private void initView() {
        //读
        etReadStartAddress = readTag.findViewById(R.id.et_readStartAddress);
        etReadBlock = readTag.findViewById(R.id.et_readBlock);
        etReadData = readTag.findViewById(R.id.et_readData);

        btnReadTag = readTag.findViewById(R.id.btn_readTag);

        btnReadTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int startAddress = Integer.parseInt(etReadStartAddress.getText().toString());
                int blockCount = Integer.parseInt(etReadBlock.getText().toString());

                if (bank == 1) {
                    moduleController.moduleReadTag(DataUtils.BANK_EPC, startAddress, blockCount, null,null);
                } else if (bank == 0) {
                    moduleController.moduleReadTag(DataUtils.BANK_RESERVED, startAddress, blockCount, null,null);
                } else if (bank == 2) {
                    moduleController.moduleReadTag(DataUtils.BANK_TID, startAddress, blockCount, null,null);
                } else if (bank == 3) {
                    moduleController.moduleReadTag(DataUtils.BANK_USER, startAddress, blockCount, null,null);
                }

            }
        });

        rgReadDataType = readTag.findViewById(R.id.rg_readDataType);
        rbHex = readTag.findViewById(R.id.rb_hex);
        rbAscii = readTag.findViewById(R.id.rb_ascii);
        rbGbk = readTag.findViewById(R.id.rb_gbk);
        rbUtf = readTag.findViewById(R.id.rb_utf);
        rbHex.setChecked(true);
        rgReadDataType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                RadioButton rbDataType = readTag.findViewById(id);
                chooseDataType = rbDataType.getText().toString();
//                Toast.makeText(context, "你选择了"+s, Toast.LENGTH_SHORT).show();
                switch (checkedId) {
                    case R.id.rb_hex:
                        type = 0;
                        break;
                    case R.id.rb_ascii:
                        type = 1;
                        break;
                    case R.id.rb_gbk:
                        type = 2;
                        break;
                    case R.id.rb_utf:
                        type = 3;
                        break;
                }
            }
        });

        rgReadOperateBlock = readTag.findViewById(R.id.rg_readOperateBlock);
        rbReserve = readTag.findViewById(R.id.rb_reserve);
        rbEpc = readTag.findViewById(R.id.rb_epc);
        rbTid = readTag.findViewById(R.id.rb_tid);
        rbUser = readTag.findViewById(R.id.rb_user);
        rbEpc.setChecked(true);
        chooseBank = "EPC";

        rgReadOperateBlock.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                rbOperateBlock = readTag.findViewById(checkedRadioButtonId);
                chooseBank = rbOperateBlock.getText().toString();
//                Toast.makeText(context, "你选择了"+checkedId, Toast.LENGTH_SHORT).show();
                switch (checkedId) {
                    case R.id.rb_reserve:
                        bank = 0;
                        break;
                    case R.id.rb_epc:
                        bank = 1;
                        break;
                    case R.id.rb_tid:
                        bank = 2;
                        break;
                    case R.id.rb_user:
                        bank = 3;
                        break;
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
