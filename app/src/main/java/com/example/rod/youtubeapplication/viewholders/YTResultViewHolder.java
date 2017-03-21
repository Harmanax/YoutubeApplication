package com.example.rod.youtubeapplication.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rod.youtubeapplication.R;
import com.example.rod.youtubeapplication.interfaces.OnResultSelectedListener;
import com.example.rod.youtubeapplication.models.YTResult;


public class YTResultViewHolder extends RecyclerView.ViewHolder {
    private TextView video;
    private TextView title;
    private TextView author;
    private TextView descr;
    private TextView publi;
    private ImageView thumb;

    private OnResultSelectedListener onResultSelectedListener;


    public YTResultViewHolder(View itemView) {
        super(itemView);
        video = (TextView) itemView.findViewById(R.id.video);
        title = (TextView) itemView.findViewById(R.id.title);
        author = (TextView) itemView.findViewById(R.id.author);
        descr = (TextView) itemView.findViewById(R.id.descr);
        publi = (TextView) itemView.findViewById(R.id.publi);
        thumb = (ImageView) itemView.findViewById(R.id.thumb);
    }

    public void bind(final YTResult ytr) {
        video.setText(ytr.getVideo());
        title.setText(ytr.getTitle());
        author.setText(ytr.getAuthor());
        descr.setText(ytr.getDescr());
        publi.setText(ytr.getPubli());
        thumb.setImageBitmap(ytr.getThumb());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onResultSelectedListener == null) {
                    return;
                }
                onResultSelectedListener.onResultSelected(ytr);
            }
        });
    }

    public void setOnResultSelectedListener(OnResultSelectedListener listener) {
        onResultSelectedListener = listener;
    }
}
