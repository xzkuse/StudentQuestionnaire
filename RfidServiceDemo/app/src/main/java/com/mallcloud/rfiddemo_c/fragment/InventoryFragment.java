package com.mallcloud.rfiddemo_c.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.mallcloud.rfidservicedemo.R;
import com.supoin.rfidservice.sdk.DataUtils;
import com.supoin.rfidservice.sdk.ModuleController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class InventoryFragment extends Fragment implements AbsListView.OnScrollListener, View.OnClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private ListView listView;
    private SwipeRefreshLayout refreshLayout;
    private int itemsLastIndex;
    private int visibleItemCount;
    private int visibleLastIndex;
    private int lastIndex;

    Handler handler = new Handler();
    private FrameLayout view;
    private Activity context;
    private TextView tv_moduleInfo;
    private Button btnStartInventory;
    private Button btnStopInventory;
    private Button btnClear;
    private boolean isInventory = false;
    private SimpleAdapter tagAdapter;
    private TextView tvState;
    private ModuleController moduleController;
    public static String[] TagList = null;

    Handler runTimeHandler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            runTimeHandler.postDelayed(this, 1000);
            long nowTime = System.currentTimeMillis();
            long runTime = nowTime - startTimeMillis;
            long totalSeconds = runTime / 1000;
            String time = changeTime((int) totalSeconds);

            //显示时间
            tvSystemTimes.setText(time);
        }
    };





    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mTagList.clear();
            mTagList.addAll(moduleController.tagList);
            tagAdapter.notifyDataSetChanged();
        }
    };
    private long startTimeMillis;
    private TextView tvSystemTimes;
    private long currentMin;
    private TextView mTvLucencyView;
    private List<HashMap<String, String>> mTagList;

    @SuppressLint("HandlerLeak")
    private Handler mHandleCount = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    tv_moduleInfo.post(new Runnable() {
                        @Override
                        public void run() {
                            tv_moduleInfo.setText(moduleController.tagList.size()+"");
                            handler.postDelayed(this,200);
                        }
                    });
                    break;
            }
        }
    };


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public InventoryFragment() {
    }


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static InventoryFragment newInstance(String index) {
        InventoryFragment fragment = new InventoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COLUMN_COUNT, index);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Message msg = new Message();
        msg.what = 1;
        mHandleCount.sendMessage(msg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = new FrameLayout(getContext());
        //添加子布局
        View inflate = inflater.inflate(R.layout.inventory_page, null);
        view.addView(inflate);
        //初始化view
        initView();
        //设置adapter
        listView.setAdapter(tagAdapter);
        listView.setOnScrollListener(this);
        return view;
    }


    @Override
    public void onActivityCreated(  Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("InventoryFragment", "ModuleController()");
        moduleController = ModuleController.getInstance(context,new ModuleController.DataListener(){
            @Override
            public void onServiceStarted() {
                super.onServiceStarted();
            }

            @Override
            public void onError() {
                Log.e("Inventory_create", "onError()");
                tv_moduleInfo.setText(R.string.no_module);
            }

            @Override
            public void onConnect(boolean isSuccess) {
                Log.d("Inventory_create", "onConnect() ---- " + isSuccess);
                tv_moduleInfo.setText(R.string.connect_success);
            }

            @Override
            public void onDisConnect(boolean isSuccess) {
                Log.d("Inventory_create", "onDisConnect() ----" + isSuccess);
                tv_moduleInfo.setText(R.string.module_disconnect);
            }

            @Override
            public void onInventoryTag(byte[] epcTid, byte[] pc, byte count, float rssi, float freq){
                super.onInventoryTag(epcTid, pc, count, rssi, freq);
                handler.postDelayed(mRunnable, 50);
            }

            @Override
            public void onSetBeep() {
                super.onSetBeep();
            }
        });
    }

    @Override
    public void onResume() {
        Log.d("InventoryFragment", "onResume()");
        super.onResume();
        //fragment is active
        initAdapter();
        btnStartInventory.setEnabled(true);
        btnStopInventory.setEnabled(false);
    }


    @Override
    public void onPause() {
        Log.d("InventoryFragment", "onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("InventoryFragment", "onStop()");
        super.onStop();
        moduleController.moduleStopInventoryTag();
        runTimeHandler.removeCallbacks(runnable);
        handler.removeCallbacks(mRunnable);
    }

    @Override
    public void onDestroyView() {
        Log.d("InventoryFragment", "onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("InventoryFragment", "onDestroy()");
        super.onDestroy();
    }


    private void initView() {
        tvState = view.findViewById(R.id.tv_state);
        btnStartInventory = view.findViewById(R.id.btn_startInventory);
        btnStopInventory = view.findViewById(R.id.btn_stopInventory);
        btnClear = view.findViewById(R.id.btn_Clear);
        mTvLucencyView = view.findViewById(R.id.tv_lucencyView);
        tvSystemTimes = view.findViewById(R.id.tv_systemTimes);

        tvState.setText(R.string.device_state);
        tvSystemTimes.setText(R.string.time);
        btnStopInventory.setEnabled(false);
        btnClear.setEnabled(false);
        btnStartInventory.setOnClickListener(this);
        btnStopInventory.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        mTvLucencyView.setVisibility(View.GONE);


        listView = view.findViewById(R.id.listView);
        refreshLayout = view.findViewById(R.id.sr_refresh);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
            }
        });
        tv_moduleInfo = view.findViewById(R.id.tv_module_info);
    }



    /*初始化适配器*/
    private void initAdapter() {
        mTagList = new ArrayList();
        tagAdapter = new SimpleAdapter(context, mTagList, R.layout.inventory_epc_item, new String[]{
                DataUtils.KEY_EPC_TID,DataUtils.KEY_COUNT}, new int[]{R.id.tv_tag_epc,R.id.tv_tag_count});
        listView.setAdapter(tagAdapter);
        handler.postDelayed(mRunnable, 50);
    }


    @Override
    public void onDetach() {
        Log.d("InventoryFragment", "onDetach()");
        super.onDetach();
    }

    //滑动状态改变时调用
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.d("InventoryFragment", "onScrollStateChanged()");
        //数据集最后一项的索引
        itemsLastIndex = tagAdapter.getCount() - 1;
        lastIndex = itemsLastIndex + 1;
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == 1) {
            //如果是自动加载，可以再这里放置异步加载数据的代码
            Log.d("LoadMore", "Loading............");
        }
    }

    /*滑动时调用*/
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleItemCount = visibleItemCount;
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startInventory:
                mTagList.clear();
                mTvLucencyView.setVisibility(View.VISIBLE);
                mTvLucencyView.setOnClickListener(this);
                isInventory = false;
                listView.setEnabled(false);
                tvState.setText(R.string.count);
                doTagInventory();
                startTimeMillis = System.currentTimeMillis();
                runTimeHandler.postDelayed(runnable, 100);
                tagAdapter.notifyDataSetChanged();  //数据集变化后通知adapter
                btnClear.setEnabled(false);
                btnStartInventory.setEnabled(false);
                btnStopInventory.setEnabled(true);
                btnClear.setEnabled(false);
                handler.postDelayed(mRunnable, 50);
                break;

            case R.id.btn_stopInventory:
                mTvLucencyView.setVisibility(View.GONE);
                listView.setEnabled(true);
                doTagInventory();
                isInventory = false;
                btnClear.setEnabled(true);
                runTimeHandler.removeCallbacks(runnable);
                handler.removeCallbacks(mRunnable);
                btnStartInventory.setEnabled(true);
                btnStopInventory.setEnabled(false);
                btnClear.setEnabled(true);
                break;

            case R.id.btn_Clear:
                tv_moduleInfo.setText("");
                mTagList.clear();
                moduleController.tagList.clear();
                btnStartInventory.setEnabled(true);
                btnStopInventory.setEnabled(false);
                btnClear.setEnabled(false);
                tagAdapter.notifyDataSetChanged();
                tvSystemTimes.setText("");
                break;
            case R.id.tv_lucencyView:
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(R.string.AlertDialog_title);
                    builder.setMessage(R.string.AlertDialog_message);
                    builder.setNegativeButton(R.string.AlertDialog_sure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create().show();
                break;
        }

    }

    public void doTagInventory() {
        isInventory = !isInventory;
        if (isInventory) {
            moduleController.moduleInventoryTag();
        } else {
            moduleController.moduleStopInventoryTag();
        }
    }

    /*
     * 将秒数转为时分秒
     * */
    public String changeTime(int second) {
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second % 3600;
        if (second > 3600) {
            h = second / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
        }
        return h + ":" + d + ":" + s + "";
    }
}
