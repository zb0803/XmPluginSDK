
package com.xiaomi.xmplugindemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaomi.smarthome.common.ui.dialog.MLAlertDialog;
import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.BaseDevice.StateChangedListener;
import com.xiaomi.smarthome.device.api.XmPluginBaseActivity;

public class MainActivity extends XmPluginBaseActivity implements StateChangedListener {
    static final int REQUEST_MENUS = 1;
    static final int MSG_SUB_PROPERTIES = 1;
    DemoDevice mDevice;

    TextView mInfoView;
    boolean mIsResume;
    Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
           switch (msg.what) {
            case MSG_SUB_PROPERTIES:
                if(mIsResume){
                    mDevice.subscribeProperty(DemoDevice.PROPERTIES, null);
                    sendEmptyMessageDelayed(MSG_SUB_PROPERTIES, 3*60000);
                }
                break;

            default:
                break;
        }
        };
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView version = (TextView) findViewById(R.id.version);
        version.setText("version:" + mPluginPackage.packageVersion);
        TextView subTitleView = ((TextView) findViewById(R.id.sub_title_bar_title));
        subTitleView.setText("子设备");
        subTitleView.setVisibility(View.VISIBLE);
        
        mInfoView = (TextView) findViewById(R.id.info);

        // 初始化device
        mDevice = DemoDevice.getDevice(mDeviceStat);

        // 设置titlebar在顶部透明显示时的顶部padding
        mHostActivity.setTitleBarPadding(findViewById(R.id.title_bar));
        ((TextView) findViewById(R.id.title_bar_title)).setText(mDevice.getName());
        findViewById(R.id.title_bar_return).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        findViewById(R.id.title_bar_redpoint).setVisibility(View.VISIBLE);

        findViewById(R.id.title_bar_more).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 新版更多菜单规则，第一页插件自定义，然后跳转到公共页面
                Intent intent = new Intent();
                startActivity(intent, MoreActivity.class.getName());

                // // 点击菜单返回界面，需要在onActivityResult接收参数
                // ArrayList<String> menus = new ArrayList<String>();
                // menus.add("ApiDemo-MenuOnResult-分享");
                //
                // ArrayList<Intent> intents = new ArrayList<Intent>();
                //
                // Intent welcomeIntent = mHostActivity.getActivityIntent(null,
                // WelcomeActivity.class.getName());
                // welcomeIntent.putExtra("menu", "你好, 开发者");
                // intents.add(welcomeIntent);
                //
                // Intent intent = mHostActivity.getActivityIntent(null,
                // SettingActivity.class.getName());
                // intent.putExtra("menu", "设置");
                // intents.add(intent);
                //
                // Intent apiDemosIntent = mHostActivity.getActivityIntent(null,
                // ApiDemosActivity.class.getName());
                // apiDemosIntent.putExtra("menu", "ApiDemo-Others");
                // intents.add(apiDemosIntent);
                //
                // // Intent mihomeIntent = new Intent();
                // // mihomeIntent.setClassName("com.xiaomi.smarthome",
                // //
                // "com.xiaomi.smarthome.framework.webview.CommonWebViewActivity");
                // // mihomeIntent.putExtra("url", "http://home.mi.com");
                // // mihomeIntent.putExtra("title", "MiHome");
                // // mihomeIntent.putExtra("menu", "MiHome");
                // // intents.add(mihomeIntent);
                //
                // // 设置自定义菜单
                // mHostActivity.openMoreMenu(menus, intents, true,
                // REQUEST_MENUS);
            }
        });

        // 打开分享
        View shareView = findViewById(R.id.title_bar_share);
        if (mDevice.isBinded()) {
            shareView.setVisibility(View.VISIBLE);
            shareView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHostActivity.openShareActivity();
                }
            });
        } else {
            shareView.setVisibility(View.GONE);
        }

        findViewById(R.id.control).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // ParcelData parcelData = new ParcelData();
                // parcelData.mData = 10;
                // intent.putExtra("parcelData", parcelData);
                startActivity(intent, ControlActivity.class.getName());
                // if(XmPluginHostApi.instance().getApiLevel()>=3)
                // mHostActivity.overridePendingTransition(IXmPluginHostActivity.ANIM_SLIDE_IN_TOP,
                // null);
            }
        });

        findViewById(R.id.cloud_debug).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mHostActivity.loadWebView("http://home.mi.com/demo/cloud.html", null);
            }
        });

        findViewById(R.id.create_product).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mHostActivity.loadWebView("http://home.mi.com/demo/product.html", null);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 自定义菜单返回
            if (requestCode == REQUEST_MENUS && data != null) {
                String selectMenu = data.getStringExtra("menu");
                if (TextUtils.isEmpty(selectMenu)) {
                    return;
                }
                if (selectMenu.equals("ApiDemo-MenuOnResult-分享")) {
                    // 分享微博，微信，米聊
                    mHostActivity
                            .share("小米智能家居分享",
                                    "小米Note 顶配双网通版 香槟金 64GB",
                                    "http://home.mi.com/share.html?gid=39",
                                    "http://static.home.mi.com/app/shop/img?id=shop_9f52eb50febcbc5e3a30b95c2290ecfb.jpg&t=webp&z=1&q=80",
                                    "http://static.home.mi.com/app/shop/img?id=shop_9f52eb50febcbc5e3a30b95c2290ecfb.jpg&t=webp&z=1&q=80",
                                    null);
                } else {
                    Toast.makeText(activity(), selectMenu, Toast.LENGTH_SHORT).show();

                    MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity());
                    builder.setTitle("测试Dialog");
                    builder.setPositiveButton("Ok", new MLAlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsResume = true;
        mHandler.sendEmptyMessage(MSG_SUB_PROPERTIES);
        mDevice.addStateChangedListener(this);
        mDevice.updateDeviceStatus();
        mDevice.updateProperty(DemoDevice.PROPERTIES);
        ((TextView) findViewById(R.id.title_bar_title)).setText(mDevice.getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        mIsResume = false;
        mDevice.removeStateChangedListener(this);
        mHandler.removeMessages(MSG_SUB_PROPERTIES);
    }

    @Override
    public void onStateChanged(BaseDevice device) {
        if(!mIsResume)
            return;
        String info = "温度:" + mDevice.getTemperature() + " 湿度:" + mDevice.getHumidity();
        mInfoView.setText(info);
    }
}
