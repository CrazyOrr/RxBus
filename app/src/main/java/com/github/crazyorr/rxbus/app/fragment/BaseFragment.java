package com.github.crazyorr.rxbus.app.fragment;

import android.support.v4.app.Fragment;

import com.hwangjr.rxbus.RxBus;

public class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    private Runnable runnable;

    @Override
    public void onResume() {
        super.onResume();
        RxBus.get().register(this);
        if (runnable != null) {
            runnable.run();
            runnable = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        RxBus.get().unregister(this);
    }

    protected void runAfterOnResume(Runnable runnable) {
        this.runnable = runnable;
    }
}
