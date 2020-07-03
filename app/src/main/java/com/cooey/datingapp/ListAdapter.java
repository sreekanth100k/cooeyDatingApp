package com.cooey.datingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter<ApiResponse> {

    private int resourceLayout;
    private Context mContext;
    private List<ApiResponse> mItems;

    public ListAdapter(Context context, int resource, List<ApiResponse> items){
        super(context, resource, items);
        this.resourceLayout =   resource;
        this.mContext       =   context;
        this.mItems         =   items;

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

        @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater vi;
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resourceLayout, parent, false);

            TextView textView = convertView.findViewById(R.id.id_tv);
            String email = mItems.get(position).email;
            textView.setText(email);
        }

        return convertView ;
    }

    @Nullable
    @Override
    public ApiResponse getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }
}
