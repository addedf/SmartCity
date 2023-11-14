package com.example.smartcity.bean;

import java.util.List;

public class TakeOutBanner {

    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":null,"createTime":"2021-04-01 09:01:36","updateBy":"admin","updateTime":"2021-05-12 09:00:55","remark":null,"params":{},"id":6,"appType":"takeout","status":"1","sort":1,"advTitle":"店家1","advImg":"/prod-api/profile/upload/image/2021/05/12/873a6039-4294-43fe-9209-408a001cf7df.png","servModule":"外卖店家","targetId":1,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-04-01 09:25:39","updateBy":"admin","updateTime":"2021-05-12 08:55:10","remark":null,"params":{},"id":7,"appType":"takeout","status":"1","sort":2,"advTitle":"店家2","advImg":"/prod-api/profile/upload/image/2021/05/12/2e1d64a3-ce43-40de-99a7-48b281b3af15.jpg","servModule":"外卖店家","targetId":2,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-04-01 09:39:42","updateBy":"1","updateTime":"2021-05-12 08:55:23","remark":null,"params":{},"id":8,"appType":"takeout","status":"1","sort":3,"advTitle":"店家3","advImg":"/prod-api/profile/upload/image/2021/05/12/b81c793c-3f6e-42b8-9a07-3d9b42a422c6.jpg","servModule":"外卖店家","targetId":3,"type":"2"}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2021-04-01 09:01:36
         * updateBy : admin
         * updateTime : 2021-05-12 09:00:55
         * remark : null
         * params : {}
         * id : 6
         * appType : takeout
         * status : 1
         * sort : 1
         * advTitle : 店家1
         * advImg : /prod-api/profile/upload/image/2021/05/12/873a6039-4294-43fe-9209-408a001cf7df.png
         * servModule : 外卖店家
         * targetId : 1
         * type : 2
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String appType;
        private String status;
        private int sort;
        private String advTitle;
        private String advImg;
        private String servModule;
        private int targetId;
        private String type;

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

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
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

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getAdvTitle() {
            return advTitle;
        }

        public void setAdvTitle(String advTitle) {
            this.advTitle = advTitle;
        }

        public String getAdvImg() {
            return advImg;
        }

        public void setAdvImg(String advImg) {
            this.advImg = advImg;
        }

        public String getServModule() {
            return servModule;
        }

        public void setServModule(String servModule) {
            this.servModule = servModule;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class ParamsBean {
        }
    }
}
