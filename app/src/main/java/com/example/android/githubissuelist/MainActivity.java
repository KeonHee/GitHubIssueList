package com.example.android.githubissuelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.githubissuelist.retrofit.Issue;
import com.example.android.githubissuelist.retrofit.RetrofitImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IssueAdapter.IssueAdapterOnClickHandler{


    private RecyclerView mRecyclerView;
    private IssueAdapter mIssueAdapter;

    private ProgressBar mLoadingIndicater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingIndicater = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mLoadingIndicater.setVisibility(View.VISIBLE);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_issue);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);


        // 비동기 통신
        RetrofitImpl.getResponseListByAsync(new RetrofitImpl.RetrofitCallback() {
            @Override
            public void success(List<Issue> gitIssues) {
                mIssueAdapter.setIssueList(gitIssues); // 성공 시 adapter에 data 추가
                mLoadingIndicater.setVisibility(View.INVISIBLE);
            }

            @Override
            public void error(Throwable throwable) {
                mLoadingIndicater.setVisibility(View.INVISIBLE);
                Log.d("Retrofit", throwable.getMessage());
            }
        });

        mIssueAdapter = new IssueAdapter(this,this);
        mRecyclerView.setAdapter(mIssueAdapter);

    }

    @Override
    public void onClick(Issue issue) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("number", issue.getIssueNumber());
        startActivity(intent);
    }
}
