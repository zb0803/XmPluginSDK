
package com.xiaomi.xmplugindemo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.device.api.XmPluginBaseActivity;

public class MoreActivity extends XmPluginBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        // 设置titlebar在顶部透明显示时的顶部padding
        mHostActivity.setTitleBarPadding(findViewById(R.id.title_bar));
        ((TextView) findViewById(R.id.title_bar_title)).setText("更多");
        findViewById(R.id.title_bar_return).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.hello).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(null, WelcomeActivity.class.getName());
            }
        });
        findViewById(R.id.transparent_titlerar).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(null, TransparentActivity.class.getName());
            }
        });
        findViewById(R.id.dialog).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(null, DiaglogActivity.class.getName());
            }
        });
        findViewById(R.id.setting).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
               startActivity(null, SettingActivity.class.getName());
               mHostActivity.overridePendingTransition(IXmPluginHostActivity.ANIM_SLIDE_IN_TOP, IXmPluginHostActivity.ANIM_SLIDE_OUT_BOTTOM);
            }
        });
        findViewById(R.id.others).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(null, ApiDemosActivity.class.getName());
            }
        });

        findViewById(R.id.base_setting).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mHostActivity.openMoreMenu(null, null, true, -1);
            }
        });
    }
}
