package com.example.nirmal.sintacs;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by nirmal on 13/7/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
    private eventsFragment myFrag;
    private ArrayList<ImageDataClass> dataset;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView eventName;
        ImageView myImages;

        public CardView cardView;
        public MyViewHolder(View myItem){
            super(myItem);
            eventName = (TextView)myItem.findViewById(R.id.event_name);
            myImages = (ImageView)myItem.findViewById(R.id.myImageView);
            cardView = (CardView)myItem.findViewById(R.id.card_view);
            cardView.setOnClickListener(this);
            myImages.setOnClickListener(this);
            eventName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String x = String.valueOf(dataset.get(getAdapterPosition()).getText());
            myFrag.goToNext(x);
        }
    }
    public ImageAdapter(ArrayList<ImageDataClass> das, eventsFragment frag1){
        dataset = das;
        myFrag = frag1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_container,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(myView);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        TextView eventName = holder.eventName;
        ImageView myImages = holder.myImages;
        eventName.setText(dataset.get(position).getText());
        myImages.setImageBitmap(dataset.get(position).getMyImageBitMap());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
