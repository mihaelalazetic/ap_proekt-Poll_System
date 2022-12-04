package com.lazetic.proekt_ap;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CreatePollFragment extends Fragment {



    public CreatePollFragment() {
        // Required empty public constructor
    }

    public static CreatePollFragment newInstance(String param1, String param2) {
        CreatePollFragment fragment = new CreatePollFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_poll, container, false);

        Button startPollBtn = (Button) view.findViewById(R.id.startPollSwitch);

        startPollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.admin_fragment_container,new StartPollFragment());
                fr.commit();
            }
        });

        return  view;
    }
}