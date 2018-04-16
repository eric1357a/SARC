package app.sarc.com.sarc.ViewController.RestaurantDetailView;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import app.sarc.com.sarc.Common.Constants;
import app.sarc.com.sarc.Common.NetworkingMethod.APIResponse;
import app.sarc.com.sarc.Frameworks.ZoomImageView;
import app.sarc.com.sarc.Model.RestaurantInfo;
import app.sarc.com.sarc.R;
import app.sarc.com.sarc.ViewController.Adapter.RestaurantDetailAdapter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RestaurantDetailActivity extends AppCompatActivity {

    private RestaurantInfo restaurantInfo = new RestaurantInfo();
    private RecyclerView detailView;
    private RestaurantDetailAdapter adapter;
    private int mShortAnimationDuration;
    private RelativeLayout expanded_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        detailView = findViewById(R.id.detail_View);
        detailView.setHasFixedSize(true);
        detailView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        expanded_view = findViewById(R.id.expanded_layout);

        if(getIntent().getSerializableExtra("ITEMVALUE") != null){
            restaurantInfo = (RestaurantInfo) getIntent().getSerializableExtra("ITEMVALUE");
            getSupportActionBar().setTitle(restaurantInfo.name);
            getDetailData(restaurantInfo.id);

        }
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);


    }

    private void getDetailData(String ID){
        new APIResponse().getRestaurantDetail(new Observer<RestaurantInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RestaurantInfo restaurantInfo) {
                adapter = new RestaurantDetailAdapter(restaurantInfo);
                detailView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, ID);
    }

    public void zoomImageFromThumb(final View thumbView, String imgPath) {
        ZoomImageView.zoomImageFromThumb(findViewById(R.id.container), expanded_view, mShortAnimationDuration,thumbView,imgPath);

    }
}
