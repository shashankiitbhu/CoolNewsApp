package com.example.coolnewsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsPagerAdapter extends PagerAdapter {
    List<NewsResult> sliderItems;
    LayoutInflater mLayoutInflater;
    Context context;

    public NewsPagerAdapter(Context context, List<NewsResult> sliderItems) {
        this.context = context;
        this.sliderItems = sliderItems;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sliderItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_container, container, false);
        ImageView imageView = itemView.findViewById(R.id.imageView);
        TextView title = itemView.findViewById(R.id.headline);
        TextView desc = itemView.findViewById(R.id.desc);
        TextView head = itemView.findViewById(R.id.head);
        Button btn = itemView.findViewById(R.id.imageView2);
        Button share = itemView.findViewById(R.id.btn_share);

        title.setText(""+sliderItems.get(position).getTitle());
        desc.setText(""+sliderItems.get(position).getDescription());
        head.setText(""+sliderItems.get(position).getSource().getName());

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareMessage = ""+sliderItems.get(position).getTitle()+"\n\n"+"Read More at: "+sliderItems.get(position).getUrl();
                Intent waIntent = new Intent(Intent.ACTION_SEND);
                waIntent.setType("text/*");
                waIntent.putExtra(Intent.EXTRA_TEXT, "" + shareMessage);
                context.startActivity(Intent.createChooser(waIntent, "Share with"));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating a Thread
                String[] arrOfStr = sliderItems.get(position).getTitle().split("\\s+");
                String newsThread = arrOfStr[0]+arrOfStr[1];


                Intent intent = new Intent(context,WebViewActivity.class);
                intent.putExtra("external_url", String.valueOf(sliderItems.get(position).getUrl()));
                context.startActivity(intent);


            }
        });
        try{
            Picasso.get().load(sliderItems.get(position).getCoverimage()).into(imageView);
        }catch (Exception e){
           imageView.setImageDrawable(context.getDrawable(R.drawable.no_image));
        }

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
