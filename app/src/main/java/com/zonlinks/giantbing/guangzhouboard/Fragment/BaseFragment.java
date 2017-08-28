package com.zonlinks.giantbing.guangzhouboard.Fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by P on 2017/8/7.
 */

public class BaseFragment extends Fragment {
    protected SharedPreferences prefs = null;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        prefs = getActivity().getSharedPreferences("classroom.zonlinks.com.classroom", MODE_PRIVATE);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {0
//        super.onActivityResult(requestCode, resultCode, data);
////        if (resultCode != RESULT_OK) {
////            return;
////        }
////        switch (requestCode) {
////            case TAKE_PHOTO:
//////                ImageUtil.compressImageByPixel(imgurl, new ImageInterface() {
//////
//////                    @Override
//////                    public void onfinsh() {
//////                        // getsecondeFileData(homeworkSecondTitle.getText().toString());
//////                        imgurl = null;
//////                    }
//////                });
////
////                break;
////
////            default:
////                break;
////        }
//    }
}
