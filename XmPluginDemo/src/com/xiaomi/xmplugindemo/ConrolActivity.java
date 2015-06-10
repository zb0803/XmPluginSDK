
package com.xiaomi.xmplugindemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginBaseActivity;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.xmplugindemo.DemoDevice.DemoDeviceListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConrolActivity extends XmPluginBaseActivity implements DemoDeviceListener {

    EditText mREdit;
    EditText mGEdit;
    EditText mBEdit;
    View mColorBoard;
    Button mSend;

    DemoDevice mDevice;

    // ParcelData mParcelData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control);

        // mParcelData =
        // (ParcelData)savedInstanceState.getParcelable("parcelData");

        mDevice = DemoDevice.getDevice(mDeviceStat);

        // 设置titlebar在顶部透明显示时的顶部padding
        mHostActivity.setTitleBarPadding(findViewById(R.id.title_bar));

        findViewById(R.id.title_bar_return).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView) findViewById(R.id.title_bar_title)).setText(getResources().getString(
                R.string.control_title));
        findViewById(R.id.title_bar_more).setVisibility(View.GONE);

        mREdit = (EditText) findViewById(R.id.r_edit);
        mGEdit = (EditText) findViewById(R.id.g_edit);
        mBEdit = (EditText) findViewById(R.id.b_edit);
        mColorBoard = findViewById(R.id.color_board);
        mSend = (Button) findViewById(R.id.send);

        mSend.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setRGB();
            }
        });

        mDevice.registerListener(this);

        getRGB();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mDevice.unregisterListener(this);
    }

    void setRGB() {

        int r = Integer.parseInt(mREdit.getText().toString());
        int g = Integer.parseInt(mGEdit.getText().toString());
        int b = Integer.parseInt(mBEdit.getText().toString());
        final int rgb = r << 16 | g << 8 | b;

        JSONArray params = new JSONArray();
        params.put(rgb);

        XmPluginHostApi.instance().callMethod(mDevice.getDid(), "set_rgb", params,
                new Callback<Boolean>() {

                    @Override
                    public void onSuccess(Boolean result) {
                        mDevice.setRGB(rgb);
                    }

                    @Override
                    public void onFailure(int error, String errorInfo) {

                    }
                }, null);
    }

    void getRGB() {
        XmPluginHostApi.instance().callMethod(mDevice.getDid(), "get_rgb", new JSONArray(),
                new Callback<Integer>() {

                    @Override
                    public void onSuccess(Integer rgb) {
                        mDevice.setRGB(rgb);
                    }

                    @Override
                    public void onFailure(int error, String errorInfo) {

                    }
                }, new Parser<Integer>() {

                    @Override
                    public Integer parse(String result) throws JSONException {
                        int rgb = 0;
                        JSONObject jsonObj = new JSONObject(result);
                        JSONArray jsonArray = jsonObj.optJSONArray("result");
                        if (jsonArray != null && jsonArray.length() > 0
                                && (jsonArray.get(0) instanceof Integer)) {
                            rgb = (Integer) jsonArray.get(0);
                        }
                        return rgb;
                    }

                });
    }

    void refreshUI() {
        int r = mDevice.getR();
        int g = mDevice.getG();
        int b = mDevice.getB();

        int tmp = Math.max(r, g);
        int max = Math.max(tmp, b);

        if (max <= 0) {
            max = 1;
        }

        int showR = (255 * r) / max;
        int showG = (255 * g) / max;
        int showB = (255 * b) / max;

        mREdit.setText(Integer.toString(r));
        mGEdit.setText(Integer.toString(g));
        mBEdit.setText(Integer.toString(b));
        mColorBoard.setBackgroundColor(Color.argb(255, showR, showG, showB));
    }

    @Override
    public void onStatusUpdated() {
        refreshUI();
    }
}
