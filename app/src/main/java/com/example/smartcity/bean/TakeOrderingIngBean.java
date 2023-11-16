package com.example.smartcity.bean;

import java.util.List;

public class TakeOrderingIngBean {

    /**
     * total : 11
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":19,"userId":1111149,"sellerId":10,"score":5,"content":"真好吃，下次还来","likeNum":0,"sellerName":"大先生小碗菜","userName":"Yandel","nickName":"Yandel","avatar":"/profile/upload/2022/12/26/e4a55417-e988-4009-8186-69103fef12f0.jpg","commentDate":"2022-09-23 15:56:04","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":20,"userId":1111149,"sellerId":10,"score":5,"content":"真的很不错哦","likeNum":0,"sellerName":"大先生小碗菜","userName":"Yandel","nickName":"Yandel","avatar":"/profile/upload/2022/12/26/e4a55417-e988-4009-8186-69103fef12f0.jpg","commentDate":"2022-09-23 15:57:27","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":96,"userId":1112320,"sellerId":10,"score":5,"content":"123","likeNum":0,"sellerName":"大先生小碗菜","userName":"大王","nickName":"煌煌","avatar":"","commentDate":"2022-11-18 13:57:11","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":106,"userId":1111459,"sellerId":10,"score":3,"content":"6677","likeNum":0,"sellerName":"大先生小碗菜","userName":"charoc","nickName":"charoc","avatar":"/profile/upload/2023/03/15/1939b9a4-eb42-4697-a13f-6338461501a2.png","commentDate":"2022-11-22 11:29:39","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":138,"userId":1114576,"sellerId":10,"score":0,"content":"难吃死了老板别开门了","likeNum":0,"sellerName":"大先生小碗菜","userName":"rick","nickName":"瑞克","avatar":"/profile/2020/10/26/27e7fd58-0972-4dbf-941c-590624e6a886.png","commentDate":"2022-12-06 12:26:52","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":180,"userId":1114576,"sellerId":10,"score":0,"content":"很好哦","likeNum":0,"sellerName":"大先生小碗菜","userName":"rick","nickName":"瑞克","avatar":"/profile/2020/10/26/27e7fd58-0972-4dbf-941c-590624e6a886.png","commentDate":"2022-12-07 16:22:07","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":189,"userId":1114576,"sellerId":10,"score":0,"content":"老板好评返现吗?","likeNum":0,"sellerName":"大先生小碗菜","userName":"rick","nickName":"瑞克","avatar":"/profile/2020/10/26/27e7fd58-0972-4dbf-941c-590624e6a886.png","commentDate":"2022-12-07 20:46:22","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":381,"userId":1111788,"sellerId":10,"score":5,"content":"123","likeNum":0,"sellerName":"大先生小碗菜","userName":"lgy","nickName":"你好","avatar":"prod-api//profile/upload/2023/10/22/bff47b3e-859b-4165-9ae3-41b487858c05.jpg","commentDate":"2023-10-20 14:52:55","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":387,"userId":1124893,"sellerId":10,"score":5,"content":"21","likeNum":0,"sellerName":"大先生小碗菜","userName":"小聪明","nickName":"小聪明2号","avatar":"/prod-api/profile/upload/2023/10/20/c9c27328-920d-4baf-9b7d-355a26d44cd0.jpg","commentDate":"2023-10-20 16:18:16","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":388,"userId":1124893,"sellerId":10,"score":5,"content":"41","likeNum":0,"sellerName":"大先生小碗菜","userName":"小聪明","nickName":"小聪明2号","avatar":"/prod-api/profile/upload/2023/10/20/c9c27328-920d-4baf-9b7d-355a26d44cd0.jpg","commentDate":"2023-10-20 16:19:53","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":389,"userId":1124893,"sellerId":10,"score":5,"content":"41","likeNum":0,"sellerName":"大先生小碗菜","userName":"小聪明","nickName":"小聪明2号","avatar":"/prod-api/profile/upload/2023/10/20/c9c27328-920d-4baf-9b7d-355a26d44cd0.jpg","commentDate":"2023-10-20 16:19:53","replyContent":null,"replyTime":null}]
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
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 19
         * userId : 1111149
         * sellerId : 10
         * score : 5
         * content : 真好吃，下次还来
         * likeNum : 0
         * sellerName : 大先生小碗菜
         * userName : Yandel
         * nickName : Yandel
         * avatar : /profile/upload/2022/12/26/e4a55417-e988-4009-8186-69103fef12f0.jpg
         * commentDate : 2022-09-23 15:56:04
         * replyContent : null
         * replyTime : null
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private int userId;
        private int sellerId;
        private int score;
        private String content;
        private int likeNum;
        private String sellerName;
        private String userName;
        private String nickName;
        private String avatar;
        private String commentDate;
        private Object replyContent;
        private Object replyTime;

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

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCommentDate() {
            return commentDate;
        }

        public void setCommentDate(String commentDate) {
            this.commentDate = commentDate;
        }

        public Object getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(Object replyContent) {
            this.replyContent = replyContent;
        }

        public Object getReplyTime() {
            return replyTime;
        }

        public void setReplyTime(Object replyTime) {
            this.replyTime = replyTime;
        }

        public static class ParamsBean {
        }
    }
}
