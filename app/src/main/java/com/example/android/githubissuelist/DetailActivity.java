package com.example.android.githubissuelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    private int mIssueNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(getIntent().hasExtra("number")){
            mIssueNumber=getIntent().getIntExtra("number", 0);
        }else {
            mIssueNumber=0;
        }






    }
}
