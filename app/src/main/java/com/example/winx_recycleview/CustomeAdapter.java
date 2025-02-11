package com.example.winx_recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winx_recycleview.R;
import com.example.winx_recycleview.Data;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.myViewHolder> {

    private ArrayList<Data> arr;
    private ArrayList<Data> arrFiltered;

    public CustomeAdapter(ArrayList<Data> arr) {
        this.arr = arr;
        this.arrFiltered = new ArrayList<>(arr);
    }

    public void filterList(String query) {
        arrFiltered.clear();
        if (query.isEmpty()) {
            arrFiltered.addAll(arr);
        } else {
            for (Data data : arr) {
                if (data.getName().toLowerCase().contains(query.toLowerCase())) { // סינון על פי שם הדמות
                    arrFiltered.add(data);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView username;
        TextView nameVersion;
        ImageView imageViewItem;

        public myViewHolder(View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.cardView);
            username = itemView.findViewById(R.id.textViewName);
            nameVersion = itemView.findViewById(R.id.textViewDescription);
            imageViewItem = itemView.findViewById(R.id.imageView);

            cardview.setOnClickListener(v -> {
                String name = username.getText().toString();
                Toast.makeText(v.getContext(), "You chose: " + name, Toast.LENGTH_SHORT).show();
            });
        }
    }

    @NonNull
    @Override
    public CustomeAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.myViewHolder holder, int position) {
        TextView textViewName = holder.username;
        TextView textViewVersion = holder.nameVersion;
        ImageView imageView = holder.imageViewItem;

        textViewName.setText(arrFiltered.get(position).getName());
        textViewVersion.setText(arrFiltered.get(position).getVersion());
        imageView.setImageResource(arrFiltered.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return arrFiltered.size();
    }
}
