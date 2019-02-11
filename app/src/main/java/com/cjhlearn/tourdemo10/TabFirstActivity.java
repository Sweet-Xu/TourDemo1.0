package com.cjhlearn.tourdemo10;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TabFirstActivity extends Activity implements LocationSource, AMapLocationListener {

    private static final String TAG="TabFirstActivity";

    private MapView mapView;
    private AMap aMap;
    private AMapLocationClient mapLocationClient=null;
    private AMapLocationClientOption mapLocationClientOption=null;
    private OnLocationChangedListener mListener=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_first);
        // TODO 高德导航以及路径
        MapInit();
        mapView.onCreate(savedInstanceState);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        Log.i(TAG, "OnLocationChanged()");
        //解析AMapLocation对象
        if (aMapLocation!=null)
        {
            Log.i(TAG, "");
            if (aMapLocation.getErrorCode()==0x00)//判断定位坐标是否有错误，0x00代表OK
            {
                int locationType=aMapLocation.getLocationType();//获取定位类型：（GPS， wifi，AGPS）
                double latitiude=aMapLocation.getLatitude();//获取经度（单位：度小数）
                double longitude=aMapLocation.getLongitude();//获取维度（单位：度小数）
                float accuracy=aMapLocation.getAccuracy();//获取精度信息
                String address=aMapLocation.getAddress();//获取地址

                String country=aMapLocation.getCountry();//国家信息
                String province=aMapLocation.getProvince();//省地址
                String city=aMapLocation.getCity();//城市信息
                String district=aMapLocation.getDistrict();//城区信息
                String street=aMapLocation.getStreet();//街道信息
                String streetNum=aMapLocation.getStreetNum();//街道门牌号信息
                String cityCode=aMapLocation.getCityCode();//城市编码
                String adCode=aMapLocation.getAdCode();//地区编码
                String aoiName=aMapLocation.getAoiName();//aoi信息
                String buildingId=aMapLocation.getBuildingId();//获取当前室内定位
                String floor=aMapLocation.getFloor();//获取室内定位楼层
                int gpsAccuracyStatus=aMapLocation.getGpsAccuracyStatus();//获取GPS工作状态

                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Date date=new Date(aMapLocation.getTime());
                df.format(date);

            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }

    /**
     * 对地图工具进行初始化
     */
    private void MapInit()
    {
        mapView=(MapView)findViewById(R.id.tv_amap_view);
        if (aMap==null)
        {
            aMap=mapView.getMap();
        }

        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setCompassEnabled(true);
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);

        mapLocationClient=new AMapLocationClient(getApplicationContext());
        mapLocationClient.setLocationListener(this);
        mapLocationClientOption=new AMapLocationClientOption();
        mapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        mapLocationClientOption.setInterval(2000);
        mapLocationClientOption.setNeedAddress(true);
        mapLocationClient.startLocation();
    }
}
