package org.maktab.beatbox.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import org.maktab.beatbox.R;
import org.maktab.beatbox.controller.activity.SingleFragmentActivity;
import org.maktab.beatbox.controller.fragment.BeatBoxFragment;
import org.maktab.beatbox.controller.fragment.MusicPlayerFragment;

public class BeatBoxActivity extends SingleFragmentActivity {


//    @Override
//    public Fragment createFragment() {
//        return BeatBoxFragment.newInstance();
//    }

    @Override
    public Fragment createFragment() {
        return MusicPlayerFragment.newInstance();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_single_fragment;
    }
}