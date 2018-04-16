package app.sarc.com.sarc.ViewController.Adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

import app.sarc.com.sarc.Common.Constants;
import app.sarc.com.sarc.Frameworks.ZoomImageView;
import app.sarc.com.sarc.Model.RestaurantInfo;
import app.sarc.com.sarc.R;
import app.sarc.com.sarc.ViewController.RestaurantDetailView.RestaurantDetailActivity;

/**
 * Created by Aron on 27/3/2018.
 */

public class RestaurantDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RestaurantInfo restaurantInfo;
    private String imgPath;
    private ArrayList<Integer> likeArray = new ArrayList<>();


    public RestaurantDetailAdapter(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
        for (int i = 0; i < 9; i++){
            likeArray.add(0);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 1;
        if(position > 0){
            viewType = 2;
        }
        return viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        switch (viewType){
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item_view, parent,false);
                return new DisplayBaseInfo(itemView);
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item_view2, parent,false);
                return new DisplayComment(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()){
            case 1:
                final DisplayBaseInfo displayBaseInfo = (DisplayBaseInfo) holder;
                String str = "";
                imgPath = Constants.baseURL+restaurantInfo.imgSrc;
                Glide.with(displayBaseInfo.itemView.getContext())
                        .load(imgPath)
                        .thumbnail(Glide.with(displayBaseInfo.itemView.getContext()).load(R.drawable.loading_pre_view))
                        .crossFade()
                        .centerCrop()
                        .into(displayBaseInfo.resImg);
                for (int i = 0; i < 5; i++){
                    str += restaurantInfo.description+"\n";
                }
                displayBaseInfo.resInfo.setText(str);


                displayBaseInfo.resImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((RestaurantDetailActivity) v.getContext()).zoomImageFromThumb(displayBaseInfo.resImg,imgPath);
                    }
                });
                break;
            case 2:
                final DisplayComment displayComment = (DisplayComment) holder;
                displayComment.comment.setText(restaurantInfo.comment);
                displayComment.like_btn.setOnClickListener(new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (likeArray.get(position) == 0){
                            displayComment.like_btn.setImageResource(R.drawable.like_icon);
                            likeArray.set(position, 1);
                        }else{
                            displayComment.like_btn.setImageResource(R.drawable.not_like_icon);
                            likeArray.set(position, 0);
                        }
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class DisplayBaseInfo extends RecyclerView.ViewHolder{
        public TextView resInfo;
        public ImageView resImg;

        public DisplayBaseInfo(View itemView) {
            super(itemView);
            resInfo = itemView.findViewById(R.id.Res_info);
            resImg = itemView.findViewById(R.id.Res_img);
            resInfo.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    public class DisplayComment extends RecyclerView.ViewHolder {
        public TextView comment;
        public ImageButton like_btn;
        public DisplayComment(View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.res_comment);
            like_btn = itemView.findViewById(R.id.like_btn);
        }
    }
}
