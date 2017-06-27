package com.github.crazyorr.rxbus.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.crazyorr.rxbus.app.Consts;
import com.github.crazyorr.rxbus.app.MyRxBus;
import com.github.crazyorr.rxbus.app.fragment.ReceiverFragment;
import com.github.crazyorr.rxbus.app.fragment.SenderFragment;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.app.R;
import com.hwangjr.rxbus.thread.EventThread;

public class FirstActivity extends AppCompatActivity {
    public static final String FRAGMENT_TAG_0 = "Fragment 0";
    public static final String FRAGMENT_TAG_1 = "Fragment 1";
    public static final String FRAGMENT_TAG_2 = "Fragment 2";

    private TextView tvReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fl_1, new SenderFragment(), FRAGMENT_TAG_0)
                .add(R.id.fl_1, ReceiverFragment.newInstance(FRAGMENT_TAG_0), FRAGMENT_TAG_0)
                .add(R.id.fl_2, ReceiverFragment.newInstance(FRAGMENT_TAG_1), FRAGMENT_TAG_1)
                .add(R.id.fl_3, ReceiverFragment.newInstance(FRAGMENT_TAG_2), FRAGMENT_TAG_2)
                .commit();

        tvReceived = (TextView) findViewById(R.id.tv_received);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_switch_fragment:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fl_1, new SenderFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case R.id.btn_switch_activity:
                        startActivity(new Intent(FirstActivity.this, SecondActivity.class));
                        break;
                }
            }
        };
        findViewById(R.id.btn_switch_fragment).setOnClickListener(onClickListener);
        findViewById(R.id.btn_switch_activity).setOnClickListener(onClickListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        MyRxBus.get().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MyRxBus.get().unregister(this);
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {@Tag(Consts.TAG_HELLO)}
    )
    public void receive(String msg) {
        tvReceived.setText(msg);
    }
}
