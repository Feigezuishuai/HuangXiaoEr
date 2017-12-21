package com.zz.huangxiaoer.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AutoLayoutActivity implements View.OnClickListener {

    private EditText etphone_regist;
    private Button btgetcode_regist;
    private Button confirm_regist;
    private int countSeconds = 60;//倒计时秒数
    private EditText etcode_regist;
    private RequestParams params;
    private String etphone;
    private EditText pass_regist;
    private String pass;
    private String mycode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
    }

    private void initEvent() {
        confirm_regist.setOnClickListener(this);
        btgetcode_regist.setOnClickListener(this);
    }

    private void initView() {
        etcode_regist = (EditText) findViewById(R.id.etcode_regist);
        etphone_regist = (EditText) findViewById(R.id.etphone_regist);
        btgetcode_regist = (Button) findViewById(R.id.btgetcode_regist);
        confirm_regist = (Button) findViewById(R.id.confirm_regist);
        pass_regist = (EditText) findViewById(R.id.pass_regist);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btgetcode_regist://获取验证码
                if (countSeconds == 60) {
                    etphone = etphone_regist.getText().toString().trim();
                    getPhone(etphone);
                    countSeconds --;
                    requestVerifyCode(etphone);
                } else {
                    Toast.makeText(RegisterActivity.this, "不能重复发送验证码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.confirm_regist://注册
                pass = pass_regist.getText().toString().trim();
                mycode = etcode_regist.getText().toString().trim();
                registForCode(mycode, etphone, pass);
                break;
            default:
                break;
        }
    }

    private void registForCode(final String mycode, String etphone, final String pass) {
        params = new RequestParams("https://cms.51hxe.com/hy_cms/member/register");
        final Map<String, String> map = new HashMap<>();
        RegistSMS registSMS = new RegistSMS();
        registSMS.setH2y_app_id("");
        registSMS.setPd(new RegistSMS.PdBean(etphone, "", pass, 0, mycode));
        registSMS.setToken("");
        params.addHeader("Accept", "*/*"); //为当前请求添加一个头
        final String s = new Gson().toJson(registSMS);
        map.put("memberMoblieRegisterReq", s);
        params.setAsJsonContent(true);
        params.setBodyContent(s);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String success = jsonObject.optString("success");
                    String msg = jsonObject.optString("msg");
                    if ("true".equals(success)) {
                        RegistSMS myRegistSMS = new Gson().fromJson(result, RegistSMS.class);
                        String sms_code = myRegistSMS.getPd().getSms_code();
                       if(TextUtils.isEmpty(pass)||TextUtils.isEmpty(mycode)){
                           Toast.makeText(RegisterActivity.this,"验证码或登录密码不能为空",Toast.LENGTH_SHORT).show();
                       }else{
                           Toast.makeText(RegisterActivity.this, "注册成功,请登录" , Toast.LENGTH_SHORT).show();
                           CommonUtils.startActivity(RegisterActivity.this, MainActivity.class);
                           finish();
                       }



                    } else {
                        Toast.makeText(RegisterActivity.this, "验证码输入有误", Toast.LENGTH_SHORT).show();

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
        if ("".equals(mycode)) {
            new AlertDialog.Builder(this).setTitle("提示").setMessage("验证码不能为空").setCancelable(true).show();
        } else if (pass.length() < 6) {
            new AlertDialog.Builder(this).setTitle("提示").setMessage("您输入的密码过短").setCancelable(true).show();
        }
    }

    //获取验证码信息，判断是否有手机号码
    private void getPhone(String etphone) {
        if ("".equals(etphone)) {
            Log.e("TTT", "mobile=" + etphone);
            new AlertDialog.Builder(this).setTitle("提示").setMessage("手机号码不能为空").setCancelable(true).show();
        } else if (isMobileNO(etphone) == false) {
            new AlertDialog.Builder(this).setTitle("提示").setMessage("请输入正确的手机号码").setCancelable(true).show();
        } else {
            requestVerifyCode(etphone);
        }
    }

    //使用正则表达式判断电话号码
    public static boolean isMobileNO(String tel) {
        Pattern p = Pattern.compile("^(13[0-9]|15([0-3]|[5-9])|14[5,7,9]|17[1,3,5,6,7,8]|18[0-9])\\d{8}$");
        Matcher m = p.matcher(tel);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    //获取验证码信息，进行验证码请求
    private void requestVerifyCode(String mobile) {
        params = new RequestParams("https://cms.51hxe.com/hy_cms/push/send_sms");
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
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String success = jsonObject.getString("success");
                    String msg = jsonObject.getString("msg");
                    if("success".equals(success)){
                        Toast.makeText(RegisterActivity.this,"验证码发送成功", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this,msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("TTT",ex.toString()+"111");
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });


    }
}
