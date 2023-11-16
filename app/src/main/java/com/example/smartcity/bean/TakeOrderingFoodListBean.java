package com.example.smartcity.bean;

import java.util.List;

public class TakeOrderingFoodListBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":null,"createTime":"2021-05-08 15:44:47","updateBy":null,"updateTime":"2021-05-08 20:23:30","remark":null,"params":{},"id":3,"categoryId":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/f9fa9bc2-edf6-4acb-9ce7-bf9baef5f295.jpg","name":"黄焖鸡+两份配菜+米饭套餐","price":19.88,"detail":"中份黄焖鸡+稻花香米饭+配菜2份","status":"1","saleQuantity":121,"favorRate":99,"sellerId":4},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 15:50:28","updateBy":null,"updateTime":"2021-05-08 20:23:41","remark":null,"params":{},"id":6,"categoryId":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/c134c177-9d06-4e87-833b-65a5e33f606f.jpg","name":"中份黄焖鸡+米饭套餐","price":34,"detail":"中份黄焖鸡米饭+稻花香米一份","status":"1","saleQuantity":48,"favorRate":98,"sellerId":4},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 15:51:00","updateBy":null,"updateTime":"2021-05-08 20:23:44","remark":null,"params":{},"id":7,"categoryId":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/8fe93426-79f9-429b-94c4-80777836be61.jpg","name":"大份黄焖鸡","price":41.8,"detail":"大份黄焖鸡+稻花香米饭一份","status":"1","saleQuantity":70,"favorRate":99,"sellerId":4},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 15:54:20","updateBy":null,"updateTime":"2021-05-09 14:36:06","remark":null,"params":{},"id":9,"categoryId":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/84f8d5ff-6b08-421a-9a63-88384ad10e06.jpg","name":"稻花香米饭","price":2.9,"detail":"米饭","status":"1","saleQuantity":57,"favorRate":99,"sellerId":4},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 15:32:41","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":241,"categoryId":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/cfceb8c8-ee4c-4e56-8839-e88c6d6afeb3.jpg","name":"黄焖鸡套餐","price":19.88,"detail":"黄焖鸡+两份配菜+米饭套餐","status":"1","saleQuantity":125,"favorRate":98,"sellerId":4},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 15:33:21","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":242,"categoryId":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/bacee99e-ffe8-422e-864c-783214b102f6.jpg","name":"中份黄焖鸡","price":34,"detail":"中份黄焖鸡+米饭套餐","status":"1","saleQuantity":50,"favorRate":98,"sellerId":4},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 15:33:53","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":243,"categoryId":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/5c606fd0-407b-48e5-8369-f2d220a18f17.jpg","name":"大份黄焖鸡","price":41.8,"detail":"大份黄焖鸡+米饭","status":"1","saleQuantity":68,"favorRate":98,"sellerId":4}]
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
         * createTime : 2021-05-08 15:44:47
         * updateBy : null
         * updateTime : 2021-05-08 20:23:30
         * remark : null
         * params : {}
         * id : 3
         * categoryId : 5
         * imgUrl : /prod-api/profile/upload/image/2021/05/08/f9fa9bc2-edf6-4acb-9ce7-bf9baef5f295.jpg
         * name : 黄焖鸡+两份配菜+米饭套餐
         * price : 19.88
         * detail : 中份黄焖鸡+稻花香米饭+配菜2份
         * status : 1
         * saleQuantity : 121
         * favorRate : 99.0
         * sellerId : 4
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private int categoryId;
        private String imgUrl;
        private String name;
        private double price;
        private String detail;
        private String status;
        private int saleQuantity;
        private double favorRate;
        private int sellerId;

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

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(int saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public double getFavorRate() {
            return favorRate;
        }

        public void setFavorRate(double favorRate) {
            this.favorRate = favorRate;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public static class ParamsBean {
        }
    }
}
