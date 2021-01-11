package Retrofit;

import com.example.mini_projet_android.Mission;
import com.example.mini_projet_android.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface INodeJS {


    //USER

    @POST("api/signup")
    @FormUrlEncoded
    Observable<String> registerUser (@Field("email") String email,
                                   @Field("password") String password,
                                     @Field("roles") String roles
);



    @POST("api/login")
    @FormUrlEncoded
    Observable<String> loginUser ( @Field("email") String email,
                                   @Field("password") String password
                                   );



    //POST

    @POST("api/post/create")
    @FormUrlEncoded
    Observable<String> ajouterpost (@Field("description") String description,
                                    @Field("userid") String userid,
                                    @Field("titre") String titre
                                    );


    @POST("api/post/show")
    @FormUrlEncoded
    Observable<String> afficherpost (@Field("id") String id);


    @GET("api/post/showall")
    Call<List<Post>> getallposts ();




    @POST("api/post/delete")
    @FormUrlEncoded
    Observable<String> supprimerpost (@Field("id") String id);


    @POST("api/post/showbyfreelance")
    @FormUrlEncoded
    Observable<String> showbyfreelancer (@Field("userid") String userid);


    //MISSION

    @POST("api/mission/create")
    @FormUrlEncoded
    Observable<String> ajoutermission (
                                       @Field("nomposte") String nomposte,
                                       @Field("description") String description,
                                       @Field("tjm") String tjm,
                                       @Field("datedeb") String datedeb,
                                       @Field("datefin")String datefin
                                       );




    @GET("api/mission/show")
    @FormUrlEncoded
    Observable<String> affichermission (@Field("id") String id);


    @GET("api/mission/showall")
    Call<List<Mission>> getallmissions ();


    @POST("api/mission/delete")
    @FormUrlEncoded
    Observable<String> supprimermission (@Field("id") String id);


    @POST("api/mission/showbyclient")
    @FormUrlEncoded
    Observable<String> showbyclient (@Field("userid") String userid);



    //FREELANCER

    @POST("api/freelance/create")
    @FormUrlEncoded
    Observable<String> ajouterfreelancer (
                                          @Field("nom") String nom,
                                          @Field("prenom") String prenom,
                                          @Field("description") String description,
                                          @Field("phone") String phone,
                                          @Field("age") String age,
                                          @Field("github") String github,
                                          @Field("linkedin") String linkedin
                                          );


    @POST("api/freelance/show")
    @FormUrlEncoded
    Observable<String> afficherfreelancer (@Field("id") String id);


    @POST("api/freelance/showall")
    @FormUrlEncoded
    Observable<String> afficherfreelancers ();


    @POST("api/freelance/delete")
    @FormUrlEncoded
    Observable<String> supprimerfreelancer (@Field("id") String id);




    //CLIENT

    @POST("api/client/create")
    @FormUrlEncoded
    Observable<String> addclient (@Field("nom") String nom,
                                  @Field("nb") String nb,
                                  @Field("description") String description,
                                  @Field("pays") String pays,
                                  @Field("mf") String mf,
                                  @Field("phone") String phone
                                  );

    @POST("api/client/show")
    @FormUrlEncoded
    Observable<String> afficherclient (@Field("id") String id);


    @POST("api/client/showall")
    @FormUrlEncoded
    Observable<String> afficherclients ();


    @POST("api/client/delete")
    @FormUrlEncoded
    Observable<String> supprimerclient (@Field("id") String id);





}
