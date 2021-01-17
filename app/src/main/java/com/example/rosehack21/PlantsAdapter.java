package com.example.rosehack21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlantsAdapter extends RecyclerView.Adapter<PlantsAdapter.ViewHolder> {
    public interface OnClickListener
    {
        void onItemClicked(int position);
    }

    List<String> plants;
    ItemsAdapter.OnClickListener clickListener;

    public PlantsAdapter(List<String> plants, ItemsAdapter.OnLongClickListener longClickListener, ItemsAdapter.OnClickListener clickListener) {
        this.plants = plants;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PlantsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }

    // responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull PlantsAdapter.ViewHolder holder, int position)
    {
        // grab the item at the position
        String item = plants.get(position);
        // bind the item into the specified view holder
        holder.bind(item);
    }

    // tells the RV how many plants are in the list
    @Override
    public int getItemCount() {
        return plants.size();
    }

    // Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //update the view inside of the view holder with this data
        public void bind(String item)
        {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}

