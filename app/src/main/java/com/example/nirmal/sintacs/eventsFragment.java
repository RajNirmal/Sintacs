package com.example.nirmal.sintacs;

import android.app.Fragment;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.support.design.R.attr.layoutManager;

/**
 * Created by nirmal on 13/7/17.
 */

public class eventsFragment extends Fragment {
    RecyclerView Tech_Events, Non_Tech_Events;
    String names[] = {"meliodas.png","FT.jpg","greninja.jpg"};
    String eventNames[] = {"Coding","Debugging","Web design"};
    String names2[] = {"main_img_kirito.png","rukario_old.jpg"};
    String eventNames2[] = {"Extempore","Game Room"};
    FirebaseStorage storage = FirebaseStorage.getInstance();
    ArrayList<ImageDataClass> datas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View subView = inflater.inflate(R.layout.content_main,container,false);
        Tech_Events = (RecyclerView)subView.findViewById(R.id.tech_events);
        Non_Tech_Events = (RecyclerView)subView.findViewById(R.id.non_tech_events);
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
        return subView;
    }

    public void setAdapter2(){
        datas = new ArrayList<>();
        for(int i=0;i<2;i++){
            final String x = eventNames2[i];
            StorageReference storageRef = storage.getReferenceFromUrl("gs://testimages-6f71f.appspot.com").child(names2[i]);
            try {
                final File localFile = File.createTempFile(String.valueOf(i)+"xyzl", "jpg");
                storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
                        bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        datas.add(new ImageDataClass(bitmap,x));
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

//            datas.add(new ImageDataClass(myMap,eventNames[i]));
        }
        ImageAdapter adapter = new ImageAdapter(datas);
        Non_Tech_Events.setAdapter(adapter);
    }
    public void setAdapter1(){
        datas = new ArrayList<>();
        for(int i=0;i<3;i++){
            final String x = eventNames[i];
            StorageReference storageRef = storage.getReferenceFromUrl("gs://testimages-6f71f.appspot.com").child(names[i]);
            try {
                final File localFile = File.createTempFile(String.valueOf(i)+"xyz", "jpg");
                storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
                        bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        datas.add(new ImageDataClass(bitmap,x));
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

//            datas.add(new ImageDataClass(myMap,eventNames[i]));
        }
        ImageAdapter adapter = new ImageAdapter(datas);
        Tech_Events.setAdapter(adapter);
    }

    Bitmap bitmap;

    public Bitmap getImage(String URI, int i){

        StorageReference storageRef = storage.getReferenceFromUrl("gs://testimages-6f71f.appspot.com").child(URI);
        try {
            final File localFile = File.createTempFile(String.valueOf(i)+"xyz", "jpg");
            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
                    bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
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
        return bitmap;
    }
}

