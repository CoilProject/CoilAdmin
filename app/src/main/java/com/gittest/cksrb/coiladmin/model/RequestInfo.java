package com.gittest.cksrb.coiladmin.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cksrb on 2016. 7. 4..
 */
public class RequestInfo {
    private int storeId;    //may equals phone number
    private String storeName;
    private String created;
    private String request;

    public RequestInfo(JSONObject obj) throws JSONException {
        this.storeId = obj.getInt("store_id");
        this.storeName = obj.getString("store_name");
        this.created = obj.getString("created");
        this.request = obj.getString("content");
    }

    public int getStoreId(){return storeId;}

    public void setStoreId(int storeId){this.storeId=storeId;}

    public String getStoreName(){return storeName;}

    public void setStoreName(String storeName){this.storeName=storeName;}

    public String getCreated(){return created;}

    public void setCreated(String created){this.created = created;}

    public String getRequest(){return request;}

    public void setRequest(String request){this.request = request;}
}
