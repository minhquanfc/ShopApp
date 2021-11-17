package com.poly.shopapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.poly.shopapp.R;
import com.poly.shopapp.model.SlidePhoto;

import java.util.List;

public class SlidePhotoAdapter extends PagerAdapter {
    private List<SlidePhoto> photoList;

    public SlidePhotoAdapter(List<SlidePhoto> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);
        ImageView img_photo = view.findViewById(R.id.imgphoto);
        SlidePhoto photo = photoList.get(position);
        img_photo.setImageResource(photo.getResoucreId());

        //add view
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
