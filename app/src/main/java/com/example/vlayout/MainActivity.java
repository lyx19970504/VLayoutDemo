package com.example.vlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    String[] ITEM_NAMES = {"天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "飞猪旅行", "领金币", "拍卖", "分类"};
    int[] IMG_URLS = {R.mipmap.ic_tian_mao, R.mipmap.ic_ju_hua_suan, R.mipmap.ic_tian_mao_guoji, R.mipmap.ic_waimai, R.mipmap.ic_chaoshi, R.mipmap.ic_voucher_center, R.mipmap.ic_travel, R.mipmap.ic_tao_gold, R.mipmap.ic_auction, R.mipmap.ic_classify};
    int[] GRID_URL = {R.mipmap.flashsale1, R.mipmap.flashsale2, R.mipmap.flashsale3, R.mipmap.flashsale4};
    int[] ITEM_URL = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        DelegateAdapter adapter = new DelegateAdapter(layoutManager,true);
        mRecyclerView.setLayoutManager(layoutManager);
        BaseDelegateAdapter bannerAdapter = new BaseDelegateAdapter(new LinearLayoutHelper(),R.layout.vlayout_banner,1){
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                List<Integer> list = new ArrayList<>();
                list.add(R.drawable.image1);
                list.add(R.drawable.image2);
                list.add(R.drawable.image3);
                list.add(R.drawable.image4);
                list.add(R.drawable.image5);
                list.add(R.drawable.image6);
                Banner banner = holder.itemView.findViewById(R.id.banner);
                //设置banner样式
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图像列表
                banner.setImages(list);
                //设置banner动画
                banner.setBannerAnimation(Transformer.DepthPage);
                //设置是否自动播放
                banner.isAutoPlay(true);
                //设置轮播时间
                banner.setDelayTime(3000);
                //设置指示器位置
                banner.setIndicatorGravity(BannerConfig.CENTER);
                banner.start();
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(getApplicationContext(), "banner点击了" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        adapter.addAdapter(bannerAdapter);

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        gridLayoutHelper.setPadding(0,16,0,0);
        gridLayoutHelper.setVGap(10);
        gridLayoutHelper.setHGap(0);    //子元素之间的水平间距


        BaseDelegateAdapter menuAdapter = new BaseDelegateAdapter(gridLayoutHelper,R.layout.vlayout_menu,10){
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.setTextView(R.id.tv_menu_title_home,ITEM_NAMES[position]);
                holder.setImageView(R.id.iv_menu_home,IMG_URLS[position]);
                holder.getView(R.id.ll_menu_home).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), ITEM_NAMES[position], Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        adapter.addAdapter(menuAdapter);

        BaseDelegateAdapter newsAdapter = new BaseDelegateAdapter(new LinearLayoutHelper(),R.layout.vlayout_news,1){
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                List<String> info1 = new ArrayList<>();
                info1.add("天猫超市最近发大活动啦，快来抢");
                info1.add("没有最便宜，只有更便宜！");

                List<String> info2 = new ArrayList<>();
                info2.add("这个是用来搞笑的，不要在意这写小细节！");
                info2.add("啦啦啦啦，我就是来搞笑的！");

                holder.setMarqueeView(R.id.marqueeView2,info2);
                holder.setMarqueeView(R.id.marqueeView1,info1);
                holder.setMarqueeView(R.id.marqueeView2,info2,R.anim.anim_bottom_in,R.anim.anim_top_out);
                holder.setMarqueeView(R.id.marqueeView1,info1,R.anim.anim_bottom_in,R.anim.anim_top_out);
                ((MarqueeView) holder.getView(R.id.marqueeView1)).setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                ((MarqueeView) holder.getView(R.id.marqueeView2)).setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        adapter.addAdapter(newsAdapter);
        for (int i=0;i<ITEM_URL.length;i++){
            BaseDelegateAdapter titleAdapter = new BaseDelegateAdapter(new LinearLayoutHelper(),R.layout.vlayout_title,1){
                @Override
                public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    holder.setImageView(R.id.iv,ITEM_URL[position]);
                }
            };
            adapter.addAdapter(titleAdapter);
            BaseDelegateAdapter gridAdapter = new BaseDelegateAdapter(new GridLayoutHelper(2),R.layout.vlayout_grid,4){
                @Override
                public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
                    int gridItem = GRID_URL[position];
                    ImageView imageView = holder.getView(R.id.iv);
                    Glide.with(getApplicationContext()).load(gridItem).into(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            adapter.addAdapter(gridAdapter);
        }
        mRecyclerView.setAdapter(adapter);
    }
}
