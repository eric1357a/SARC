package app.sarc.com.sarc.ViewController.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.sarc.com.sarc.Common.Constants;
import app.sarc.com.sarc.Model.RestaurantInfo;
import app.sarc.com.sarc.R;
import app.sarc.com.sarc.ViewController.RestaurantDetailView.RestaurantDetailActivity;

/**
 * Created by Aron on 26/3/2018.
 */

public class RestaurantAdapter  extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>{

    private ArrayList<RestaurantInfo> restaurantInfos;

    public RestaurantAdapter(ArrayList<RestaurantInfo> restaurantInfos) {
        this.restaurantInfos = restaurantInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item_view, parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.RES_Name.setText(restaurantInfos.get(position).name);
        holder.RES_Description.setText(restaurantInfos.get(position).description);
        Glide.with(holder.itemView.getContext())
                .load(Constants.baseURL+restaurantInfos.get(position).imgSrc)
                .thumbnail(Glide.with(holder.itemView.getContext()).load(R.drawable.loading_pre_view))
                .fitCenter()
                .crossFade()
                .into(holder.RES_Image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RestaurantDetailActivity.class);
                intent.putExtra("ITEMVALUE", restaurantInfos.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView RES_Name, RES_Description;
        public ImageView RES_Image;

        public ViewHolder(View itemView) {
            super(itemView);

            RES_Name = itemView.findViewById(R.id.res_name);
            RES_Description = itemView.findViewById(R.id.res_desc);
            RES_Image = itemView.findViewById(R.id.res_img);
        }
    }
}
