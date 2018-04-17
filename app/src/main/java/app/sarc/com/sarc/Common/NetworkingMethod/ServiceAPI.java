package app.sarc.com.sarc.Common.NetworkingMethod;

import app.sarc.com.sarc.Model.RestaurantInfo;
import app.sarc.com.sarc.Model.RestaurantList;
import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.Query;


/**
 * Created by Aron on 26/3/2018.
 */

public interface ServiceAPI {

    @GET("/FYP/APICalling/GetRestaurant.php")
    Observable<RestaurantList> getResList();

    @GET("/FYP/APICalling/GetOne.php")
    Observable<RestaurantInfo> getResDetail(@Query("id") String id);

    @GET("/FYP/APICalling/GetRecent.php")
    Observable<RestaurantList>getRecentList();
}

