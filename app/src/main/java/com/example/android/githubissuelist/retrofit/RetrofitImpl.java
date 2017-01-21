package com.example.android.githubissuelist.retrofit;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 2017-01-21.
 */

public class RetrofitImpl {
    public final static String GITHUB_HOST_URL = "https://api.github.com/";

    /* GitHub Issue List */
    public interface GItHubIssue {
        @GET("/repos/JakeWharton/DiskLruCache/issues")
        Call<List<Issue>> gitIssues();
    }

    public interface RetrofitCallback {
        // 생성시의 Callback
        void success(List<Issue> gitIssues);

        // 실패시의 Callback
        void error(Throwable throwable);
    }

    /* Get Method Http 통신, Async */
    public static void getResponseListByAsync(final RetrofitCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GItHubIssue gitHub = retrofit.create(GItHubIssue.class);

        Call<List<Issue>> call = gitHub.gitIssues();

        call.enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                if(response.isSuccessful()){
                    List<Issue> gitIssues = response.body();
                    callback.success(gitIssues);
                }else{
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });

    }



    /* GitHub Issue */
    public interface GitHubIssueOne{
        @GET("/repos/JakeWharton/DiskLruCache/issues/{issue_number}")
        Call<Issue> gitIssue(@Path("issue_number") int issue_number);
    }
    public interface RetrofitOneCallback {
        // 생성시의 Callback
        void success(Issue gitIssue);

        // 실패시의 Callback
        void error(Throwable throwable);
    }
    /* Get Method Http 통신, Async */
    public static void getResponseByAsync(int issueNumber, final RetrofitOneCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubIssueOne gitHub = retrofit.create(GitHubIssueOne.class);

        Call<Issue> call = gitHub.gitIssue(issueNumber);

        call.enqueue(new Callback<Issue>() {
            @Override
            public void onResponse(Call<Issue> call, Response<Issue> response) {
                if(response.isSuccessful()){
                    Issue gitIssue = response.body();
                    callback.success(gitIssue);
                }else{
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Issue> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });

    }
}
