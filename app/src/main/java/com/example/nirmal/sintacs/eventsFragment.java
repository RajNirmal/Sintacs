package com.example.nirmal.sintacs;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.SingleLineTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

import static android.support.design.R.attr.layoutManager;
import static android.support.design.R.attr.singleChoiceItemLayout;

/**
 * Created by nirmal on 13/7/17.
 */

public class eventsFragment extends Fragment {
    RecyclerView Tech_Events, Non_Tech_Events;
    String eventNames[] = {"Coding","Web design","Debugging","Google it","Paper Presentation"};
    String eventNames2[] = {"Game Room","Extempore"};
    FirebaseStorage storage = FirebaseStorage.getInstance();
    ImageView appEventImage;
    GifImageView loadImage;
    TextView tech_events,non_tech_events,online_app;
    ArrayList<ImageDataClass> datas,datas2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View subView = inflater.inflate(R.layout.content_main,container,false);
        Tech_Events = (RecyclerView)subView.findViewById(R.id.tech_events);
        Non_Tech_Events = (RecyclerView)subView.findViewById(R.id.non_tech_events);
        appEventImage = (ImageView) subView.findViewById(R.id.app_event);
        loadImage = (GifImageView) subView.findViewById(R.id.loadImage);
        tech_events = (TextView) subView.findViewById(R.id.tech_names);
        non_tech_events = (TextView) subView.findViewById(R.id.non_tech_names);
        online_app = (TextView) subView.findViewById(R.id.app_name);
        showLoad();
//        Glide.with(getActivity().getApplicationContext()).asGif().load(R.raw.gifs).into(loadImage);
        //        Glide.with(getActivity().getApplicationContext()).load(R.drawable.gifs).into(loadImage);
        Tech_Events.setHasFixedSize(false);
        Non_Tech_Events.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this.getActivity());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        Tech_Events.setLayoutManager(layoutManager);
        Tech_Events.setItemAnimator(new DefaultItemAnimator());
        Non_Tech_Events.setLayoutManager(layoutManager2);
        Non_Tech_Events.setItemAnimator(new DefaultItemAnimator());
        setAdapter1();
        setAdapter2();
        appEventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singletonClass.flag = 7;
                ((MainActivity)getActivity()).goToFragment();
            }
        });
        return subView;
    }

    public void showLoad(){
        Tech_Events.setVisibility(View.GONE);
        Non_Tech_Events.setVisibility(View.GONE);
        tech_events.setVisibility(View.GONE);
        non_tech_events.setVisibility(View.GONE);
        online_app.setVisibility(View.GONE);
        appEventImage.setVisibility(View.GONE);
        loadImage.setVisibility(View.VISIBLE);
    }

    public void showImages(){
        if(finalFlag == 2){
            Tech_Events.setVisibility(View.VISIBLE);
            Non_Tech_Events.setVisibility(View.VISIBLE);
            tech_events.setVisibility(View.VISIBLE);
            non_tech_events.setVisibility(View.VISIBLE);
            online_app.setVisibility(View.VISIBLE);
            appEventImage.setVisibility(View.VISIBLE);
            loadImage.setVisibility(View.GONE);
        }
    }

    public void setAdapter1(){

        datas = new ArrayList<>();
        for(int i=0;i<5;i++){
            final String x = eventNames[i];
            StorageReference storageRef = storage.getReferenceFromUrl("gs://testimages-6f71f.appspot.com").child(singletonClass.techEventImagesNames[i]);
            try {
                final File localFile = File.createTempFile(String.valueOf(i)+"xyz", "jpg");
                storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                        Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
                        bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        datas.add(new ImageDataClass(bitmap,x));
                        flagCount++;
                        if (flagCount == 5) {
                            ImageAdapter adapter = new ImageAdapter(datas,eventsFragment.this);
                            Tech_Events.setAdapter(adapter);
                            finalFlag++;
                            showImages();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getActivity(),exception.toString(),Toast.LENGTH_SHORT).show();
                        bitmap = null;
                    }
                });
            } catch (IOException e ) {
                Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                bitmap = null;
            }

        }

    }
    public void setAdapter2(){

        datas2 = new ArrayList<>();
        for(int i=0;i<2;i++){
            final String x = eventNames2[i];
            StorageReference storageRef = storage.getReferenceFromUrl("gs://testimages-6f71f.appspot.com").child(singletonClass.nonTechEventImagesNames[i]);
            try {
                final File localFile = File.createTempFile(String.valueOf(i)+"xyzl", "jpg");
                storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                        Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
                        bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        datas2.add(new ImageDataClass(bitmap,x));
                        flagCount2++;
                        if (flagCount2 > 1) {
                            ImageAdapter adapter = new ImageAdapter(datas2,eventsFragment.this);
                            Non_Tech_Events.setAdapter(adapter);
                            finalFlag++;
                            showImages();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getActivity(),exception.toString(),Toast.LENGTH_SHORT).show();
                        bitmap = null;
                    }
                });
            } catch (IOException e ) {
                Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                bitmap = null;
            }

        }
    }
    int flagCount = 0;
    int flagCount2 = 0;
    int finalFlag = 0;
    public void goToNext(final String eventName){
        singletonClass.flag = findEventID(eventName);
//        Toast.makeText(getActivity(),eventName+String.valueOf(singletonClass.flag),Toast.LENGTH_SHORT).show();
        ((MainActivity)getActivity()).goToFragment();
//
    }

    public int findEventID(final String events){
        int out = 0;
        if(events.contains("Coding")){
            out = 0;
        }else if(events.contains("Web")){
            out = 1;
        }else if(events.contains("Debug")){
            out = 2;
        }else if(events.contains("Google")){
            out = 3;
        }else if(events.contains("Paper")){
            out = 4;
        }else if(events.contains("Game")){
            out = 5;
        }else if(events.contains("Extemp")){
            out = 6;
        }
        return out;
    }

    Bitmap bitmap;


}

