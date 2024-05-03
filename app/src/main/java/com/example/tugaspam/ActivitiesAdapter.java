package com.example.tugaspam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ViewHolder> {
    private List<ActivityItem> activitiesList;

    public ActivitiesAdapter(List<ActivityItem> activitiesList) {
        this.activitiesList = activitiesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActivityItem activityItem = activitiesList.get(position);
        holder.tvId.setText(String.valueOf(activityItem.getId()));
        holder.tvWhat.setText(activityItem.getWhat());
        holder.tvTime.setText(activityItem.getTime());
    }

    @Override
    public int getItemCount() {
        return activitiesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvWhat, tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvWhat = itemView.findViewById(R.id.tvWhat);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}

