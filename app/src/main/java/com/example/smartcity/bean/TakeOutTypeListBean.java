package com.example.smartcity.bean;

import java.util.List;

public class TakeOutTypeListBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":null,"createTime":"2021-05-08 15:42:01","updateBy":null,"updateTime":"2021-05-09 14:34:03","remark":null,"params":{},"id":5,"name":"黄焖鸡套餐","sellerId":4,"themeId":1,"sort":1},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 14:34:40","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":26,"name":"特色小吃","sellerId":4,"themeId":1,"sort":2},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 14:34:52","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":27,"name":"黄焖系列","sellerId":4,"themeId":1,"sort":3}]
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
         * searchValue : null
         * createBy : null
         * createTime : 2021-05-08 15:42:01
         * updateBy : null
         * updateTime : 2021-05-09 14:34:03
         * remark : null
         * params : {}
         * id : 5
         * name : 黄焖鸡套餐
         * sellerId : 4
         * themeId : 1
         * sort : 1
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String name;
        private int sellerId;
        private int themeId;
        private int sort;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public int getThemeId() {
            return themeId;
        }

        public void setThemeId(int themeId) {
            this.themeId = themeId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public static class ParamsBean {
        }
    }
}
