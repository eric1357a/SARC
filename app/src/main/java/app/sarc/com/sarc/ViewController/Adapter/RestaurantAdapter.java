package app.sarc.com.sarc.ViewController.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;

import app.sarc.com.sarc.Common.Constants;
import app.sarc.com.sarc.Model.RestaurantInfo;
import app.sarc.com.sarc.R;
import app.sarc.com.sarc.ViewController.RestaurantDetailView.RestaurantDetailActivity;

/**
 * Created by Aron on 26/3/2018.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<RestaurantInfo> restaurantInfos;

    public RestaurantAdapter(ArrayList<RestaurantInfo> restaurantInfos) {
        this.restaurantInfos = restaurantInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         switch (viewType){
                     case 1:
                         View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item_view, parent, false);
                         return new ViewHolder(itemView);
                     case 2:
                         itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_view, parent,false);
                         return new ViewHolder(itemView);
                 }
                 return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()){
            case 1:
                ViewHolder rest_view =  (ViewHolder) holder;
                rest_view.RES_Name.setText(restaurantInfos.get(position).name);
                rest_view.RES_Description.setText(restaurantInfos.get(position).description);
                 Glide.with(holder.itemView.getContext())
                .load(Constants.baseURL+restaurantInfos.get(position).imgSrc)
                .thumbnail(Glide.with(holder.itemView.getContext()).load(R.drawable.loading_pre_view))
                .fitCenter()
                .crossFade()
                .into(rest_view.RES_Image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RestaurantDetailActivity.class);
                intent.putExtra("ITEMVALUE", restaurantInfos.get(position));
                v.getContext().startActivity(intent);
            }
        });
                break;
            case 2:
                mapHolder mapHolder = (mapHolder) holder;

                break;
        }
    }




//    @Override
//    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.RES_Name.setText(restaurantInfos.get(position).name);
//        holder.RES_Description.setText(restaurantInfos.get(position).description);
//        Glide.with(holder.itemView.getContext())
//                .load(Constants.baseURL+restaurantInfos.get(position).imgSrc)
//                .thumbnail(Glide.with(holder.itemView.getContext()).load(R.drawable.loading_pre_view))
//                .fitCenter()
//                .crossFade()
//                .into(holder.RES_Image);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), RestaurantDetailActivity.class);
//                intent.putExtra("ITEMVALUE", restaurantInfos.get(position));
//                v.getContext().startActivity(intent);
//            }
//        });
//    }

    @Override
    public int getItemViewType(int position) {
              int viewType = 1;
              if(position == 0) viewType = 2;
              else viewType = 1;
              return viewType;
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

    public class mapHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
        private GoogleMap mMap;
        MapView map;
        LatLng mMapLocation;
        Context context;

        public mapHolder(View itemView) {
            super(itemView);
            map = itemView.findViewById(R.id.mapView);
            this.context = itemView.getContext();
            if (map != null) {
                map.onCreate(null);
                map.onResume();
                map.getMapAsync(this);
            }
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsInitializer.initialize(context);
            mMap = googleMap;
        }

        public void setMapLocation(LatLng mapLocation) {
            mMapLocation = mapLocation;
            if (mMap != null) {
                updateMapContents();
            }
        }

        protected void updateMapContents() {
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(mMapLocation));
        }
    }
}
