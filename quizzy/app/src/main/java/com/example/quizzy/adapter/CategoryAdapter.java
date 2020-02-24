package com.example.quizzy.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzy.HomeApplication;
import com.example.quizzy.R;
import com.example.quizzy.pojo.Category;

import java.util.List;

public class CategoryAdapter/* extends RecyclerView.Adapter<RecyclerView.ViewHolder>*/ {

    /*private List<Category> mCategories;
    private final LayoutInflater mInflater;

    public CategoryAdapter(List<Category> categories) {
        mCategories = categories;
        mInflater = LayoutInflater.from(HomeApplication.getContext());
    }

    public Object getItem(int i) {
        return null != mCategories ? mCategories.get(i): null;
    }

    /*@NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_item, parent, false);

        return new RecyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return null != mCategories ? mCategories.size():0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // If we don't have any convertView to reuse, inflate one
        if (null == convertView){
            convertView = mInflater.inflate(R.layout.category_item, null);
        }

        // Get the current item
        final Category category = (Category) getItem(position);

        // Set the user name, to do so, retrieve the corresponding TextView
        final TextView headCard = convertView.findViewById(R.id.headCard);
        headCard.setText(category.category);

        // Set the user alias, to do so, retrieve the corresponding TextView
        final TextView footCard = convertView.findViewById(R.id.footCard);
        footCard.setText("@string/" + category.category);

        //ajouter après @string/category cad @string/geographie etc... dans les ressources

        return convertView;
    }

    @SuppressLint("ResourceAsColor")
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // If we don't have any convertView to reuse, inflate one
        if (null == convertView){
            convertView = mInflater.inflate(R.layout.category_item, null);

            // Instantiate the ViewHolder
            holder = new ViewHolder(convertView);
            // Set as tag to the convertView to retrieve it easily
            convertView.setTag(holder);

        } else {
            // Just retrieve the ViewHolder instance in the tag of the view
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the current item
        final Category category = (Category) getItem(position);

        // Set the card color
        //holder.card.setCardBackgroundColor(R.color.colorPink "@color/" + category.category);
        holder.card.setCardBackgroundColor( R.color.colorPink);

        // Set the alias
        holder.headCard.setText(category.category);

        //ajouter après @string/category cad @string/geographie etc... dans les ressources

        // Set the text
        holder.footCard.setText("@string/" + category.category);

        return convertView;
    }

    private class ViewHolder {
        public CardView card;
        public TextView headCard;
        public TextView footCard;

        public ViewHolder(View view){
            card = view.findViewById(R.id.cardH);
            headCard = view.findViewById(R.id.headCard);
            footCard = view.findViewById(R.id.footCard);
        }
    }*/
}
