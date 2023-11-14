package com.example.smartcity.bean;

public class TakeOutShopInfoBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":"2021-04-21 08:53:46","updateBy":null,"updateTime":"2021-05-11 15:40:38","remark":null,"params":{},"id":2,"name":"皖北刘哥牛肉板面","address":"辽宁省大连市甘井子区新新园79号","introduction":"各种汤面","themeId":5,"score":4.5,"saleQuantity":838,"deliveryTime":32,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/30258b9d-3cea-44a8-9ce1-03c4790870d8.jpg","avgCost":30,"other":null,"recommend":"N","distance":1500,"saleNum3hour":64}
     */

    private String msg;
    private int code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2021-04-21 08:53:46
         * updateBy : null
         * updateTime : 2021-05-11 15:40:38
         * remark : null
         * params : {}
         * id : 2
         * name : 皖北刘哥牛肉板面
         * address : 辽宁省大连市甘井子区新新园79号
         * introduction : 各种汤面
         * themeId : 5
         * score : 4.5
         * saleQuantity : 838
         * deliveryTime : 32
         * imgUrl : /prod-api/profile/upload/image/2021/05/08/30258b9d-3cea-44a8-9ce1-03c4790870d8.jpg
         * avgCost : 30.0
         * other : null
         * recommend : N
         * distance : 1500.0
         * saleNum3hour : 64
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
        private String address;
        private String introduction;
        private int themeId;
        private double score;
        private int saleQuantity;
        private int deliveryTime;
        private String imgUrl;
        private double avgCost;
        private Object other;
        private String recommend;
        private double distance;
        private int saleNum3hour;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public int getThemeId() {
            return themeId;
        }

        public void setThemeId(int themeId) {
            this.themeId = themeId;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(int saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public int getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(int deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public double getAvgCost() {
            return avgCost;
        }

        public void setAvgCost(double avgCost) {
            this.avgCost = avgCost;
        }

        public Object getOther() {
            return other;
        }

        public void setOther(Object other) {
            this.other = other;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getSaleNum3hour() {
            return saleNum3hour;
        }

        public void setSaleNum3hour(int saleNum3hour) {
            this.saleNum3hour = saleNum3hour;
        }

        public static class ParamsBean {
        }
    }
}
