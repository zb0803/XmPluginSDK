
package com.xiaomi.xmplugindemo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xiaomi.smarthome.device.api.XmPluginBaseActivity;

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
    }

}
