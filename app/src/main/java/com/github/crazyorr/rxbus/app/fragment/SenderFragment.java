package com.github.crazyorr.rxbus.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.crazyorr.rxbus.app.Consts;
import com.github.crazyorr.rxbus.app.MyRxBus;
import com.hwangjr.rxbus.app.R;

public class SenderFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sender, container, false);

        final EditText etSent = (EditText) view.findViewById(R.id.et_sent);
        final EditText etCount = (EditText) view.findViewById(R.id.et_count);
        view.findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRxBus.get().post(Consts.TAG_HELLO, etSent.getText().toString(),
                        Integer.parseInt(etCount.getText().toString()));
            }
        });
        return view;
    }
}
