package com.example.coolnewsapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    Toolbar layout_toolbar;
    ViewPager verticalViewPager;


    String que = "trump";
    List<NewsResult> sliderItems = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       // layout_toolbar = findViewById(R.id.toolbarnews);
//        setupToolbar();

        if(getIntent().getExtras()!=null){
            Bundle bundle= getIntent().getExtras();
            que = bundle.getString("EXTRA_Q");
        }

        getSupportActionBar().setTitle("Results for: "+que);

        verticalViewPager =(VerticalViewPager) findViewById(R.id.verticalViewPager);
        getNews();

    }

    public void getNews(){
        RetrofitClient.ApiClient.getNewsApiInterface().getTechNews(""+que,"0360e3e2d1dc4e8da356af129a30c807").enqueue(new Callback<TotalHeadlines>() {

            @Override
            public void onResponse(Call<TotalHeadlines> call, Response<TotalHeadlines> response) {

                if(response.code() == 200){
                    // Toast.makeText(getApplicationContext(), "Here "+response.body().getCategoriesResult().get(0).getTitle(), Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, "Total Results "+response.body().getCategoriesResult().size(), Toast.LENGTH_LONG).show();
                    if(response.body().getCategoriesResult()!=null){
                        sliderItems.addAll(response.body().getCategoriesResult());
                        verticalViewPager.setAdapter(new NewsPagerAdapter(MainActivity.this, sliderItems));
                    }
                }else if(response.code() == 400){
//
                }
            }

            @Override
            public void onFailure(Call<TotalHeadlines> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}