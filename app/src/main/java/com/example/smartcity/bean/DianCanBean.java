package com.example.smartcity.bean;

import java.util.List;

public class DianCanBean {

    /**
     * code : 200
     * data : [{"categoryId":5,"createTime":"2021-05-08 15:44:47","detail":"中份黄焖鸡+稻花香米饭+配菜2份","favorRate":99,"id":3,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/f9fa9bc2-edf6-4acb-9ce7-bf9baef5f295.jpg","index":0,"name":"黄焖鸡+两份配菜+米饭套餐","params":{},"price":19.88,"saleQuantity":121,"sellerId":4,"status":"1","total":0,"updateTime":"2021-05-08 20:23:30"},{"categoryId":5,"createTime":"2021-05-08 15:50:28","detail":"中份黄焖鸡米饭+稻花香米一份","favorRate":98,"id":6,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/c134c177-9d06-4e87-833b-65a5e33f606f.jpg","index":0,"name":"中份黄焖鸡+米饭套餐","params":{},"price":34,"saleQuantity":48,"sellerId":4,"status":"1","total":0,"updateTime":"2021-05-08 20:23:41"},{"categoryId":5,"createTime":"2021-05-08 15:51:00","detail":"大份黄焖鸡+稻花香米饭一份","favorRate":99,"id":7,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/8fe93426-79f9-429b-94c4-80777836be61.jpg","index":0,"name":"大份黄焖鸡","params":{},"price":41.8,"saleQuantity":70,"sellerId":4,"status":"1","total":0,"updateTime":"2021-05-08 20:23:44"},{"categoryId":5,"createTime":"2021-05-08 15:54:20","detail":"米饭","favorRate":99,"id":9,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/84f8d5ff-6b08-421a-9a63-88384ad10e06.jpg","index":0,"name":"稻花香米饭","params":{},"price":2.9,"saleQuantity":57,"sellerId":4,"status":"1","total":0,"updateTime":"2021-05-09 14:36:06"},{"categoryId":5,"createTime":"2021-05-09 15:32:41","detail":"黄焖鸡+两份配菜+米饭套餐","favorRate":98,"id":241,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/cfceb8c8-ee4c-4e56-8839-e88c6d6afeb3.jpg","index":0,"name":"黄焖鸡套餐","params":{},"price":19.88,"saleQuantity":125,"sellerId":4,"status":"1","total":0},{"categoryId":5,"createTime":"2021-05-09 15:33:21","detail":"中份黄焖鸡+米饭套餐","favorRate":98,"id":242,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/bacee99e-ffe8-422e-864c-783214b102f6.jpg","index":0,"name":"中份黄焖鸡","params":{},"price":34,"saleQuantity":50,"sellerId":4,"status":"1","total":0},{"categoryId":5,"createTime":"2021-05-09 15:33:53","detail":"大份黄焖鸡+米饭","favorRate":98,"id":243,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/5c606fd0-407b-48e5-8369-f2d220a18f17.jpg","index":0,"name":"大份黄焖鸡","params":{},"price":41.8,"saleQuantity":68,"sellerId":4,"status":"1","total":0}]
     * msg : 操作成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * categoryId : 5
         * createTime : 2021-05-08 15:44:47
         * detail : 中份黄焖鸡+稻花香米饭+配菜2份
         * favorRate : 99.0
         * id : 3
         * imgUrl : /prod-api/profile/upload/image/2021/05/08/f9fa9bc2-edf6-4acb-9ce7-bf9baef5f295.jpg
         * index : 0
         * name : 黄焖鸡+两份配菜+米饭套餐
         * params : {}
         * price : 19.88
         * saleQuantity : 121
         * sellerId : 4
         * status : 1
         * total : 0.0
         * updateTime : 2021-05-08 20:23:30
         */

        private int categoryId;
        private String createTime;
        private String detail;
        private double favorRate;
        private int id;
        private String imgUrl;
        private int index;
        private String name;
        private ParamsBean params;
        private double price;
        private int saleQuantity;
        private int sellerId;
        private String status;
        private double total;
        private String updateTime;

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public double getFavorRate() {
            return favorRate;
        }

        public void setFavorRate(double favorRate) {
            this.favorRate = favorRate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(int saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public static class ParamsBean {
        }
    }
}
