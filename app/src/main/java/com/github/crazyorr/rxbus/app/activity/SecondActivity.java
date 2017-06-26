package com.github.crazyorr.rxbus.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.github.crazyorr.rxbus.app.Consts;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.app.R;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final EditText etSent = (EditText) findViewById(R.id.et_sent);
        final EditText etCount = (EditText) findViewById(R.id.et_count);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_send:
                        RxBus.get().post(Consts.TAG_HELLO, etSent.getText().toString(),
                                Integer.parseInt(etCount.getText().toString()));
                        break;
                    case R.id.btn_go_back:
                        finish();
                        break;
                }
            }
        };
        findViewById(R.id.btn_send).setOnClickListener(onClickListener);
        findViewById(R.id.btn_go_back).setOnClickListener(onClickListener);
    }
}
