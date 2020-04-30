package com.helb.eatBelgium.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helb.eatBelgium.R;
import com.helb.eatBelgium.model.Category;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {
    private List<Category> listCategories;

    public CategoriesAdapter(List<Category> listCategories) {
        this.listCategories = listCategories;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.fragment_categories_item,parent,false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.updateWithCategories(this.listCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listCategories.size();
    }
}
