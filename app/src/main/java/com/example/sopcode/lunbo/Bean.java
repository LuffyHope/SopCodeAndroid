package com.example.sopcode.lunbo;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.util.List;

public class Bean {
    private int code;
    private String msg;
    private List<ObjBean> obj;

    public Bean(int code, String msg, List<ObjBean> obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }


    public class ObjBean extends SimpleBannerInfo {
        private String name;
        private String age;
        private String imageurl;

        public ObjBean(String name, String age, String imageurl) {
            this.name = name;
            this.age = age;
            this.imageurl = imageurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        @Override
        public Object getXBannerUrl() {
            return imageurl;
        }
    }
}
