package com.example.coolnewsapp.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.coolnewsapp.NewsPagerAdapter;
import com.example.coolnewsapp.NewsResult;
import com.example.coolnewsapp.R;
import com.example.coolnewsapp.RetrofitClient;
import com.example.coolnewsapp.TotalHeadlines;
import com.example.coolnewsapp.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class CountryFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    ViewPager verticalViewPager;
    List<NewsResult> sliderItems = new ArrayList<>();

    public static CountryFragment newInstance(boolean enabled) {
        CountryFragment fragment = new CountryFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("SVPC_ENABLED", enabled);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);

        verticalViewPager =(VerticalViewPager) rootView.findViewById(R.id.verticalViewPager);
        getNews();

        return rootView;
    }

    public void getNews(){
        RetrofitClient.ApiClient.getNewsApiInterface().getGlobalHeadlines("us","0360e3e2d1dc4e8da356af129a30c807").enqueue(new Callback<TotalHeadlines>() {

            @Override
            public void onResponse(Call<TotalHeadlines> call, Response<TotalHeadlines> response) {

               // Toast.makeText(getContext(), "Real Response "+response.message(), Toast.LENGTH_LONG).show();
                if(response.code() == 200){
                    // Toast.makeText(getApplicationContext(), "Here "+response.body().getCategoriesResult().get(0).getTitle(), Toast.LENGTH_LONG).show();
                    if(response.body().getCategoriesResult()!=null){
                        sliderItems.addAll(response.body().getCategoriesResult());
                        verticalViewPager.setAdapter(new NewsPagerAdapter(getContext(), sliderItems));
                    }
                }else if(response.code() == 400){
//
                }
            }

            @Override
            public void onFailure(Call<TotalHeadlines> call, Throwable t) {
                Toast.makeText(getContext(), "Error "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}