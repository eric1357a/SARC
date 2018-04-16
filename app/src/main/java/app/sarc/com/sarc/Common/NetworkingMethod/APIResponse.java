package app.sarc.com.sarc.Common.NetworkingMethod;

/**
 * Created by Aron on 26/3/2018.
 */

import app.sarc.com.sarc.Model.RestaurantInfo;
import app.sarc.com.sarc.Model.RestaurantList;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class APIResponse {

    public void getRestaurantList(Observer<RestaurantList> observer){
        HttpMethods.getInstance().getServiceAPI().getResList()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRestaurantDetail(Observer<RestaurantInfo> observer, String ID){
        HttpMethods.getInstance().getServiceAPI().getResDetail(ID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
