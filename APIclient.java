package Retrofit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class APIclient {

    public static Retrofit getRetrofit ()
    {HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor ();
    httpLoggingInterceptor.setLevel (HttpLoggingInterceptor.Level.BODY);
    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl ("http://10.0.2.2:3000/")
            .addConverterFactory (GsonConverterFactory.create())
            .client (okHttpClient)
            .build ();
    return retrofit;
    }
    public static INodeJS getINodeJS_Services(){
        INodeJS iNodeJS = getRetrofit().create(INodeJS.class);
        return iNodeJS;
    }


}
