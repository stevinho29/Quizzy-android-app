package com.example.quizzy.adpaters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



import com.example.quizzy.QuizzyApplication;
import com.example.quizzy.R;
import com.example.quizzy.interfaces.CategoryListener;
import com.example.quizzy.model.entities.Category;

import java.util.List;

public class CategoriesAdapter extends BaseAdapter implements View.OnTouchListener {

    private List<Category> mCategories;
    private final LayoutInflater mInflater;
    private CategoryListener mListener;

    public CategoriesAdapter(List<Category> categories) {
        mCategories = categories;
        mInflater = LayoutInflater.from(QuizzyApplication.getContext());

        // Instantiate our cache
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 16;


    }
    public void setCategoryListener(CategoryListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public int getCount() {
        return null != mCategories ?mCategories.size():0;
    }

    @Override
    public Object getItem(int i) {
        return null != mCategories ? mCategories.get(i): null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewBestWay(position,convertView,parent);
    }

    /* Best way, because we reuse the convertView, and we keep a reference to the component, so we don't
            * iterate each time over the view hierarchy to find component, it's only done once the view is inflated
            * @param position
     * @param convertView
     * @param parent
     * @return
             */
    private View getViewBestWay(int position, View convertView, ViewGroup parent) {
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

        // Set the user name
        holder.libelle.setText(category.getLibelleCategory());

        // Set the alias
        holder.description.setText("description de la catégorie");


        // Register a listener to handle the touch on the fragment view
        convertView.setOnTouchListener(this);





        return convertView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("Fragment","on va handle ");
        return false;
    }

    private class ViewHolder {

        public TextView libelle;
        public TextView description;


        public ViewHolder(View view){
            libelle =  view.findViewById(R.id.headCard);
            description = view.findViewById(R.id.footCard);

        }
    }
}
