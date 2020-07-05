package com.cooey.datingapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cooey.datingapp.db.ProfileEntity;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LikedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int resourceLayout;
    private Context mContext;
    private List<ProfileEntity> mItemsList;

    public LikedListAdapter(Context context, int resource, ArrayList<ProfileEntity> items) {
        this.resourceLayout =   resource;
        mItemsList          =   items;
        mContext    =   context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        RecyclerView.ViewHolder holder=new ViewHolder(itemView);
        holder.setIsRecyclable(false);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolderObj = (ViewHolder) holder;
        try{
            viewHolderObj.age_tv.setText(mItemsList.get(position).getAge());
            viewHolderObj.email_tv.setText(mItemsList.get(position).getEmail());
            viewHolderObj.favorite_tv.setText(mItemsList.get(position).getFavoriteColor());
            viewHolderObj.name_tv.setText(mItemsList.get(position).getName());
            viewHolderObj.gender_tv.setText(mItemsList.get(position).getGender());
            viewHolderObj.last_seen_tv.setText(mItemsList.get(position).getLastSeen());
            viewHolderObj.phone_tv.setText(mItemsList.get(position).getPhone());

            String url = mItemsList.get(position).getPicture().replace("http","https");
//            ProgressBar progressBar =  (ProgressBar)convertView.findViewById(R.id.id_photo_pb);
//            progressBar.setVisibility(View.VISIBLE);
//            imageView.setVisibility(View.GONE);
            Glide.with(mContext).load(url).timeout(60000).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                    progressBar.setVisibility(View.GONE);
//                    imageView.setVisibility(View.VISIBLE);
                    viewHolderObj.imageView.setBackground(mContext.getDrawable(R.drawable.ic_launcher_background));

                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

//                    progressBar.setVisibility(View.GONE);
//                    imageView.setVisibility(View.VISIBLE);
                    viewHolderObj.imageView.setImageDrawable(resource);

                    return false;
                }

            }).into(viewHolderObj.imageView);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView age_tv;
        private TextView email_tv;
        private TextView favorite_tv;
        private TextView last_seen_tv;
        private TextView phone_tv;
        private TextView gender_tv;
        private ImageView imageView;
        private TextView name_tv;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
//            TextView idTv                       =   view.findViewById(R.id.id_tv);
            email_tv                    =    view.findViewById(R.id.id_email_tv);
            age_tv                     =   view.findViewById(R.id.id_age_tv);
            favorite_tv            =   view.findViewById(R.id.id_favorite_color);
            last_seen_tv                =   view.findViewById(R.id.id_last_seen);
            phone_tv                    =   view.findViewById(R.id.id_phone_no_tv);
            gender_tv                  =   view.findViewById(R.id.id_gender_tv);
             imageView                 =   view.findViewById(R.id.id_photo_iv);
            name_tv                     =   view.findViewById(R.id.id_name_tv);


        }

        @Override
        public void onClick(View view) {
        }
    }
}
