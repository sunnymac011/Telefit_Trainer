package fit.tele.com.trainer.apiBase;

import java.util.ArrayList;
import java.util.Map;

import fit.tele.com.trainer.modelBean.CategoryBean;
import fit.tele.com.trainer.modelBean.CountryBean;
import fit.tele.com.trainer.modelBean.CreatePlanApiBean;
import fit.tele.com.trainer.modelBean.CreatePostBean;
import fit.tele.com.trainer.modelBean.CrossFitBean;
import fit.tele.com.trainer.modelBean.CustomFoodBean;
import fit.tele.com.trainer.modelBean.CustomerProfileBean;
import fit.tele.com.trainer.modelBean.ExerciseDetailsBean;
import fit.tele.com.trainer.modelBean.ExercisesBean;
import fit.tele.com.trainer.modelBean.ExercisesListBean;
import fit.tele.com.trainer.modelBean.FoodCategoryBean;
import fit.tele.com.trainer.modelBean.GoalBarBean;
import fit.tele.com.trainer.modelBean.LoginBean;
import fit.tele.com.trainer.modelBean.MealCategoryBean;
import fit.tele.com.trainer.modelBean.ModelBean;
import fit.tele.com.trainer.modelBean.NewRecipeBean;
import fit.tele.com.trainer.modelBean.PackageBean;
import fit.tele.com.trainer.modelBean.PaymentInfoBean;
import fit.tele.com.trainer.modelBean.PrivacyBean;
import fit.tele.com.trainer.modelBean.RecipeListBean;
import fit.tele.com.trainer.modelBean.RoutinePlanBean;
import fit.tele.com.trainer.modelBean.RoutinePlanDetailsBean;
import fit.tele.com.trainer.modelBean.RoutinePlanListBean;
import fit.tele.com.trainer.modelBean.SelectedItemsBean;
import fit.tele.com.trainer.modelBean.SubExerciseBean;
import fit.tele.com.trainer.modelBean.SubOptionsBean;
import fit.tele.com.trainer.modelBean.UpdatePlanApiBean;
import fit.tele.com.trainer.modelBean.YogaApiBean;
import fit.tele.com.trainer.modelBean.YogaExerciseDetailsBean;
import fit.tele.com.trainer.modelBean.chompBeans.ChompProductBean;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

public interface FetchServiceInterFace {

    @Multipart
    @POST("registration_trainer")
    Observable<ModelBean<LoginBean>> signUpApi(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part file); //done

    @POST("login_trainer")
    Observable<ModelBean<LoginBean>> manualLogin(@Body Map<String, String> params); //done

    @POST("forgot_password_trainer")
    Observable<ModelBean<LoginBean>> forgotAPI(@Body Map<String, String> params); //done

    @POST("save_new_password_trainer")
    Observable<ModelBean<LoginBean>> setNewPasswordAPI(@Body Map<String, String> params); //done

    @Multipart
    @POST("trainer/trainer_update_profile")
    Observable<ModelBean<LoginBean>> editProfileCustomer(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part file); //done

    @POST("trainer/user_exe_yoga")
    Observable<ModelBean<ArrayList<ExercisesBean>>> getExerciseTestAPI(@Body Map<String, String> params); //done

    @GET("product-search.php")
    rx.Observable<ResponseBody> getChomp(@Query("token") String q, @Query("name") String appid);

    @POST("trainer/trainer_get_all_subcategory")
    Observable<ModelBean<ArrayList<SubExerciseBean>>> getSubExerciseAPI(@Body Map<String, String> params); //done

    @POST("trainer/user_get_all_option")
    Observable<ModelBean<ArrayList<SubOptionsBean>>> getExerciseAPI(@Body Map<String, String> params); //done

    @POST("trainer/trainer_gym_get_all_subcat_opt")
    Observable<ModelBean<ArrayList<SubExerciseBean>>> getGymExerciseAPI(@Body Map<String, String> params); //done

    @POST("trainer/trainer_gym_all_subcat_opt_exe")
    Observable<ModelBean<ArrayList<ExercisesListBean>>> getFilteredExercises(@Body SelectedItemsBean request); //done

    @POST("trainer/trainer_gym_get_exe")
    Observable<ModelBean<ArrayList<ExerciseDetailsBean>>> getExerciseDetailsAPI(@Body Map<String, String> params); //done

    @POST("trainer/trainer_yoga_all_subcat_opt_exe")
    Observable<ModelBean<ArrayList<ExercisesListBean>>> getYogaFilteredExercises(@Body YogaApiBean request); //done

    @POST("trainer/yoga_get_exe")
    Observable<ModelBean<ArrayList<YogaExerciseDetailsBean>>> getYogaExerciseDetailsAPI(@Body Map<String, String> params); //done

    @POST("trainer/trainer_crossfit_get_all_subcat_opt")
    Observable<ModelBean<ArrayList<CrossFitBean>>> getCrossFitAPI(@Body Map<String, String> params); //done

    @POST("trainer/trainer_crossfit_all_subcat_opt_exe")
    Observable<ModelBean<ArrayList<ExercisesListBean>>> getCrossFitFilteredExercises(@Body YogaApiBean request); //done

    @POST("trainer/trainer_crossfit_all_subcat_opt_exe")
    Observable<ModelBean<ArrayList<ExercisesListBean>>> getCrossFitFilteredExercises(@Body SelectedItemsBean request); //done

    @POST("trainer/crossfit_get_exe")
    Observable<ModelBean<ArrayList<YogaExerciseDetailsBean>>> getCrossFitExerciseDetailsAPI(@Body Map<String, String> params); //done

    @POST("trainer/trainer_customer_exe_plane")
    Observable<ModelBean<ExercisesListBean>> createRoutineApi(@Body CreatePlanApiBean request); //done

    @POST("trainer/trainer_customer_routine_plane_get")
    Observable<ModelBean<ArrayList<RoutinePlanBean>>> getRoutinePlansApi(@Body Map<String, String> params); //done

    @POST("trainer/trainer_customer_routine_plane_getexe")
    Observable<ModelBean<ArrayList<RoutinePlanDetailsBean>>> getRoutinePlanDetailsAPI(@Body Map<String, String> params); //done

    @POST("trainer/ChargePayment")
    Observable<ModelBean<LoginBean>> getPaymentAPI(@Body Map<String, String> params); //done

    @GET("product-code.php")
    rx.Observable<ResponseBody> getBarCodeChomp(@Query("token") String q, @Query("code") String appid);

    @POST("trainer/trainer_help_add")
    Observable<ModelBean<LoginBean>> setHelp(@Body Map<String, String> params); //done

    @POST("trainer/trainer_privacy_policy")
    Observable<ModelBean<PrivacyBean>> getPrivacyApi(); //done

    @POST("trainer/term_condition")
    Observable<ModelBean<PrivacyBean>> getTermsApi(); //done

    @POST("trainer/triner_set_showhide_private")
    Observable<ModelBean<LoginBean>> setPrivacy(@Body Map<String, String> params); //done

    @POST("get_country")
    Observable<ModelBean<ArrayList<CountryBean>>> getCountryApi(); //done

    @POST("trainer/trainer_set_notification")
    Observable<ModelBean<LoginBean>> setNotificationSettings(@Body Map<String, String> params); //done

    @POST("trainer/get_all_cust_request")
    Observable<ModelBean<ArrayList<CustomerProfileBean>>> getRequestList(); //done

    @POST("trainer/accept_request_trainer_to_cust")
    Observable<ModelBean<ArrayList<CustomerProfileBean>>> getRequestAccept(@Body Map<String, String> params); //done

    @POST("trainer/trainer_subscription_term_condition")
    Observable<ModelBean<PaymentInfoBean>> getCheckPaymentAPI(); //done

    @POST("trainer/get_package")
    Observable<ModelBean<ArrayList<PackageBean>>> getPackage(); //done

    @POST("trainer/set_package")
    Observable<ModelBean<PackageBean>> setPackage(@Body Map<String, String> params); //done

    @POST("trainer/edit_package")
    Observable<ModelBean<PackageBean>> setEditPackage(@Body Map<String, String> params); //done

    @POST("trainer/customer_list")
    Observable<ModelBean<ArrayList<CustomerProfileBean>>> getCustomersList(); //done

    @POST("trainer/trainer_get_all_category")
    Observable<ModelBean<ArrayList<CategoryBean>>> getCategoryApi(); //done

    @POST("trainer/trainer_customer_routine_plane_update")
    Observable<ModelBean<ArrayList<RoutinePlanBean>>> deleteRoutine(@Body Map<String, String> params); //done

    @POST("trainer/trainer_customer_routine_plane_delete")
    Observable<ModelBean<ExercisesListBean>> updateRoutineApi(@Body UpdatePlanApiBean request); //done

    @POST("customer/cust_goal_get")
    Observable<ModelBean<GoalBarBean>> getGoalApi(@Body Map<String, String> params); //done

    @POST("trainer/cust_profile")
    Observable<ModelBean<LoginBean>> getCustomerProfile(@Body Map<String, String> params); //done

    @POST("trainer/trainer_list_post")
    Observable<ModelBean<ArrayList<CreatePostBean>>> getAllActivities(@Body Map<String, String> params); //done

    @Multipart
    @POST("trainer/trainer_set_post")
    Observable<ModelBean<LoginBean>> createPost(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part file); //done

    @POST("trainer/trainer_delete_post")
    Observable<ModelBean<ArrayList<CreatePostBean>>> deletePost(@Body Map<String, String> params); //done

    @POST("trainer/trainer_social_settings")
    Observable<ModelBean<LoginBean>> getSocialSetting(@Body Map<String, String> params); //done

    @POST("trainer/trainer_add_food")
    Observable<ModelBean<CustomFoodBean>> addCustomFood(@Body CustomFoodBean request); //done

    @POST("trainer/trainer_get_food")
    Observable<ModelBean<ArrayList<ChompProductBean>>> getCustomFoodApi(@Body Map<String, String> params); //done

    @POST("trainer/trainer_get_my_food")
    Observable<ModelBean<ArrayList<ChompProductBean>>> getMyFoodApi(@Body Map<String, String> params); //done

    @POST("trainer/trainer_delete_food")
    Observable<ModelBean<ArrayList<ChompProductBean>>> deleteFood(@Body Map<String, String> params); //done

    @POST("customer/cust_food_nutrition")
    Observable<ModelBean<ArrayList<String>>> getNutritionApi(); //done

    @POST("customer/cust_get_recipe")
    Observable<ModelBean<ArrayList<RecipeListBean>>> getRecipeApi(@Body Map<String, String> params); //done

    @GET("request.php")
    rx.Observable<ResponseBody> getChompCountry(@Query("token") String q, @Query("keyword") String foodName, @Query("country") String strCountry);

    @POST("customer/cust_get_all_meals")
    Observable<ModelBean<ArrayList<FoodCategoryBean>>> getFoodPlansApi(@Body Map<String, String> params); //done

    @POST("customer/cust_delete_recipe")
    Observable<ModelBean<ArrayList<RecipeListBean>>> deleteRecipe(@Body Map<String, String> params); //done

    @POST("customer/cust_get_meals_details")
    Observable<ModelBean<NewRecipeBean>> getMealApi(@Body Map<String, String> params); //done

    @POST("customer/cust_add_meals")
    Observable<ModelBean<NewRecipeBean>> createMealApi(@Body NewRecipeBean request); //done

    @POST("customer/cust_edit_meals")
    Observable<ModelBean<NewRecipeBean>> updateRecipeApi(@Body NewRecipeBean request); //done

    @POST("customer/cust_get_meals")
    Observable<ModelBean<ArrayList<FoodCategoryBean>>> getMealByDateApi(@Body Map<String, String> params); //done

    @POST("customer/add_food_category")
    Observable<ModelBean<ArrayList<FoodCategoryBean>>> getAddCatApi(@Body Map<String, String> params); //done

    @POST("customer/delete_food_category")
    Observable<ModelBean<ArrayList<FoodCategoryBean>>> getDeleteCatApi(@Body Map<String, String> params); //done

    @POST("customer/cust_get_meals_food_category")
    Observable<ModelBean<ArrayList<MealCategoryBean>>> getMealCatApi(@Body Map<String, String> params); //done
}
