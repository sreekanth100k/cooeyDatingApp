package com.cooey.datingapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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
            LayoutInflater inflater     =   (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView                 =   inflater.inflate(resourceLayout, parent, false);

            TextView idTv                       =   convertView.findViewById(R.id.id_tv);
            TextView emailTv                    =    convertView.findViewById(R.id.id_email_tv);
            TextView ageTv                      =   convertView.findViewById(R.id.id_age_tv);
            TextView favoriteColorTv            =   convertView.findViewById(R.id.id_favorite_color);
            TextView lastSeeenTv                =   convertView.findViewById(R.id.id_last_seen);
            TextView phoneTv                    =   convertView.findViewById(R.id.id_phone_no_tv);
            TextView genderTv                   =   convertView.findViewById(R.id.id_gender_tv);
            ImageView imageView                 =   convertView.findViewById(R.id.id_photo_iv);
            TextView nameTv                     =   convertView.findViewById(R.id.id_name_tv);

            String email            =   mItems.get(position).email;
            Integer age             =   mItems.get(position).age;
            String gender           =   mItems.get(position).gender;
            String url              =   mItems.get(position).picture;
            String lastSeen         =   mItems.get(position).lastSeen;
            String name             =   mItems.get(position).name;
            String favoriteColor    =   mItems.get(position).favoriteColor;
            String id               =   mItems.get(position).id;
            Geolocation geoLocation =   mItems.get(position).geoLocation;
            String phone            =   mItems.get(position).phone;
            emailTv.setText(email);
            idTv.setText(id);
           ageTv.setText(age);
            genderTv.setText(gender);
            phoneTv.setText(phone);
            favoriteColorTv.setText(favoriteColor);
            lastSeeenTv.setText(lastSeen);
            nameTv.setText(name);


            url = url.replace("http","https");
//            ProgressBar progressBar =  (ProgressBar)convertView.findViewById(R.id.id_photo_pb);
//            progressBar.setVisibility(View.VISIBLE);
//            imageView.setVisibility(View.GONE);
            Glide.with(mContext).load(url).timeout(60000).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                    progressBar.setVisibility(View.GONE);
//                    imageView.setVisibility(View.VISIBLE);
                    imageView.setBackground(mContext.getDrawable(R.drawable.ic_launcher_background));

                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

//                    progressBar.setVisibility(View.GONE);
//                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageDrawable(resource);

                    return false;
                }

                }).into(imageView);
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
