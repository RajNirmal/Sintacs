package com.example.nirmal.sintacs;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleEventFragment extends Fragment {
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView imageView;
    TextView EventName, EventDesc, EventRules;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View subView = inflater.inflate(R.layout.activity_main_fragment,container,false);
        imageView = (ImageView) subView.findViewById(R.id.event_image);
        EventName = (TextView) subView.findViewById(R.id.main_heading);
        EventDesc = (TextView) subView.findViewById(R.id.event_desc);
        EventRules = (TextView) subView.findViewById(R.id.rules_content);
        collapsingToolbarLayout = (CollapsingToolbarLayout)subView.findViewById(R.id.collapserlayout);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.walderwebb);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimary)));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimaryDark)));
            }
        });
        setImage();
        setText(singletonClass.flag);
        return subView;
    }

    public void setText(int x){
        EventName.setText(singletonClass.ViewEventNames[x]);
        EventDesc.setText(singletonClass.ViewEventDesc[x]);
        EventRules.setText(singletonClass.ViewEventRules[x]);
    }

    public void setImage(){
        int x = singletonClass.flag;
        switch (x) {
            case 0:
//                imageView.setImageDrawable(getResources().getDrawable(R.drawable.valarcodulus));
                imageView.setBackgroundResource(R.drawable.valarcodulus);
                break;
            case 1:
                imageView.setBackgroundResource(R.drawable.walderwebb);
                break;
            case 2:
                imageView.setBackgroundResource(R.drawable.bugslayer);
                break;
            case 3:
                imageView.setBackgroundResource(R.drawable.threeeyedraven);
                break;
            case 4:
                imageView.setBackgroundResource(R.drawable.paper);
                break;
            case 5:
                imageView.setBackgroundResource(R.drawable.gameroom);
                break;
            case 6:
                imageView.setBackgroundResource(R.drawable.extempore);
                break;
            case 7:
                imageView.setBackgroundResource(R.drawable.onlineapp);
                break;

        }

    }
}
