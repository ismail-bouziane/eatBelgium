package com.helb.eatBelgium.Controlers.Activities.Views;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helb.eatBelgium.R;
import com.helb.eatBelgium.model.Category;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_root)
    TextView textView;

    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithCategories(Category category) {
        this.textView.setText(category.getNomCategory());
    }
}
