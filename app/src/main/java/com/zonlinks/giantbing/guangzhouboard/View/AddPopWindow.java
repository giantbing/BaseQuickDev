package com.zonlinks.giantbing.guangzhouboard.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.zonlinks.giantbing.guangzhouboard.Excute.MainChangeSkinExcute;
import com.zonlinks.giantbing.guangzhouboard.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by P on 2017/9/5.
 */

public class AddPopWindow extends PopupWindow {
    private View conentView;

    public AddPopWindow(final Activity context, MainChangeSkinExcute excute) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.chosse_skin, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w / 4 + 50);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
       // this.setAnimationStyle(R.style.AnimationPreview);
        ViewHolder viewHolder = new ViewHolder(conentView);
        viewHolder.skinBlue.setOnClickListener(initClick(excute));
        viewHolder.skinOrginN.setOnClickListener(initClick(excute));
        viewHolder.skinRed.setOnClickListener(initClick(excute));
        viewHolder.skinGreenN.setOnClickListener(initClick(excute));
        viewHolder.skinYellow.setOnClickListener(initClick(excute));
        viewHolder.skinPepuleN.setOnClickListener(initClick(excute));

    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
        } else {
            this.dismiss();
        }
    }
    public void dismissPopupWindow(){
        this.dismiss();
    }
    static class ViewHolder {
        @BindView(R.id.skin_blue)
        RadioButton skinBlue;
        @BindView(R.id.skin_orgin_n)
        RadioButton skinOrginN;
        @BindView(R.id.skin_red)
        RadioButton skinRed;
        @BindView(R.id.skin_green_n)
        RadioButton skinGreenN;
        @BindView(R.id.skin_yellow)
        RadioButton skinYellow;
        @BindView(R.id.skin_pepule_n)
        RadioButton skinPepuleN;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    private View.OnClickListener initClick(final MainChangeSkinExcute excute){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.skin_blue:
                        excute.change2Blue();
                        dismissPopupWindow();
                        break;
                    case R.id.skin_orgin_n:
                        excute.change2Orgin();
                        dismissPopupWindow();
                        break;
                    case R.id.skin_red:
                        excute.change2Red();
                        dismissPopupWindow();
                        break;
                    case R.id.skin_green_n:
                        excute.change2Green();
                        dismissPopupWindow();
                        break;
                    case R.id.skin_yellow:
                        excute.change2Yellow();
                        dismissPopupWindow();
                        break;
                    case R.id.skin_pepule_n:
                        excute.change2Puper();
                        dismissPopupWindow();
                        break;
                }
            }
        };
        return listener;
    }
}
