package com.alaaramadan.flashdemo.data.api;


import com.alaaramadan.flashdemo.data.CheckWinnerToday.CheckWinnerToday;
import com.alaaramadan.flashdemo.data.model.CheckPhone.CheckPhone;
import com.alaaramadan.flashdemo.data.model.CheckRegistration.CheckRegistration;
import com.alaaramadan.flashdemo.data.model.ConnectUs.ConnectUs;
import com.alaaramadan.flashdemo.data.model.ExternalAds.ExternalAds;
import com.alaaramadan.flashdemo.data.model.Flash.Flash;
import com.alaaramadan.flashdemo.data.model.GetWinners.GetWinners;
import com.alaaramadan.flashdemo.data.model.InternalAds.InternalAds;
import com.alaaramadan.flashdemo.data.model.ListCity.DataCity;
import com.alaaramadan.flashdemo.data.model.ListCity.ListCity;
import com.alaaramadan.flashdemo.data.model.ListGovernorate.ListGovernorate;
import com.alaaramadan.flashdemo.data.model.ListPrivacyPolicy.ListPrivacyPolicy;
import com.alaaramadan.flashdemo.data.model.ListUsageAgreement.ListUsageAgreement;
import com.alaaramadan.flashdemo.data.model.Login.Login;
import com.alaaramadan.flashdemo.data.model.Registeration.Registeration;
import com.alaaramadan.flashdemo.data.model.UserLogin.UserLogin;
import com.alaaramadan.flashdemo.data.model.UserRestore.UserRestore;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {


    @POST("index.php")
    @FormUrlEncoded
    Call<ListGovernorate> getGovernorateList(@Field("mode") String mode,
                                             @Field("type") String type
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<ListCity> getCityList(@Field("mode") String mode,
                               @Field("type") String type,
                               @Field( "gov_id" ) String gov_id
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<ConnectUs> getConnectUs(@Field("mode") String mode,
                                 @Field("type") String type
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<CheckPhone> CheckPhone(@Field("mode") String mode,
                                @Field("type") String type,
                                @Field( "phone" ) String phone
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<CheckRegistration> checkRegistration(@Field("mode") String mode,
                                              @Field("type") String type
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<Registeration> Registration(@Field("mode") String mode,
                                     @Field("type") String type,
                                     @Field( "phone" ) String phone,
                                     @Field( "PIN_code" ) String PIN_code,
                                     @Field( "confirm_PIN_code" ) String confirm_PIN_code,
                                     @Field( "password" ) String password,
                                     @Field( "confirm_password" ) String confirm_password,
                                     @Field( "name" ) String name,
                                     @Field( "udid" ) String udid,
                                     @Field( "gender" ) String gender,
                                     @Field( "date_of_birth" ) String date_of_birth,
                                     @Field( "city_id" ) String city_id,
                                     @Field( "gov_id" ) String gov_id
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<UserRestore> restoreAccount(@Field("mode") String mode,
                                         @Field("type") String type,
                                         @Field( "phone" ) String phone
    );
    @POST("index.php")
    @FormUrlEncoded
    Call<ListPrivacyPolicy> getPrivacyPolicyList(@Field("mode") String mode,
                                               @Field("type") String type
    );
    @POST("index.php")
    @FormUrlEncoded
    Call<ListUsageAgreement> getUsageAgreementList(@Field("mode") String mode,
                                                   @Field("type") String type
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<Login> checkAuth(@Field("mode") String mode,
                          @Field("type") String type,
                          @Field("phone") String phone,
                          @Field("password") String password
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<UserLogin> loginAuth(@Field("mode") String mode,
                              @Field("type") String type,
                              @Field("phone") String phone,
                              @Field("password") String password,
                              @Field("PIN_code") String PIN_code,
                              @Field( "udid" ) String udid
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<ExternalAds> getExternalAds(@Field("mode") String mode,
                                     @Field("type") String type,
                                     @Field("status") String status
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<GetWinners> getWinners(@Field("mode") String mode,
                                @Field("type") String type,
                                @Field("static") String apiToken,
                                @Field("udid") String udid
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<InternalAds> getInternalAds(@Field("mode") String mode,
                                     @Field("type") String type,
                                     @Field("status") String status,
                                     @Field("udid") String udid,
                                     @Field("static") String api_token
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<Flash> setFlash(@Field("mode") String mode,
                         @Field("type") String type,
                         @Field("static") String Api_token,
                         @Field("udid") String udid,
                         @Field("code") String code
    );

    @POST("index.php")
    @FormUrlEncoded
    Call<CheckWinnerToday> checkWinner(@Field("mode") String mode,
                                       @Field("type") String type,
                                       @Field("static") String Api_token,
                                       @Field("udid") String udid
    );

   /* //AUTH
    @POST("login")
    @FormUrlEncoded
    Call<Login> setDataLogin(@Field("name") String name,
                             @Field("password") String password);


    */

//    //AUTH
//    @POST("login")
//    @FormUrlEncoded
//    Single<Auth> onLogin(@Field("phone") String phone,
//                         @Field("local") String local);
//
//    //AUTH
//    @POST("logout")
//    @FormUrlEncoded
//    Single<NullResponse> onLogout(@Header("Authorization") String Authorization,
//                                  @Field("token") String token);
//
//    @POST("confirm-account")
//    @FormUrlEncoded
//    Single<Auth> onActiveSession(@Field("phone") String api_token,
//                                 @Field("pin_code") String pin_code,
//                                 @Field("token") String token,
//                                 @Field("os") String os,
//                                 @Field("local") String local);
//
//
//    @POST("accept-privacy")
//    @FormUrlEncoded
//    Single<Auth> onAcceptCondition(@Header("Authorization") String token,
//                                   @Field("local") String local);
//
//
//    @POST("resend-pin-code")
//    @FormUrlEncoded
//    Single<Auth> onResendCode(@Field("phone") String phone,
//                              @Field("local") String local);
//
//    //end AUTH
//
//    @GET("categories")
//    Single<Category> getCategories(@Query("local") String local);
//
//
//    @GET("stores")
//    Single<Store> getStores(@Query("category_id") int categoryId,
//                            @Query("sub_category_id") int subCategoryId,
//                            @Query("name") String name,
//                            @Query("latitude") String latitude,
//                            @Query("longitude") String longitude,
//                            @Query("page") int page,
//                            @Query("local") String local);
//
//    @GET("show-addresses")
//    Single<Address> getAddress(@Header("Authorization") String token,
//                               @Query("local") String local);
//
//    @POST("add-address")
//    @FormUrlEncoded
//    Single<AddAddress> addAddress(@Header("Authorization") String token,
//                                  @Field("title") String title,
//                                  @Field("latitude") String latitude,
//                                  @Field("longitude") String longitude,
//                                  @Field("local") String local);
//
//    @GET("store-products")
//    Single<Products> getProducts(@Header("Authorization") String token,
//                                 @Query("store_id") int storeId,
//                                 @Query("category_id") int categoryId,
//                                 @Query("name") String name,
//                                 @Query("page") int page,
//                                 @Query("local") String local);
//
//    @POST("add-to-cart")
//    @FormUrlEncoded
//    Single<NullResponse> addToCart(@Header("Authorization") String token,
//                                   @Field("product_id") int productId,
//                                   @Field("store_id") int storeId,
//                                   @Field("quantity") String quantity,
//                                   @Field("addition[]") List<Integer> addition,
//                                   @Field("addition_quantity[]") List<Integer> addition_quantity,
//                                   @Field("local") String local);
//
//    @POST("add-written-product-to-order")
//    @Multipart
//    Single<NullResponse> writeOrderForCart(@Header("Authorization") String token,
//                                           @Part("type") RequestBody type,
//                                           @Part("store_id") RequestBody storeId,
//                                           @Part("text") RequestBody quantity,
//                                           @Part MultipartBody.Part photo,
//                                           @Part("local") String local);
//
//    @POST("edit-addition-quantity")
//    @FormUrlEncoded
//    Single<NullResponse> editCart(@Header("Authorization") String token,
//                                  @Field("product_id") int productId,
//                                  @Field("quantity") String quantity,
//                                  @Field("addition_id") int addition);
//
//    @POST("edit-product-quantity")
//    @FormUrlEncoded
//    Single<NullResponse> editCart(@Header("Authorization") String token,
//                                  @Field("product_id") int productId,
//                                  @Field("quantity") int quantity);
//
//    @POST("remove-from-cart")
//    @FormUrlEncoded
//    Single<NullResponse> deleteForCart(@Header("Authorization") String token,
//                                       @Field("product_id") int productId);
//
//    @GET("offers")
//    Single<Products> getOffers(@Header("Authorization") String token,
//                               @Query("category_id") int categoryId,
//                               @Query("name") String name,
//                               @Query("page") int page,
//                               @Query("local") String local);
//
//    @GET("governorates")
//    Single<GeneralResponse> getGoverns();
//
//
//    @POST("add-delivery")
//    @Multipart
//    Single<NullResponse> joinAsDelivery(@Part("name") RequestBody name,
//                                        @Part("user_name") RequestBody user_name,
//                                        @Part("region_id") RequestBody region_id,
//                                        @Part("gender") RequestBody gender,
//                                        @Part("phone") RequestBody phone,
//                                        @Part("d_o_b") RequestBody d_o_b,
//                                        @Part MultipartBody.Part id_card_photo,
//                                        @Part MultipartBody.Part transport_photo);
//
//    @POST("add-store")
//    @FormUrlEncoded
//    Single<NullResponse> joinAsShop(@Field("phone") String phone,
//                                    @Field("region_id") int region_id,
//                                    @Field("name") String name,
//                                    @Field("user_name") String user_name,
//                                    @Field("category") String category,
//                                    @Field("owner_name") String owner_name);
//
//    @GET("list-cart")
//    Single<Cart> getCart(@Header("Authorization") String token,
//                         @Query("page") int page,
//                         @Query("local") String local);
//
//
////    @POST("edit-addition-quantity")
////    @FormUrlEncoded
////    Call<NullResponse> editAddition(@Field("product_id") int product_id,
////                                    @Field("addition_id") int addition_id,
////                                    @Field("quantity") String quantity);
////
////    @GET("store-categories")
////    Call<RestaurantCategories> getRestaurantCategories(@Query("store_id")int store_id);
//
//
//    @POST("log-mobile-app")
//    @FormUrlEncoded
//    Single<NullResponse> registerException(@Field("line") int line,
//                                           @Field("class_name") String class_name,
//                                           @Field("function_name") String function_name,
//                                           @Field("exception_message") String exception_message,
//                                           @Field("type") String type);
//
//
//    @GET("cities")
//    Call<GeneralResponse> getCity(@Query("governorate_id") int governorate_id);
//
//    @GET("regions")
//    Call<GeneralResponse> getRegions(@Query("city_id") int city_id);
//
//    @POST("update-profile")
//    @FormUrlEncoded
//    Single<Auth> updateProfile(@Field("phone") String phone,
//                               @Field("name") String name,
//                               @Field("gender") String gender,
//                               @Field("d_o_b") String d_o_b);
//
//
//    @POST("add-credit-card")
//    @FormUrlEncoded
//    Single<NullResponse> addCreditCard(@Field("name") String name,
//                                       @Field("number") String number,
//                                       @Field("cvv_code") String cvv_code,
//                                       @Field("expired_year") String expired_year,
//                                       @Field("expired_month") String expired_month,
//                                       @Header("Authorization") String token);
//
//    @POST("recharge-wallet")
//    @FormUrlEncoded
//    Single<NullResponse> rechargeWallet(@Field("type") String type,
//                                        @Field("amount") String amount,
//                                        @Header("Authorization") String token);
//
//
//    @GET("list-credit-card")
//    Call<CreditsList> getCredits(@Header("Authorization") String token);
}
