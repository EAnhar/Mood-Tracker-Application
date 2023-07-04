package com.example.moodtrackerapp.Adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moodtrackerapp.timeLinePage;
import com.example.moodtrackerapp.R;
import com.example.moodtrackerapp.model.moodModel;
import java.util.List;

public class moodAdapter extends RecyclerView.Adapter<moodAdapter.ViewHolder> {
    private List<moodModel> list;
    private final timeLinePage activity;

    public moodAdapter (timeLinePage activity) {
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int pos) {
        moodModel item = list.get(pos);

        holder.txtMood.setText(item.getTxtMood());
        holder.day.setText(item.getDay());
        holder.note.setText(item.getNote());
        holder.month.setText(item.getMonth());

        Resources res = holder.faceMood.getResources();
        int resID = res.getIdentifier(item.getFaceMood(), "drawable", this.activity.getPackageName());
        holder.faceMood.setImageResource(resID);
    }

    public int getItemCount() {
        return list.size();
    }

    public void setMoods(List<moodModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMood, note, day, month;
        ImageView faceMood;
        ViewHolder(View v) {
            super(v);
            faceMood = v.findViewById(R.id.faceMood);
            txtMood = v.findViewById(R.id.moodTxt);
            note = v.findViewById(R.id.notes);
            day = v.findViewById(R.id.day);
            month = v.findViewById(R.id.month);
        }
    }
}
