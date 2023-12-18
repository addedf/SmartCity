package com.example.smartcity.bean;

import java.util.List;

public class FoodTypeBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":null,"createTime":"2021-05-08 15:30:07","updateBy":null,"updateTime":"2021-05-09 14:23:23","remark":null,"params":{},"id":3,"name":"披萨","sellerId":1,"themeId":5,"sort":1},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 14:24:08","updateBy":null,"updateTime":"2021-05-09 14:25:17","remark":null,"params":{},"id":19,"name":"组合套餐","sellerId":1,"themeId":3,"sort":2},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 14:24:23","updateBy":null,"updateTime":"2021-05-11 21:36:08","remark":null,"params":{},"id":20,"name":"焗饭","sellerId":1,"themeId":3,"sort":3}]
     */

    private String msg;
    private Integer code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
         * createTime : 2021-05-08 15:30:07
         * updateBy : null
         * updateTime : 2021-05-09 14:23:23
         * remark : null
         * params : {}
         * id : 3
         * name : 披萨
         * sellerId : 1
         * themeId : 5
         * sort : 1
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private Integer id;
        private String name;
        private Integer sellerId;
        private Integer themeId;
        private Integer sort;

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

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getSellerId() {
            return sellerId;
        }

        public void setSellerId(Integer sellerId) {
            this.sellerId = sellerId;
        }

        public Integer getThemeId() {
            return themeId;
        }

        public void setThemeId(Integer themeId) {
            this.themeId = themeId;
        }

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public static class ParamsBean {
        }
    }
}
