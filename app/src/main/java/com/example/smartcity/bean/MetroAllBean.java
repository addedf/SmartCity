package com.example.smartcity.bean;

import java.util.List;

public class MetroAllBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"lineId":1,"lineName":"16号线"},{"lineId":2,"lineName":"s1线"},{"lineId":3,"lineName":"燕房线"},{"lineId":4,"lineName":"西郊线"},{"lineId":5,"lineName":"s1线"},{"lineId":6,"lineName":"15号线"},{"lineId":7,"lineName":"8号线"},{"lineId":8,"lineName":"15号线"},{"lineId":9,"lineName":"14号线东段"},{"lineId":10,"lineName":"14号线西段"},{"lineId":11,"lineName":"昌平线"},{"lineId":12,"lineName":"14号线东段"},{"lineId":13,"lineName":"昌平线"},{"lineId":14,"lineName":"房山线"},{"lineId":15,"lineName":"4号线大兴线"},{"lineId":16,"lineName":"7号线"},{"lineId":17,"lineName":"亦庄线"},{"lineId":18,"lineName":"9号线"},{"lineId":19,"lineName":"亦庄线"},{"lineId":20,"lineName":"14号线西段"},{"lineId":21,"lineName":"6号线"},{"lineId":22,"lineName":"9号线"},{"lineId":23,"lineName":"房山线"},{"lineId":24,"lineName":"6号线"},{"lineId":25,"lineName":"4号线大兴线"},{"lineId":26,"lineName":"7号线"},{"lineId":27,"lineName":"8号线"},{"lineId":28,"lineName":"西郊线"},{"lineId":29,"lineName":"燕房线"},{"lineId":30,"lineName":"16号线"},{"lineId":31,"lineName":"2号线"},{"lineId":32,"lineName":"机场线"},{"lineId":33,"lineName":"1号线"},{"lineId":34,"lineName":"5号线"},{"lineId":35,"lineName":"1号线"},{"lineId":36,"lineName":"八通线"},{"lineId":37,"lineName":"10号线"},{"lineId":38,"lineName":"2号线"},{"lineId":39,"lineName":"5号线"},{"lineId":40,"lineName":"13号线"},{"lineId":41,"lineName":"10号线"},{"lineId":42,"lineName":"八通线"},{"lineId":43,"lineName":"13号线"}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lineId : 1
         * lineName : 16号线
         */

        private int lineId;
        private String lineName;

        public int getLineId() {
            return lineId;
        }

        public void setLineId(int lineId) {
            this.lineId = lineId;
        }

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }
    }
}
