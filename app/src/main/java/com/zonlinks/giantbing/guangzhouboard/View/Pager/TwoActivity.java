package com.zonlinks.giantbing.guangzhouboard.View.Pager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.zonlinks.giantbing.guangzhouboard.HttpClient.HttpCilent;
import com.zonlinks.giantbing.guangzhouboard.R;
import com.zonlinks.giantbing.guangzhouboard.Util.Image.ImageUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TwoActivity extends AppCompatActivity {
    @BindView(R.id.two_btn)
    Button twoBtn;
    private ViewPager viewPager;
    private LinearLayout two_layout;
    private List<ImageView> imageViews;
    private int pagerWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        two_layout = (LinearLayout) findViewById(R.id.activity_two);

        initData();

        viewPager.setOffscreenPageLimit(3);
        pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 3.0f / 5.0f);
        ViewGroup.LayoutParams lp = viewPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }
        viewPager.setLayoutParams(lp);
        viewPager.setPageMargin(-50);
        two_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return viewPager.dispatchTouchEvent(motionEvent);
            }
        });
        viewPager.setPageTransformer(true, new GallyPageTransformer());
        viewPager.setAdapter(new MyViewPager(imageViews));

        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    private void initData() {
        imageViews = new ArrayList<>();
        final ImageView first = new ImageView(TwoActivity.this);
        final ImageView second = new ImageView(TwoActivity.this);
        final ImageView third = new ImageView(TwoActivity.this);
        final ImageView fourth = new ImageView(TwoActivity.this);

        HttpCilent.putimg(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    byte data[] = response.body().bytes();
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    first.setImageBitmap(ImageUtil.getReverseBitmapById(bitmap, TwoActivity.this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        },"large/69dba6f5ly1fhnfotqiawj20go0oodrf.jpg");
        HttpCilent.putimg(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    byte data[] = response.body().bytes();
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    second.setImageBitmap(ImageUtil.getReverseBitmapById(bitmap, TwoActivity.this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        },"large/69dba6f5jw1f37rmp97t9j20go0op478.jpg");
        HttpCilent.putimg(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    byte data[] = response.body().bytes();
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    third.setImageBitmap(ImageUtil.getReverseBitmapById(bitmap, TwoActivity.this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        },"large/69dba6f5jw1er2p8hwn8wj20rs155q7q.jpg");
        HttpCilent.putimg(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    byte data[] = response.body().bytes();
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    fourth.setImageBitmap(ImageUtil.getReverseBitmapById(bitmap, TwoActivity.this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        },"large/69dba6f5jw1ef72e97pdrj20go0oon2a.jpg");


//        ImageView a = new ImageView(TwoActivity.this);
//        a.setImageBitmap(ImageUtil.getReverseBitmapById(image,TwoActivity.this));




//        imageViews.add(a);
        imageViews.add(first);
        imageViews.add(second);
        imageViews.add(third);
        imageViews.add(fourth);

    }

    class ItemOnclick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(TwoActivity.this, "当前点击了图片" + position, Toast.LENGTH_SHORT).show();
        }
    }

}
