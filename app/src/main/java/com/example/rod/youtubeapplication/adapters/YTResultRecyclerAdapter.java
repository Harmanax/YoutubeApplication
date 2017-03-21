package com.example.rod.youtubeapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.rod.youtubeapplication.R;
import com.example.rod.youtubeapplication.interfaces.OnResultSelectedListener;
import com.example.rod.youtubeapplication.models.ImageLoadTask;
import com.example.rod.youtubeapplication.models.YTResult;
import com.example.rod.youtubeapplication.models.YTResults;
import com.example.rod.youtubeapplication.viewholders.YTResultViewHolder;


public class YTResultRecyclerAdapter extends RecyclerView.Adapter<YTResultViewHolder> {
    private final YTResults results;
    private OnResultSelectedListener onResultSelectedListener;


    public YTResultRecyclerAdapter(YTResults pResults) {
        results = pResults;
        for (YTResult r : results) {
            new ImageLoadTask(r).execute();
        }
    }


    @Override
    public YTResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new YTResultViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.viewholder_ytresult, parent, false));
    }

    @Override
    public void onBindViewHolder(YTResultViewHolder holder, int position) {
        holder.setOnResultSelectedListener(onResultSelectedListener);
        holder.bind(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results != null ? results.size() : 0;
    }

    public YTResult getItem(int index) {
        return results.get(index);
    }

    public void setOnResultSelectedListener(OnResultSelectedListener listener) {
        this.onResultSelectedListener = listener;
    }
}
