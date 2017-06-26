package com.github.crazyorr.rxbus.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.crazyorr.rxbus.app.Consts;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.app.R;
import com.hwangjr.rxbus.thread.EventThread;

public class ReceiverFragment extends BaseFragment {
    private static final String KEY_TITLE = "KEY_TITLE";

    private TextView tvReceived;

    public static ReceiverFragment newInstance(String title) {
        ReceiverFragment fragment = new ReceiverFragment();
        Bundle arguments = new Bundle();
        arguments.putString(KEY_TITLE, title);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receiver, container, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        Bundle bundle = getArguments();
        tvTitle.setText(bundle.getString(KEY_TITLE) + " Receive:");
        tvReceived = (TextView) view.findViewById(R.id.tv_received);
        return view;
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {@Tag(Consts.TAG_HELLO)}
    )
    public void receive(String msg) {
        tvReceived.setText(msg);
    }
}
