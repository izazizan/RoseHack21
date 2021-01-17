package com.example.rosehack21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlantsAdapter extends RecyclerView.Adapter<PlantsAdapter.ViewHolder> {
    public interface OnClickListener
    {
        void onItemClicked(int position);
    }

    List<Plant> plants;
    ItemsAdapter.OnClickListener clickListener;

    public PlantsAdapter(List<Plant> plants, ItemsAdapter.OnClickListener clickListener) {
        this.plants = plants;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PlantsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // use layout inflater to inflate a view
        View shopView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a View Holder and return it
        return new ViewHolder(shopView);
    }

    // responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull PlantsAdapter.ViewHolder holder, int position)
    {
        // grab the item at the position
        Plant plant = plants.get(position);
        // bind the item into the specified view holder
        holder.bind(plant);
    }

    // tells the RV how many plants are in the list
    @Override
    public int getItemCount() {
        return plants.size();
    }

    // Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTitle;
        TextView txtDesc;
        ImageView img;
        public ViewHolder(@NonNull View plantView) {
            super(plantView);
            txtTitle = plantView.findViewById(R.id.txt_name);
            txtDesc = plantView.findViewById(R.id.txt_description);
            img = plantView.findViewById(R.id.img_plant);
        }

        //update the view inside of the view holder with this data
        public void bind(Plant plant)
        {
            txtTitle.setText(plant.getType());
            txtDesc.setText(plant.getDescription());
            // img.setBackground();
            txtDesc.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}

