
package com.xiaomi.xmplugindemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xiaomi.smarthome.common.ui.dialog.MLAlertDialog;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginBaseActivity;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;

import java.util.List;

public class ApiDemosActivity extends XmPluginBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_api_demos);
     
        mHostActivity.setTitleBarPadding(findViewById(R.id.title_bar));

        findViewById(R.id.title_bar_return).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((TextView) findViewById(R.id.title_bar_title)).setText("ApiDemos");

        findViewById(R.id.title_bar_more).setVisibility(View.GONE);

        findViewById(R.id.webview).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mHostActivity.loadWebView("http://home.mi.com", "MiHome");
            }
        });

        findViewById(R.id.dialog).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                MLAlertDialog dialog = new MLAlertDialog.Builder(ApiDemosActivity.this).setMessage(
                        "测试Dialog").setPositiveButton("确定", null).create();
                dialog.getWindow().setWindowAnimations(R.style.V5_Animation_Dialog);
                dialog.show();
            }
        });

        findViewById(R.id.openDevice).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final List<DeviceStat> deviceList = XmPluginHostApi.instance().getDeviceList();
                String[] items = new String[deviceList.size()];
                for (int i = 0; i < deviceList.size(); i++) {
                    items[i] = deviceList.get(i).name;
                }
                new MLAlertDialog.Builder(ApiDemosActivity.this).setTitle(
                        "选择设备").setItems(items, new MLAlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mHostActivity.openDevice(deviceList.get(which).did, null);
                       // XmPluginHostApi.instance().addToLauncher(mPluginPackage,deviceList.get(which).did, null);
                    }
                }).show();
            }
        });
        
        
        findViewById(R.id.testFragment).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
//                SerializableData sData = new SerializableData();
//                sData.id = 5;
//                intent.putExtra("sData", sData);
                ParcelData pData = new ParcelData();
                pData.mData = 2;
                intent.putExtra("pData", pData);
                startActivity(intent, FragmentActivity.class.getName());
            }
        });
    }

}
