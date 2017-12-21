package com.zz.huangxiaoer.bean;

/**
 * Created by ${韩永光} on on 2017/12/5 0005 09:30..
 */

public class SMS {


    /**
     * h2y_app_id : string
     * pd : {"mobile":"string","type":"string"}
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
         * mobile : string
         * type : string
         */

        private String mobile;
        private String type;

        public PdBean(String type, String mobile) {
            this.type = type;
            this.mobile = mobile;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
