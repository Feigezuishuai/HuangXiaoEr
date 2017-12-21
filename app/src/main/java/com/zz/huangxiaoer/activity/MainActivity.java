package com.zz.huangxiaoer.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zz.huangxiaoer.R;
import com.zz.huangxiaoer.bean.RegistSMS;
import com.zz.huangxiaoer.bean.SMS;
import com.zz.huangxiaoer.utils.CommonUtils;
import com.zz.huangxiaoer.utils.URLUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AutoLayoutActivity implements View.OnClickListener {
    private Handler mCountHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (countSeconds > 0) {
                --countSeconds;
                btngetcode_login.setText("(" + countSeconds + ")后获取验证码");
                mCountHandler.sendEmptyMessageDelayed(0, 1000);
            } else {
                countSeconds = 60;
                btngetcode_login.setText("请重新获取验证码");
            }
        }
    };
//啥都不做就测试下
    //第二次测试12/12
    private EditText etphone_login;
    private EditText etcode_login;
    private Button btnentry_login;
    private Button btngetcode_login;
    private int countSeconds = 60;//倒计时秒数
    private RequestParams params;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();

    }

    private void initEvent() {
        btnentry_login.setOnClickListener(this);
        btngetcode_login.setOnClickListener(this);
    }


    private void initView() {
        etphone_login = (EditText) findViewById(R.id.etphone_login);
        etcode_login = (EditText) findViewById(R.id.etcode_login);
        btnentry_login = (Button) findViewById(R.id.btnentry_login);
        btngetcode_login = (Button) findViewById(R.id.btngetcode_login);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btngetcode_login://验证码
                if (countSeconds == 60) {
                    String mobile = etphone_login.getText().toString();
                    getMobiile(mobile);
                } else {
                    Toast.makeText(this, "不能重复发送验证码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnentry_login://登录
                CommonUtils.startActivity(MainActivity.this,HomeActivity.class);
               // login();
                break;
            default:
                break;
        }
    }

    private void login() {
        final String mobile = etphone_login.getText().toString().trim();
        String verifyCode = etcode_login.getText().toString().trim();
        params = new RequestParams("https://cms.51hxe.com/hy_cms/member/register");
        final Map<String, String> map = new HashMap<>();
        RegistSMS registSMS = new RegistSMS();
        registSMS.setH2y_app_id("");
        registSMS.setPd(new RegistSMS.PdBean(mobile, "", "", 0, verifyCode));
        registSMS.setToken("");
        params.addHeader("Accept", "*/*"); //为当前请求添加一个头
        final String s = new Gson().toJson(registSMS);
        map.put("memberMoblieRegisterReq", s);
        params.setAsJsonContent(true);
        params.setBodyContent(s);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                Log.d("TTT",result+"222");
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String success = jsonObject.optString("result");
                    String msg = jsonObject.optString("msg");
                    if ("".equals(mobile)) {
                        Log.e("tag", "mobile=" + mobile);
                        new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("手机号码不能为空").setCancelable(true).show();
                    } else if (isMobileNO(mobile) == false) {
                        new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("请输入正确的手机号码").setCancelable(true).show();
                    }else{
                        if("success".equals(success)){
                            token = jsonObject.getString("token");
                            CommonUtils.startActivity(MainActivity.this,HomeActivity.class);
                        }else{
                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("TTT", "请求退出");
            }

            @Override
            public void onFinished() {

            }
        });

    }

    //获取验证码信息，判断是否有手机号码
    private void getMobiile(String mobile) {
        if ("".equals(mobile)) {
            Log.e("tag", "mobile=" + mobile);
            new AlertDialog.Builder(this).setTitle("提示").setMessage("手机号码不能为空").setCancelable(true).show();
        } else if (isMobileNO(mobile) == false) {
            new AlertDialog.Builder(this).setTitle("提示").setMessage("请输入正确的手机号码").setCancelable(true).show();
        } else {
            Log.e("tag", "输入了正确的手机号");
            requestVerifyCode(mobile);// 获取验证码信息，进行验证码请求
        }
    }

    // 获取验证码信息，进行验证码请求
    private void requestVerifyCode(String mobile) {
        params = new RequestParams(URLUtils.YANZHENG_URL);
        final Map<String, String> map = new HashMap<>();
        SMS sms = new SMS();
        sms.setH2y_app_id("");
        sms.setToken("");
        sms.setPd(new SMS.PdBean("", mobile));
        params.addHeader("Accept", "*/*"); //为当前请求添加一个头
        String s = new Gson().toJson(sms);
        map.put("smsReq", s);
        params.setAsJsonContent(true);
        params.setBodyContent(s);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("TTT", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String success = jsonObject.getString("result");
                    String msg = jsonObject.getString("msg");
                    Log.d("TTT", success);
                    Log.d("TTT", msg);
                    if ("success".equals(success)) {
                        Toast.makeText(MainActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                        startCountBack();//这里是用来进行请求参数的
                    } else {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("TTT", ex.toString() + "111");
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });
    }

    //使用正则表达式判断电话号码
    public static boolean isMobileNO(String tel) {
        Pattern p = Pattern.compile("^(13[0-9]|15([0-3]|[5-9])|14[5,7,9]|17[1,3,5,6,7,8]|18[0-9])\\d{8}$");
        Matcher m = p.matcher(tel);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    //获取验证码信息,进行计时操作
    private void startCountBack() {
        (MainActivity.this).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btngetcode_login.setText(countSeconds + "");
                mCountHandler.sendEmptyMessage(0);
            }
        });
    }
}