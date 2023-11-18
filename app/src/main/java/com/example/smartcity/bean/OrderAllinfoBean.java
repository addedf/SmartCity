package com.example.smartcity.bean;

import java.util.List;

public class OrderAllinfoBean {

    /**
     * orderInfo : {"amount":34,"createTime":"2023-11-18 13:53:11","id":3140,"orderItemList":[{"createTime":"2023-11-18 13:53:11","id":1117216,"orderNo":"2023111813531048940","params":{},"productId":242,"productImage":"/prod-api/profile/upload/image/2021/05/09/bacee99e-ffe8-422e-864c-783214b102f6.jpg","productName":"中份黄焖鸡","productPrice":34,"quantity":1,"totalPrice":34,"userId":1111423}],"orderNo":"2023111813531048940","params":{},"payTime":"2023-11-18 13:53:17","paymentType":"电子支付","receiverAddress":"大连理工大学教学楼 A3-118","receiverLabel":"学校","receiverName":"王大卫","receiverPhone":"15898135276","sellerId":4,"status":"待评价","updateTime":"2023-11-18 13:53:17","userId":1111423}
     * sellerInfo : {"address":"大连甘井子区凌水镇新新园18号楼8号公建","avgCost":20,"createTime":"2021-05-08 15:07:16","deliveryTime":30,"distance":510,"id":4,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/6cf580bd-9418-43a7-8fd4-05798463616d.jpg","introduction":"田玉麟黄焖鸡成立于2015年，是大连销量遥遥领先的黄焖鸡连锁品牌","name":"田玉麟黄焖鸡","params":{},"recommend":"Y","saleNum3hour":15,"saleQuantity":668,"score":4.7,"themeId":1,"updateTime":"2021-05-12 12:42:19"}
     */

    private OrderInfoBean orderInfo;
    private SellerInfoBean sellerInfo;

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public SellerInfoBean getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(SellerInfoBean sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public static class OrderInfoBean {
        /**
         * amount : 34.0
         * createTime : 2023-11-18 13:53:11
         * id : 3140
         * orderItemList : [{"createTime":"2023-11-18 13:53:11","id":1117216,"orderNo":"2023111813531048940","params":{},"productId":242,"productImage":"/prod-api/profile/upload/image/2021/05/09/bacee99e-ffe8-422e-864c-783214b102f6.jpg","productName":"中份黄焖鸡","productPrice":34,"quantity":1,"totalPrice":34,"userId":1111423}]
         * orderNo : 2023111813531048940
         * params : {}
         * payTime : 2023-11-18 13:53:17
         * paymentType : 电子支付
         * receiverAddress : 大连理工大学教学楼 A3-118
         * receiverLabel : 学校
         * receiverName : 王大卫
         * receiverPhone : 15898135276
         * sellerId : 4
         * status : 待评价
         * updateTime : 2023-11-18 13:53:17
         * userId : 1111423
         */

        private double amount;
        private String createTime;
        private int id;
        private String orderNo;
        private ParamsBean params;
        private String payTime;
        private String paymentType;
        private String receiverAddress;
        private String receiverLabel;
        private String receiverName;
        private String receiverPhone;
        private int sellerId;
        private String status;
        private String updateTime;
        private int userId;
        private List<OrderItemListBean> orderItemList;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }

        public String getReceiverLabel() {
            return receiverLabel;
        }

        public void setReceiverLabel(String receiverLabel) {
            this.receiverLabel = receiverLabel;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getReceiverPhone() {
            return receiverPhone;
        }

        public void setReceiverPhone(String receiverPhone) {
            this.receiverPhone = receiverPhone;
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<OrderItemListBean> getOrderItemList() {
            return orderItemList;
        }

        public void setOrderItemList(List<OrderItemListBean> orderItemList) {
            this.orderItemList = orderItemList;
        }

        public static class ParamsBean {
        }

        public static class OrderItemListBean {
            /**
             * createTime : 2023-11-18 13:53:11
             * id : 1117216
             * orderNo : 2023111813531048940
             * params : {}
             * productId : 242
             * productImage : /prod-api/profile/upload/image/2021/05/09/bacee99e-ffe8-422e-864c-783214b102f6.jpg
             * productName : 中份黄焖鸡
             * productPrice : 34.0
             * quantity : 1
             * totalPrice : 34.0
             * userId : 1111423
             */

            private String createTime;
            private int id;
            private String orderNo;
            private ParamsBeanX params;
            private int productId;
            private String productImage;
            private String productName;
            private double productPrice;
            private int quantity;
            private double totalPrice;
            private int userId;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
                this.params = params;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductImage() {
                return productImage;
            }

            public void setProductImage(String productImage) {
                this.productImage = productImage;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public double getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(double productPrice) {
                this.productPrice = productPrice;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public double getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public static class ParamsBeanX {
            }
        }
    }

    public static class SellerInfoBean {
        /**
         * address : 大连甘井子区凌水镇新新园18号楼8号公建
         * avgCost : 20.0
         * createTime : 2021-05-08 15:07:16
         * deliveryTime : 30
         * distance : 510.0
         * id : 4
         * imgUrl : /prod-api/profile/upload/image/2021/05/08/6cf580bd-9418-43a7-8fd4-05798463616d.jpg
         * introduction : 田玉麟黄焖鸡成立于2015年，是大连销量遥遥领先的黄焖鸡连锁品牌
         * name : 田玉麟黄焖鸡
         * params : {}
         * recommend : Y
         * saleNum3hour : 15
         * saleQuantity : 668
         * score : 4.7
         * themeId : 1
         * updateTime : 2021-05-12 12:42:19
         */

        private String address;
        private double avgCost;
        private String createTime;
        private int deliveryTime;
        private double distance;
        private int id;
        private String imgUrl;
        private String introduction;
        private String name;
        private ParamsBeanXX params;
        private String recommend;
        private int saleNum3hour;
        private int saleQuantity;
        private double score;
        private int themeId;
        private String updateTime;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getAvgCost() {
            return avgCost;
        }

        public void setAvgCost(double avgCost) {
            this.avgCost = avgCost;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(int deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
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

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ParamsBeanXX getParams() {
            return params;
        }

        public void setParams(ParamsBeanXX params) {
            this.params = params;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public int getSaleNum3hour() {
            return saleNum3hour;
        }

        public void setSaleNum3hour(int saleNum3hour) {
            this.saleNum3hour = saleNum3hour;
        }

        public int getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(int saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getThemeId() {
            return themeId;
        }

        public void setThemeId(int themeId) {
            this.themeId = themeId;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public static class ParamsBeanXX {
        }
    }
}
