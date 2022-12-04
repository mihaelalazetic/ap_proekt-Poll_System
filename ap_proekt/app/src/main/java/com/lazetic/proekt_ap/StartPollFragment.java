package com.lazetic.proekt_ap;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class StartPollFragment extends Fragment {


    public StartPollFragment() {
        // Required empty public constructor
    }

    public static StartPollFragment newInstance(String param1, String param2) {
        StartPollFragment fragment = new StartPollFragment();
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

        View view = inflater.inflate(R.layout.fragment_start_poll, container, false);

        Button createPollBtn = (Button) view.findViewById(R.id.createPollSwitch);

        createPollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.admin_fragment_container,new CreatePollFragment());
                fr.commit();
            }
        });

        Button startPollBtn = (Button) view.findViewById(R.id.startPollBtn);

        startPollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner selectedPoll = (Spinner) view.findViewById(R.id.listOfPolls);
                String selectedPoll_data = selectedPoll.getSelectedItem().toString();

                String sql = "INSERT INTO poll_logs(poll_name,active) VALUES('"+selectedPoll_data+"','1');";
                ((AdminActivity)getActivity()).insertInDB(sql);

                ArrayList<String> activePolls = ((AdminActivity)getActivity()).getActivePolls(view);
                ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, activePolls);

                ListView listView = (ListView) view.findViewById(R.id.activePolls);
                listView.setAdapter(adapter);

                Intent intent = new Intent(getActivity(),AdminActivity.class);
                intent.putStringArrayListExtra("activePolls",activePolls);
                startActivity(intent);
            }
        });

        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Spinner spinner = getView().findViewById(R.id.listOfPolls);
//        spinner.setOnItemSelectedListener(this);
        Intent intent = getActivity().getIntent();
        List<String> pollNames = intent.getStringArrayListExtra("pollNames");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, pollNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}