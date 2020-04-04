package com.antnzr.alef.images;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antnzr.alef.R;

import java.util.List;

import static com.antnzr.alef.utils.ImageDownloader.setImage;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {

    private List<String> images;
    private OnImageClickListener listener;

    public ImagesAdapter(List<String> images, OnImageClickListener listener) {
        this.images = images;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImagesViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_image, null, false),
                listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        setImage(holder.image, images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView image;
        private OnImageClickListener listener;

        public ImagesViewHolder(@NonNull View itemView, OnImageClickListener listener) {
            super(itemView);
            this.listener = listener;

            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onImageClick(v, images.get(getAdapterPosition()));
        }
    }

    public interface OnImageClickListener {
        void onImageClick(View view, String imageUrl);
    }
}
