package com.mallcloud.rfiddemo_c.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.mallcloud.rfiddemo_c.adapter.ViewPagerAdapter;
import com.mallcloud.rfiddemo_c.fragment.content.LockFragment;
import com.mallcloud.rfiddemo_c.fragment.content.ReadFragment;
import com.mallcloud.rfiddemo_c.fragment.content.WriteFragment;
import com.mallcloud.rfidservicedemo.R;
import com.supoin.rfidservice.sdk.ModuleController;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class WriteLockFragment extends Fragment implements View.OnClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private FrameLayout writeLockView;
    private View writeView;
    private Button btnRead;
    private Button btnWrite;
    private Button btnLock;
    private ViewPager viewPager;
    private TextView tvPagerTitle;
    private String[] imageTitles;//标题集合
    private int[] imageResoure;
    private ArrayList<ImageView> imageList; //轮播的图片集合
    // 在values文件假下创建了pager_image_ids.xml文件，并定义了4张轮播图对应的id，用于点击事件
    private int[] imgae_ids = new int[]{R.id.pager_image1, R.id.pager_image2, R.id.pager_image3, R.id.pager_image4};
    private List<View> mDots;//小点
    private int previousPosition = 0;//前一个被选中的position

    private boolean isStop = false;//线程是否停止
    private static int PAGER_TIME = 5000;//间隔时间
    private Activity context;
    private boolean turnFlag = false;
    private ModuleController moduleController;
//    private LinearLayout mLlChooseTag;

//    private TextView mTvSingleTag;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WriteLockFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static WriteLockFragment newInstance(int index) {
        WriteLockFragment fragment = new WriteLockFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COLUMN_COUNT, String.valueOf(index));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moduleController = ModuleController.getInstance(context,new ModuleController.DataListener(){
            @Override
            public void onConnect(boolean isSuccess) {
                super.onConnect(isSuccess);
                if (isSuccess) {
                    turnFlag = true;
                }
            }
        });

       /* moduleController.create(context, new ModuleController.DataListener() {
            @Override
            public void onConnect(boolean isSuccess) {
                super.onConnect(isSuccess);
            }
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        writeLockView = new FrameLayout(getContext());
        writeLockView.setBackgroundColor(Color.RED);

        writeView = inflater.inflate(R.layout.base_write_page, null);
        writeLockView.addView(writeView);

        //默认启动时显示的页面
        getFragmentManager().beginTransaction().replace(R.id.write_page_content, ReadFragment.newInstance("read")).commit();
        initView();
        return writeLockView;
    }


    /*初始化view*/
    private void initView() {
        //读写锁菜单栏
        btnRead = writeView.findViewById(R.id.btn_read);
        btnWrite = writeView.findViewById(R.id.btn_write);
        btnLock = writeView.findViewById(R.id.btn_lock);
//        mLlChooseTag = writeView.findViewById(R.id.ll_chooseTag);
//        mTvSingleTag = writeView.findViewById(R.id.tv_singleTag);

//        mLlChooseTag.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnWrite.setOnClickListener(this);
        btnLock.setOnClickListener(this);

        viewPager = writeView.findViewById(R.id.vp_adv);
        tvPagerTitle = writeView.findViewById(R.id.tv_pager_title);
        initData(); //初始化数据
        initViewPager();//初始化view，设置适配器
        autoPlayView();  //开启线程，自动播放
    }


    //初始化数据（图片，标题，点击事件）
    private void initData() {
        //初始化标题列表和图片
        imageTitles = new String[]{" ", "", "", ""};
        imageResoure = new int[]{R.drawable.images1, R.drawable.images2, R.drawable.images3, R.drawable.images4};

        //添加图片到图片列表中
        imageList = new ArrayList<>();//
        for (int i = 0; i < imageTitles.length; i++) {//imageList.size();
            ImageView iv = new ImageView(writeView.getContext());
            iv.setBackgroundResource(imageResoure[i]); //设置图片
            iv.setId(imgae_ids[i]); //给图片添加id
            iv.setOnClickListener(new pagerImageOnClick());//设置图片点击事件
            imageList.add(iv);
        }
        //添加轮播点
        LinearLayout linearLayoutDots = (LinearLayout) writeView.findViewById(R.id.lineLayout_dot);
        mDots = addDots(linearLayoutDots, fromResToDrawable(writeView.getContext(), R.drawable.ic_layout_dot), imageList.size());
        //其中fromResToDrawable()方法是自定义的，目的是将资源文件转成Drawable
    }


    /**
     * 资源图片转Drawable
     *
     * @param context
     * @param resId
     * @return
     */
    public Drawable fromResToDrawable(Context context, int resId) {
        return context.getResources().getDrawable(resId);
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
    public void onClick(View view) {
        switch (view.getId()) {
           /* case R.id.ll_chooseTag:

                Toast.makeText(context, "点击了！", Toast.LENGTH_SHORT).show();
                if (TagList.length == 0){
                    return;
                }
                OptionPicker optionPicker = new OptionPicker(context, TagList);
                optionPicker.setCanceledOnTouchOutside(false);
                optionPicker.setDividerRatio(WheelView.DividerConfig.FILL);
                optionPicker.setShadowColor(Color.BLUE,40);
                optionPicker.setSelectedIndex(48);
                optionPicker.setCycleDisable(true);
                optionPicker.setTextSize(22);
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        mTvSingleTag.setText(item);

                    }
                });
                break;*/
            case R.id.btn_read:
                getFragmentManager().beginTransaction().replace(R.id.write_page_content, ReadFragment
                    .newInstance("read")).commit();
                btnRead.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.read_red, 0, 0);
                btnWrite.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.write_gray, 0, 0);
                btnLock.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.lock_gray, 0, 0);
                break;
            case R.id.btn_write:
                getFragmentManager().beginTransaction().replace(R.id.write_page_content, WriteFragment
                    .newInstance("write")).commit();
                btnRead.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.read_gray, 0, 0);
                btnWrite.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.write_red, 0, 0);
                btnLock.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.lock_gray, 0, 0);

                break;
            case R.id.btn_lock:
                getFragmentManager().beginTransaction().replace(R.id.write_page_content, LockFragment
                    .newInstance("lock")).commit();
                btnRead.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.read_gray, 0, 0);
                btnWrite.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.write_gray, 0, 0);
                btnLock.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.lock_red, 0, 0);
                break;
        }

    }

    private class pagerImageOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.pager_image1:
//                    Toast.makeText(writeView.getContext(), "图片1被点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pager_image2:
//                    Toast.makeText(writeView.getContext(), "图片2被点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pager_image3:
//                    Toast.makeText(writeView.getContext(), "图片3被点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pager_image4:
//                    Toast.makeText(writeView.getContext(), "图片4被点击", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }


    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(imageList, viewPager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //伪无限循环，滑到最后一张图片又从新进入第一张图片
                int newPosition = position % imageList.size();
                // 把当前选中的点给切换了, 还有描述信息也切换
                tvPagerTitle.setText(imageTitles[newPosition]);//图片下面设置显示文本
                //设置轮播点
                LinearLayout.LayoutParams newDotParams = (LinearLayout.LayoutParams) mDots.get(newPosition).getLayoutParams();
                newDotParams.width = 24;
                newDotParams.height = 24;

                LinearLayout.LayoutParams oldDotParams = (LinearLayout.LayoutParams) mDots.get(previousPosition).getLayoutParams();
                oldDotParams.width = 16;
                oldDotParams.height = 16;

                // 把当前的索引赋值给前一个索引变量, 方便下一次再切换.
                previousPosition = newPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
//        moduleController.close();
    }

    /**
     * 开启线程，自动播放
     */
    private void autoPlayView() {
        //自动播放图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    if (getActivity() == null) {
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                    SystemClock.sleep(PAGER_TIME);
                }
            }
        }).start();
    }


    /**
     * 动态添加一个点
     *
     * @param linearLayout 添加到LinearLayout布局
     * @param backgount    设置
     * @return
     */
    public int addDot(final LinearLayout linearLayout, Drawable backgount) {
        final View dot = new View(writeView.getContext());
        LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                            ViewGroup.LayoutParams.WRAP_CONTENT);
        dotParams.width = 16;
        dotParams.height = 16;
        dotParams.setMargins(4, 0, 4, 0);
        dot.setLayoutParams(dotParams);
        dot.setBackground(backgount);
        dot.setId(View.generateViewId());
        linearLayout.addView(dot);
        return dot.getId();
    }

    /**
     * 添加多个轮播小点到横向线性布局
     *
     * @param linearLayout
     * @param backgount
     * @param number
     * @return
     */
    public List<View> addDots(final LinearLayout linearLayout, Drawable backgount, int number) {
        List<View> dots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int dotId = addDot(linearLayout, backgount);
            dots.add(writeView.findViewById(dotId));
        }
        return dots;
    }
}
