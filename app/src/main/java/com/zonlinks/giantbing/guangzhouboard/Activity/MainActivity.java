package com.zonlinks.giantbing.guangzhouboard.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.zonlinks.giantbing.guangzhouboard.R;
import com.zonlinks.giantbing.guangzhouboard.Util.AnimotionHelper;
import com.zonlinks.giantbing.guangzhouboard.Util.StartActivityHelper;
import com.zonlinks.giantbing.guangzhouboard.View.MyTestView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.myView)
    MyTestView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myView.setLisner(new MyTestView.clickLisner() {
            @Override
            public void onClick() {
                StartActivityHelper.startTwoActivityTraslate(MainActivity.this, AnimotionHelper.explodebundle(MainActivity.this));
            }
        });
    }
}
