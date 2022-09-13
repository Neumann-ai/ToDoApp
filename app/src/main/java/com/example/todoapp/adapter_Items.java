package com.example.todoapp;

import static android.view.View.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.todoapp.Tasks;

public class adapter_Items extends RecyclerView.Adapter<adapter_Items.ItemViewHolder> {

    private final ArrayList<Tasks> objectList;

    public RecyclerViewInterface recyclerViewInterface;

    public adapter_Items(ArrayList<Tasks> objectList, RecyclerViewInterface recyclerViewInterface) {
        this.objectList = objectList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);

        return new ItemViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.content.setText(objectList.get(position).getText());
        holder.date.setText(objectList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
         TextView content;
         TextView date;

        public ItemViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            content = itemView.findViewById(R.id.item_Content);
            date =  itemView.findViewById(R.id.item_Date);

            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(adapter_Items.this.recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION)
                        {
                            adapter_Items.this.recyclerViewInterface.onItemClick(pos);
                        }

                    }
                }
            });

            itemView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(adapter_Items.this.recyclerViewInterface != null)
                    {
                     int pos = getAdapterPosition();

                     if(pos != RecyclerView.NO_POSITION)
                     {
                         adapter_Items.this.recyclerViewInterface.onItemLongClick(pos);
                     }
                    }
                    return true;
                }
            });

    }

}
}
