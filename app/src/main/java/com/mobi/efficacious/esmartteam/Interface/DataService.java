package com.mobi.efficacious.esmartteam.Interface;

import com.mobi.efficacious.esmartteam.entity.CustomerList;
import com.mobi.efficacious.esmartteam.entity.CustomerListPojo;
import com.mobi.efficacious.esmartteam.entity.LoginPojo;
import com.mobi.efficacious.esmartteam.entity.Requisition;
import com.mobi.efficacious.esmartteam.entity.RequisitionTypePojo;
import com.mobi.efficacious.esmartteam.entity.TaskList;
import com.mobi.efficacious.esmartteam.entity.TaskListPojo;
import com.mobi.efficacious.esmartteam.entity.TblLatestRec;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface DataService {
    @GET("Login")
    Call<LoginPojo> getLoginData(@Query("Username") String Username,
                                 @Query("Password") String Password );
    @GET("TaskList")
    Call<TaskListPojo> getDailyTaskList(@Query("command") String command,
                                        @Query("EmployeeId") String EmployeeId,
                                        @Query("OrgId") String OrgId );
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("TaskList")
    Call<ResponseBody> UpdateInsertTaskList(@Query("command") String command,
                                                        @Body TaskList insert);

    @GET("Customer")
    Call<CustomerListPojo> getCustomerDetail(@Query("intOrg_id") String intOrg_id );
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("Customer")
    Call<ResponseBody> InsertCustomer(@Body CustomerList insert);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("Customer")
    Call<ResponseBody> UpdateCustomer(@Body CustomerList Update);

    @GET("Requisition")
    Call<RequisitionTypePojo> getRequisitionType(@Query("intOrg_id") String intOrg_id );
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("Requisition")
    Call<ResponseBody> InserRequisition(@Body Requisition insert);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("LatestRec")
    Call<ResponseBody> InsertLatestRec(@Body TblLatestRec insert);
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("LatestRec")
    Call<ResponseBody> InsertLocation(@Body TblLatestRec insert);
}
