package com.example.quizzy.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.quizzy.QuizzyApplication;
import com.example.quizzy.R;
import com.example.quizzy.adpaters.CategoriesAdapter;
import com.example.quizzy.interfaces.CategoryChangeListener;
import com.example.quizzy.interfaces.CategoryListener;
import com.example.quizzy.model.Repository.UserDatabase;
import com.example.quizzy.model.entities.Category;
import com.example.quizzy.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoriesFragment extends Fragment implements CategoryChangeListener,AdapterView.OnItemClickListener {

    UserDatabase db;
    private CategoryListener mCategoryListener;
    private ListView mListView;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        db= QuizzyApplication.getDb();
        final String login = PreferenceUtils.getUsername();

            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    onCategoryRetrieved(db.CategoryDao().getAllCategory());
                }
            });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_categories, container, false);

        mListView= (ListView) RootView.findViewById(R.id.CategoriesListView);
        // Set adapter with no elements to let the ListView display the empty view
        mListView.setAdapter(
                new ArrayAdapter<Category>(getActivity(),
                        android.R.layout.simple_list_item_1, new ArrayList<Category>()));

        mListView.setOnItemClickListener(this);
        return RootView;
    }
    @Override
    public void onCategoryRetrieved(List<Category> categoryList) {
        // Set the adapter
        final CategoriesAdapter  adapter = new CategoriesAdapter(categoryList);
        adapter.setCategoryListener(mCategoryListener);
        mListView.setAdapter(adapter);

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof CategoryListener)
        { mCategoryListener = (CategoryListener) activity; }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //ListView lView = (ListView) view;
        Category category = (Category) mListView.getItemAtPosition(i);
        if(mCategoryListener!= null) mCategoryListener.onViewCategory(category.getLibelleCategory());
    }
}
