package com.gittest.cksrb.coiladmin.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gittest.cksrb.coiladmin.R;
import com.gittest.cksrb.coiladmin.model.RequestInfo;

import java.util.List;

/**
 * Created by cksrb on 2016. 7. 4..
 */
public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder>{

    private List<RequestInfo> items;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView storeName,created,request;

        public ViewHolder(View view) {
            super(view);
            storeName = (TextView) view.findViewById(R.id.text_storeName);
            created = (TextView) view.findViewById(R.id.text_created);
            request = (TextView) view.findViewById(R.id.text_request);
        }
    }

    public RequestAdapter(List<RequestInfo> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.request_list, parent, false);

        return new ViewHolder(itemView);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RequestInfo requestInfo = items.get(position);
        holder.storeName.setText(requestInfo.getStoreName());
        holder.created.setText(requestInfo.getCreated());
        holder.request.setText(requestInfo.getRequest());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

}
