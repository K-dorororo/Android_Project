package com.iot.matzip_project;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MatZip_Adapter extends BaseAdapter {
    ArrayList<MatZipAdapterData> list = new ArrayList<MatZipAdapterData>();
    private final Activity activity;

    public MatZip_Adapter(Activity activity) {
        this.activity = activity;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, @Nullable View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if(view == null) {
            Log.i("test", "list view test");
            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.activity_mat_zip_info, viewGroup, false);
        }

        ImageView mainImage = (ImageView) view.findViewById(R.id.imageView);
        TextView nameTextView = (TextView) view.findViewById(R.id.storeName);
        TextView categoryTv = (TextView) view.findViewById(R.id.category);
        TextView commentTv = (TextView) view.findViewById(R.id.comment);
        TextView menuTv = (TextView) view.findViewById(R.id.category_menu);
        ImageView sojuImage = (ImageView) view.findViewById(R.id.imagesoju);
        ImageView beerImage = (ImageView) view.findViewById(R.id.imagebeer);

        MatZipAdapterData listdata = list.get(position);

        //mainImage.setImageResource(listdata.getMAINIMAGE());
        nameTextView.setText(listdata.getSTORENAME());
        commentTv.setText(listdata.getCOMMENT());
        categoryTv.setText(listdata.getCATEGORY());
        menuTv.setText(listdata.getMENU());
        return view;
    }

    public void addStore(String storeName, String comment, String sp_category, String menu_category) {
        MatZipAdapterData listData = new MatZipAdapterData();

        listData.setSTORENAME(storeName);
        listData.setCOMMENT(comment);
        listData.setCATEGORY(sp_category);
        listData.setMENU(menu_category);

        list.add(listData);
    }

}
