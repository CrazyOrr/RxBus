package com.github.crazyorr.rxbus.app.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.github.crazyorr.rxbus.app.Consts;
import com.github.crazyorr.rxbus.app.MyRxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

public abstract class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    private Runnable runnable;

    @Override
    public void onResume() {
        super.onResume();
        MyRxBus.get().register(this);
        if (runnable != null) {
            runnable.run();
            runnable = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MyRxBus.get().unregister(this);
    }

    protected void runAfterOnResume(Runnable runnable) {
        this.runnable = runnable;
    }

    @Subscribe(
            thread = EventThread.IMMEDIATE,
            tags = {@Tag(Consts.TAG_HELLO)}
    )
    public void baseReceive(String msg) {
        Log.e(TAG, "BaseFragment received: " + msg);
    }
}
