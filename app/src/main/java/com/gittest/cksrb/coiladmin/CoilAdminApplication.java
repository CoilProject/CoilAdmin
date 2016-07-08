package com.gittest.cksrb.coiladmin;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by cksrb on 2016. 7. 8..
 */
public class CoilAdminApplication extends Application {

    public String user_id;
    public String user_permission;

    public int version_code;
    public boolean debug_mode;

    //public RequestAll requestAll;

    @Override
    public void onCreate() {
        super.onCreate();

        initCoil(true);

    }

    /**
     * 현재 어플리케이션의 초기화를 담당한다
     * version_code 에 현재 어플리케이션의 빌드 버전 코드 정보를 저장한다
     */
    private void initCoil(boolean dm){
        // debug_mode
        this.debug_mode = dm;

        // version_code
        version_code = -1;
        try {
            PackageInfo packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            version_code = packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
/*
    public void requestAll(){
        requestAll = new RequestAll();
    }
*/
    /**
     * 쿠폰 카드 정보를 저장하는 데이터
     */
    /*public class RequestAll {
        private boolean doNetwork;
        private List<RequestInfo> itemList;
        private RequestAdapter adapter;

        public RequestAll(){
            this.doNetwork = true;
            itemList = new ArrayList<>();
        }

        public void listInit(){
            itemList.clear();
        }

        public void setDoNetwork(boolean doNetwork){
            this.doNetwork = doNetwork;
        }

        public boolean isDoNetwork() {
            return doNetwork;
        }
        public List<RequestInfo> getItemList() {
            return itemList;
        }


        public void setItemList(List<RequestInfo> itemList) {
            this.itemList = itemList;
        }

        public void addItem(RequestInfo item){
            itemList.add(item);
        }
        public void setAdapter(RequestAdapter adapter){
            this.adapter = adapter;
        }

        public void notifyAdapter(){
            adapter.notifyDataSetChanged();
        }
    }
*/
}
