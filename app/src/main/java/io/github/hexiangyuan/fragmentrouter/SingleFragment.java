package io.github.hexiangyuan.fragmentrouter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hexiangyuan on 2017/8/29.
 * Feature:请一定要补全功能
 */

public class SingleFragment extends Fragment {
    private int index;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("index");
        Log.e("index" + index, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toolbar toolbar = getView().findViewById(R.id.toolbar);
        toolbar.setTitle(String.valueOf(index));
        getView().findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).start();

            }
        });

        getView().findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).onBackPressed();
            }
        });
        getView().findViewById(R.id.replace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replace();
            }
        });
    }

    public static SingleFragment newInstance(int index) {
        Bundle args = new Bundle();
        args.putInt("index", index);
        SingleFragment fragment = new SingleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("index" + index, "onDestroy");
    }
}
