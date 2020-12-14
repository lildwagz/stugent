package ei.eseptiyadi.aps.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private static Retrofit getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gabutcodex.tk/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }

    public static ApiServices getInstance() {
        return getRetrofit().create(ApiServices.class);
    }
}