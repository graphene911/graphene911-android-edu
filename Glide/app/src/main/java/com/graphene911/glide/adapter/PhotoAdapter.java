package com.graphene911.glide.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.graphene911.glide.PhotoActivity;
import com.graphene911.glide.R;
import com.graphene911.glide.model.Photo;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    Context context;
    List<Photo> photoList;

    public PhotoAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_row, parent, false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder holder, int position) {
        Photo photo = photoList.get(position);

        holder.txtTitle.setText(photo.title);
        holder.txtId.setText(photo.id+"");
        holder.txtAlbumId.setText(photo.albumId+"");

        // 이미지를 가져올땐 글라이드 라이브러리를 사용
        GlideUrl url = new GlideUrl(photo.thumbnailUrl, new LazyHeaders.Builder().addHeader("User-Agent","Android").build());
        Glide.with(context).load(url).into(holder.imgThumb);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView txtTitle;
        TextView txtId;
        TextView txtAlbumId;
        ImageView imgThumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtId = itemView.findViewById(R.id.txtId);
            txtAlbumId = itemView.findViewById(R.id.txtAlbumId);
            imgThumb = itemView.findViewById(R.id.imgThumb);

            imgThumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 썸네일 누르면 큰 이미지가 나오는 액티비티 실행

                    Intent intent = new Intent(context, PhotoActivity.class);

                    int index = getAdapterPosition();
                    Photo photo = photoList.get(index);

                    intent.putExtra("url", photo.url);

                    context.startActivity(intent);
                }
            });
        }
    }

}
