package com.example.smartcity.bean;

import java.util.List;

public class TakeTypeBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":null,"createTime":"2021-04-13 16:52:46","updateBy":null,"updateTime":"2021-05-08 11:56:50","remark":null,"params":{},"id":1,"themeName":"美食","themeDesc":"美食来找我","imgUrl":"/prod-api/profile/upload/image/2021/05/08/f8c06dfc-9da2-41cc-9784-8cf234c999f2.png","sort":1},{"searchValue":null,"createBy":null,"createTime":"2021-04-13 16:53:13","updateBy":null,"updateTime":"2021-05-08 11:57:00","remark":null,"params":{},"id":2,"themeName":"甜点饮品","themeDesc":null,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/12d84c9a-fca8-488b-9d92-383cbe448555.png","sort":2},{"searchValue":null,"createBy":null,"createTime":"2021-04-13 16:53:59","updateBy":null,"updateTime":"2021-05-08 11:57:08","remark":null,"params":{},"id":3,"themeName":"汉堡披萨","themeDesc":null,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/61478234-24b7-4f7e-8ce8-05a74ae2e56b.png","sort":3},{"searchValue":null,"createBy":null,"createTime":"2021-04-13 16:56:43","updateBy":null,"updateTime":"2021-05-08 11:57:23","remark":null,"params":{},"id":4,"themeName":"炸鸡炸串","themeDesc":null,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/0348a4c8-2353-40bb-a17a-31672ecd1dad.png","sort":4},{"searchValue":null,"createBy":null,"createTime":"2021-04-13 16:58:34","updateBy":null,"updateTime":"2021-05-08 11:57:33","remark":null,"params":{},"id":5,"themeName":"美味面馆","themeDesc":null,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/0b4b5757-ac06-4ac5-91eb-dc49e4846959.png","sort":5}]
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
         * createTime : 2021-04-13 16:52:46
         * updateBy : null
         * updateTime : 2021-05-08 11:56:50
         * remark : null
         * params : {}
         * id : 1
         * themeName : 美食
         * themeDesc : 美食来找我
         * imgUrl : /prod-api/profile/upload/image/2021/05/08/f8c06dfc-9da2-41cc-9784-8cf234c999f2.png
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
        private String themeName;
        private String themeDesc;
        private String imgUrl;
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

        public String getThemeName() {
            return themeName;
        }

        public void setThemeName(String themeName) {
            this.themeName = themeName;
        }

        public String getThemeDesc() {
            return themeDesc;
        }

        public void setThemeDesc(String themeDesc) {
            this.themeDesc = themeDesc;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
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
