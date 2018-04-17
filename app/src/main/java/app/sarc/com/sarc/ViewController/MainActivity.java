package app.sarc.com.sarc.ViewController;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;

import app.sarc.com.sarc.Common.NetworkingMethod.APIResponse;
import app.sarc.com.sarc.Model.RestaurantList;
import app.sarc.com.sarc.R;
import app.sarc.com.sarc.ViewController.Adapter.RestaurantAdapter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RestaurantListView;
    private RestaurantAdapter adapter;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Restaurant List");
        RestaurantListView = findViewById(R.id.Res_list);
        RestaurantListView.setHasFixedSize(true);
        RestaurantListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        InitImageLoader();
        getRestaurantListData();
    }

    private void InitImageLoader(){
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
                getApplicationContext());
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    private void getRestaurantListData(){
        new APIResponse().getRestaurantList(new Observer<RestaurantList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RestaurantList restaurantList) {
                adapter = new RestaurantAdapter(restaurantList.restaturant_list);
                RestaurantListView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
