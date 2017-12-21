package com.zz.huangxiaoer.bean;

/**
 * Created by ${韩永光} on on 2017/12/7 0007 14:30..
 */

public class RegistSMS {


    /**
     * h2y_app_id : string
     * pd : {"account":"1378394855","h2y_app_id":"string","password":"string","ref_one_id":0,"sms_code":""}
     * token : string
     */

    private String h2y_app_id;
    private PdBean pd;
    private String token;

    public String getH2y_app_id() {
        return h2y_app_id;
    }

    public void setH2y_app_id(String h2y_app_id) {
        this.h2y_app_id = h2y_app_id;
    }

    public PdBean getPd() {
        return pd;
    }

    public void setPd(PdBean pd) {
        this.pd = pd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class PdBean {
        /**
         * account : 1378394855
         * h2y_app_id : string
         * password : string
         * ref_one_id : 0
         * sms_code :
         */

        private String account;
        private String h2y_app_id;
        private String password;
        private int ref_one_id;
        private String sms_code;

        public PdBean(String account, String h2y_app_id, String password, int ref_one_id, String sms_code) {
            this.account = account;
            this.h2y_app_id = h2y_app_id;
            this.password = password;
            this.ref_one_id = ref_one_id;
            this.sms_code = sms_code;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getH2y_app_id() {
            return h2y_app_id;
        }

        public void setH2y_app_id(String h2y_app_id) {
            this.h2y_app_id = h2y_app_id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getRef_one_id() {
            return ref_one_id;
        }

        public void setRef_one_id(int ref_one_id) {
            this.ref_one_id = ref_one_id;
        }

        public String getSms_code() {
            return sms_code;
        }

        public void setSms_code(String sms_code) {
            this.sms_code = sms_code;
        }
    }
}

