package com.example.android.githubissuelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.githubissuelist.retrofit.Issue;

import java.util.List;

/**
 * Created by user on 2017-01-21.
 */

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.IssueAdapterViewHolder> {

    final private Context mContext;

    final private IssueAdapterOnClickHandler mOnClickHandler;

    private List<Issue> mIssueList;

    public interface IssueAdapterOnClickHandler {
        void onClick(Issue issue);
    }

    public IssueAdapter(Context mContext, IssueAdapterOnClickHandler mOnClickHandler){
        this.mContext=mContext;
        this.mOnClickHandler=mOnClickHandler;
    }

    public void setIssueList(List<Issue> issueList){
        // issueList에 값이 할당되면 refresh
        mIssueList=issueList;
        notifyDataSetChanged();
    }

    @Override
    public IssueAdapter.IssueAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.issue_list_item,parent,false);

        view.setFocusable(true);

        return new IssueAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IssueAdapter.IssueAdapterViewHolder holder, int position) {
        Issue issue = mIssueList.get(position);

        /* @TODO 이미지 */
        holder.mUserNameTextView.setText(issue.getUser().getUserName());
        holder.mIssueNumberTextView.setText(String.valueOf(issue.getIssueNumber()));
        holder.mIssueTitleTextView.setText(issue.getIssueTitle());
        holder.mCommentCoutTextView.setText(String.valueOf(issue.getCommentCount()));

    }

    @Override
    public int getItemCount() {
        if (mIssueList==null) return 0;
        return mIssueList.size();
    }

    class IssueAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mUserImage;
        private TextView mUserNameTextView;
        private TextView mIssueNumberTextView;
        private TextView mIssueTitleTextView;
        private TextView mCommentCoutTextView;

        public IssueAdapterViewHolder(View itemView) {
            super(itemView);

            /* @TODO 이미지*/
            mUserNameTextView = (TextView) itemView.findViewById(R.id.tv_user_name);
            mIssueNumberTextView= (TextView) itemView.findViewById(R.id.tv_issue_number);
            mIssueTitleTextView= (TextView) itemView.findViewById(R.id.tv_issue_title);
            mCommentCoutTextView= (TextView) itemView.findViewById(R.id.tv_comment_count);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mOnClickHandler.onClick(mIssueList.get(adapterPosition));
        }
    }
}
