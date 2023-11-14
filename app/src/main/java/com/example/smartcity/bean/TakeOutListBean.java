package com.example.smartcity.bean;

import java.util.List;

public class TakeOutListBean {

    /**
     * total : 15
     * rows : [{"searchValue":null,"createBy":null,"createTime":"2021-04-13 17:16:05","updateBy":null,"updateTime":"2021-05-11 15:41:54","remark":null,"params":{},"id":1,"name":"尊宝比萨","address":"辽宁省大连市高新技术园区软景中心南门","introduction":"各种披萨","themeId":3,"score":4.9,"saleQuantity":379,"deliveryTime":30,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/9e062202-b89d-412c-ae02-5370bb3b309b.jpg","avgCost":45,"other":null,"recommend":"N","distance":620,"saleNum3hour":21},{"searchValue":null,"createBy":null,"createTime":"2021-04-21 08:53:46","updateBy":null,"updateTime":"2021-05-11 15:40:38","remark":null,"params":{},"id":2,"name":"皖北刘哥牛肉板面","address":"辽宁省大连市甘井子区新新园79号","introduction":"各种汤面","themeId":5,"score":4.5,"saleQuantity":838,"deliveryTime":32,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/30258b9d-3cea-44a8-9ce1-03c4790870d8.jpg","avgCost":30,"other":null,"recommend":"N","distance":1500,"saleNum3hour":64},{"searchValue":null,"createBy":null,"createTime":"2021-04-30 12:01:35","updateBy":null,"updateTime":"2021-05-11 15:40:46","remark":null,"params":{},"id":3,"name":"贡茶","address":"尖山街131号1-2","introduction":"黑石礁奶茶第二名","themeId":2,"score":4.8,"saleQuantity":3737,"deliveryTime":38,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/a42956a6-6ca5-47a1-8778-c93d8deba365.jpg","avgCost":20,"other":null,"recommend":"Y","distance":3700,"saleNum3hour":66},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 15:07:16","updateBy":null,"updateTime":"2021-05-12 12:42:19","remark":null,"params":{},"id":4,"name":"田玉麟黄焖鸡","address":"大连甘井子区凌水镇新新园18号楼8号公建","introduction":"田玉麟黄焖鸡成立于2015年，是大连销量遥遥领先的黄焖鸡连锁品牌","themeId":1,"score":4.7,"saleQuantity":668,"deliveryTime":30,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/6cf580bd-9418-43a7-8fd4-05798463616d.jpg","avgCost":20,"other":null,"recommend":"Y","distance":510,"saleNum3hour":15},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 15:41:38","updateBy":null,"updateTime":"2021-05-11 15:41:09","remark":null,"params":{},"id":5,"name":"炸匠先森·韩式炸鸡","address":"辽宁省大连市甘井子区软件园路新新园1号-4","introduction":"选好肉 炸好鸡","themeId":4,"score":5,"saleQuantity":570,"deliveryTime":30,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/d87665c4-79e7-47aa-aea1-d7180890ba38.jpg","avgCost":20,"other":null,"recommend":"N","distance":500,"saleNum3hour":16},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 22:41:33","updateBy":null,"updateTime":"2021-05-11 15:41:22","remark":null,"params":{},"id":8,"name":"串歹不胖","address":"凌水镇理工大学车站","introduction":"凌水镇炸物小吃第四名","themeId":4,"score":4.9,"saleQuantity":520,"deliveryTime":32,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/d8611695-af7e-4dba-a2ed-c691ab7bb80e.jpg","avgCost":20,"other":null,"recommend":"Y","distance":3000,"saleNum3hour":20},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 23:04:05","updateBy":null,"updateTime":"2021-05-11 15:41:32","remark":null,"params":{},"id":9,"name":"香满城炸肉","address":"辽宁省大连市甘井子区新新园27号1单元1层1号","introduction":"品质保证，酥脆味美","themeId":4,"score":4.8,"saleQuantity":752,"deliveryTime":30,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/63b59628-9845-4b0a-bb86-d310d058485c.jpg","avgCost":30,"other":null,"recommend":"N","distance":1600,"saleNum3hour":22},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 23:24:46","updateBy":null,"updateTime":"2021-05-12 12:42:35","remark":null,"params":{},"id":10,"name":"大先生小碗菜","address":"辽宁省大连市沙河口区文萃街","introduction":"用心做好饭","themeId":1,"score":4.8,"saleQuantity":2807,"deliveryTime":30,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/f6b0c53a-a481-4c8a-8775-dd3c1f2287e7.jpg","avgCost":20,"other":null,"recommend":"N","distance":1400,"saleNum3hour":46},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 23:45:52","updateBy":null,"updateTime":"2021-05-09 02:29:54","remark":null,"params":{},"id":11,"name":"鹿岛の日式咖喱蛋包饭","address":"辽宁省大连市甘井子区新新园114号","introduction":"大连蛋包饭第一名","themeId":1,"score":5,"saleQuantity":1887,"deliveryTime":32,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/081fc1fb-8797-44c0-87e9-815398f0b62c.jpg","avgCost":20,"other":null,"recommend":"N","distance":2000,"saleNum3hour":28},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 02:58:09","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":12,"name":"MrBurger汉堡披萨意面","address":"大连市沙河口区尖山街95-1-1","introduction":"黑石礁汉堡热销第九名","themeId":3,"score":4.7,"saleQuantity":260,"deliveryTime":32,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/81783889-66f7-4078-916f-07f073ae9200.jpg","avgCost":30,"other":null,"recommend":"N","distance":1800,"saleNum3hour":15},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 03:06:48","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":13,"name":"NO.FIVE美式汉堡","address":"辽宁省大连高新园区博广园42-7号","introduction":"纯手工牛肉汉堡","themeId":3,"score":4.9,"saleQuantity":365,"deliveryTime":40,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/8c214c52-e4ba-4ad3-b0a2-25b8fb83106a.jpg","avgCost":30,"other":null,"recommend":"N","distance":3700,"saleNum3hour":19},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 03:35:02","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":14,"name":"九叶牛肉面","address":"沙河口区西南路29-1号","introduction":"用心做好面","themeId":5,"score":4.6,"saleQuantity":1635,"deliveryTime":42,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/b19808eb-8de1-4355-ace0-6cc801f00988.jpg","avgCost":30,"other":null,"recommend":"N","distance":3100,"saleNum3hour":25},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 03:37:41","updateBy":null,"updateTime":"2021-05-12 12:41:28","remark":null,"params":{},"id":15,"name":"五爷拌面","address":"辽宁省大连市甘井子区软件园路新新园2号","introduction":"五爷拌面坚持用新鲜的食材，做好面","themeId":5,"score":4.7,"saleQuantity":2700,"deliveryTime":35,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/8d243364-fc17-4471-858c-f5dfbba19374.jpg","avgCost":30,"other":null,"recommend":"N","distance":1400,"saleNum3hour":71},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 03:59:34","updateBy":null,"updateTime":"2021-05-12 12:43:04","remark":null,"params":{},"id":16,"name":"Dance cream网红生日蛋糕","address":"辽宁省大连市沙河口区杨树东街35号1单元5楼","introduction":"所有蛋糕都含动物奶油","themeId":2,"score":4.9,"saleQuantity":1482,"deliveryTime":42,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/21c135fa-2f5c-4a1e-9d95-2d7da72b6e74.jpg","avgCost":50,"other":null,"recommend":"Y","distance":2500,"saleNum3hour":36},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 04:14:01","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":17,"name":"泷千家","address":"绘春街3-1号","introduction":null,"themeId":2,"score":5,"saleQuantity":361,"deliveryTime":30,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/3c004379-bbd9-4ab2-8553-20e41a300c90.jpg","avgCost":20,"other":null,"recommend":"N","distance":500,"saleNum3hour":13}]
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
         * createTime : 2021-04-13 17:16:05
         * updateBy : null
         * updateTime : 2021-05-11 15:41:54
         * remark : null
         * params : {}
         * id : 1
         * name : 尊宝比萨
         * address : 辽宁省大连市高新技术园区软景中心南门
         * introduction : 各种披萨
         * themeId : 3
         * score : 4.9
         * saleQuantity : 379
         * deliveryTime : 30
         * imgUrl : /prod-api/profile/upload/image/2021/05/08/9e062202-b89d-412c-ae02-5370bb3b309b.jpg
         * avgCost : 45.0
         * other : null
         * recommend : N
         * distance : 620.0
         * saleNum3hour : 21
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
