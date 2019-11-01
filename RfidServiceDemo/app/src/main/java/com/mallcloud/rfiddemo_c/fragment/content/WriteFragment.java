package com.mallcloud.rfiddemo_c.fragment.content;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
import java.util.Random;

public class WriteFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private Activity context;
    private View writeTag;
    private EditText etWriteData;
    private EditText etWriteStartAddress;
    private EditText etWriteBlock;
    private Button btnWriteTag;
    private RadioButton rbWriteHex;
    private RadioButton rbWriteAscii;
    private RadioButton rbWriteGbk;
    private RadioButton rbWriteUhf;
    private RadioButton rbWriteReserve;
    private RadioButton rbWriteEpc;
    private RadioButton rbWriteTid;
    private RadioButton rbWriteUser;
    private RadioGroup rgWriteBank;
    private RadioGroup rgWriteDataType;

    private static int writeBank = DataUtils.BANK_EPC;
    private static int writeType = 0;
    private ModuleController moduleController;
    private CheckBox mCbPassword;
    private EditText mEtPassword;
    private boolean mChecked;

    public WriteFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WriteFragment newInstance(String index) {
        WriteFragment fragment = new WriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COLUMN_COUNT, index);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moduleController = ModuleController.getInstance(context, new ModuleController.DataListener() {
            @Override
            public void onWriteTag(boolean isSuccess) {
                if (isSuccess) {
                    Toast.makeText(context, "write succeed!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "write failed!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout view = new FrameLayout(Objects.requireNonNull(getContext()));

        //添加子布局
        writeTag = inflater.inflate(R.layout.write_tags, null);
        view.addView(writeTag);
        // Inflate the layout for this fragment
        initView();
        return view;
    }

    private void initView() {
        etWriteData = writeTag.findViewById(R.id.et_writeData);
        etWriteStartAddress = writeTag.findViewById(R.id.et_writeStartAddress);
        etWriteBlock = writeTag.findViewById(R.id.et_writeBlock);
        btnWriteTag = writeTag.findViewById(R.id.btn_writeTag);

        rbWriteHex = writeTag.findViewById(R.id.rb_writeHex);
        rbWriteAscii = writeTag.findViewById(R.id.rb_writeAscii);
        rbWriteGbk = writeTag.findViewById(R.id.rb_writeGbk);
        rbWriteUhf = writeTag.findViewById(R.id.rb_writeUtf);

        rbWriteReserve = writeTag.findViewById(R.id.rb_writeReserve);
        rbWriteEpc = writeTag.findViewById(R.id.rb_writeEpc);
        rbWriteTid = writeTag.findViewById(R.id.rb_writeTid);
        rbWriteUser = writeTag.findViewById(R.id.rb_writeUser);

        mCbPassword = writeTag.findViewById(R.id.cb_password);
        mEtPassword = writeTag.findViewById(R.id.et_password);
        mChecked = mCbPassword.isChecked();
        mCbPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mChecked = true;
                }else {
                    mChecked = false;
                }
            }
        });

        rbWriteHex.setChecked(true);
        rbWriteEpc.setChecked(true);

        rgWriteBank = writeTag.findViewById(R.id.rg_writeBank);
        rgWriteBank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_writeReserve:
                        writeBank = DataUtils.BANK_RESERVED;
                        break;
                    case R.id.rb_writeEpc:
                        writeBank = DataUtils.BANK_EPC;
                        break;
                    case R.id.rb_writeTid:
                        writeBank = DataUtils.BANK_TID;
                        break;
                    case R.id.rb_writeUser:
                        writeBank = DataUtils.BANK_USER;
                        break;
                }
            }
        });

        rgWriteDataType = writeTag.findViewById(R.id.rg_writeDataType);
        rgWriteDataType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_writeHex:
                        writeType = 0;
                        break;
                    case R.id.rb_writeAscii:
                        writeType = 1;
                        break;
                    case R.id.rb_writeGbk:
                        writeType = 2;
                        break;
                    case R.id.rb_writeUtf:
                        writeType = 3;
                        break;
                }
            }
        });

        btnWriteTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int writeStartAddress = Integer.valueOf(etWriteStartAddress.getText().toString().trim());
                String writeData = etWriteData.getText().toString();
                int writeBlocks = Integer.valueOf(etWriteBlock.getText().toString());
                if (writeData.equals("")) {

                    Toast.makeText(context, R.string.write_null_toast, Toast.LENGTH_SHORT).show();
                } else {
                    if (!mChecked){
                        if (writeType == DataUtils.DATA_TYPE_HEX) {
                            try {
                                moduleController.moduleWriteTag(writeBank, writeStartAddress, DataUtils.hexStrToByte(writeData), null,null);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                Toast.makeText(context, R.string.write_toast, Toast.LENGTH_LONG).show();
                                Random r = new Random();
                                int n = writeBlocks * 2;
                                byte[] data = new byte[n];
                                for(int i=0;i<n;i++){
                                    data[i] = (byte) r.nextInt();
                                }
                                etWriteData.setText(DataUtils.byteToHexStr(data)+"");
                                etWriteData.setTextColor(Color.RED);

                            }
                        } else if (writeType == DataUtils.DATA_TYPE_ASCII) {
                            moduleController.moduleWriteTag(writeBank, writeStartAddress, DataUtils.asciiStrToByte(writeData), null,null);
                        } else if (writeType == DataUtils.DATA_TYPE_GBK) {
                            moduleController.moduleWriteTag(writeBank, writeStartAddress, DataUtils.gbkStrToByte(writeData), null,null);
                        } else if (writeType == DataUtils.DATA_TYPE_UTF8) {
                            moduleController.moduleWriteTag(writeBank, writeStartAddress, DataUtils.utfStrToByte(writeData), null,null);
                        }
                    }else {
                        String password = mEtPassword.getText().toString().trim();
                        if (writeType == DataUtils.DATA_TYPE_HEX) {
                            try {
                                moduleController.moduleWriteTag(writeBank, writeStartAddress, DataUtils.hexStrToByte(writeData), DataUtils.hexStrToByte(password),null);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                Toast.makeText(context, R.string.write_toast, Toast.LENGTH_LONG).show();
                                Random r = new Random();
                                int n = writeBlocks * 2;
                                byte[] data = new byte[n];
                                for(int i=0;i<n;i++){
                                    data[i] = (byte) r.nextInt();
                                }
                                etWriteData.setText(DataUtils.byteToHexStr(data)+"");
                                etWriteData.setTextColor(Color.RED);

                            }
                        } else if (writeType == DataUtils.DATA_TYPE_ASCII) {
                            moduleController.moduleWriteTag(writeBank, writeStartAddress, DataUtils.asciiStrToByte(writeData), DataUtils.hexStrToByte(password),null);
                        } else if (writeType == DataUtils.DATA_TYPE_GBK) {
                            moduleController.moduleWriteTag(writeBank, writeStartAddress, DataUtils.gbkStrToByte(writeData), DataUtils.hexStrToByte(password),null);
                        } else if (writeType == DataUtils.DATA_TYPE_UTF8) {
                            moduleController.moduleWriteTag(writeBank, writeStartAddress, DataUtils.utfStrToByte(writeData), DataUtils.hexStrToByte(password),null);
                        }

                    }

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
