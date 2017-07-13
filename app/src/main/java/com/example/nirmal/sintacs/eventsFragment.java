package com.example.nirmal.sintacs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.support.design.R.attr.layoutManager;

/**
 * Created by nirmal on 13/7/17.
 */

public class eventsFragment extends Fragment {
    RecyclerView Tech_Events, Non_Tech_Events;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View subView = inflater.inflate(R.layout.content_main,container,false);
        Tech_Events = (RecyclerView)subView.findViewById(R.id.tech_events);
        Non_Tech_Events = (RecyclerView)subView.findViewById(R.id.non_tech_events);
        Tech_Events.setHasFixedSize(false);
        Non_Tech_Events.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        Tech_Events.setLayoutManager(layoutManager);
        Tech_Events.setItemAnimator(new DefaultItemAnimator());
        Non_Tech_Events.setLayoutManager(layoutManager);
        Non_Tech_Events.setItemAnimator(new DefaultItemAnimator());
        return subView;
    }
}
