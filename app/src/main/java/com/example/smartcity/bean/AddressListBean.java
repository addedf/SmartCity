package com.example.smartcity.bean;

import java.util.List;

public class AddressListBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":null,"createTime":"2023-11-17 16:42:37","updateBy":null,"updateTime":"2023-11-17 16:42:36","remark":null,"params":{},"id":1355,"userId":1111423,"name":"王大卫","phone":"15898135276","addressDetail":"大连理工大学教学楼 A3-118","houseNumber":"","label":"学校"}]
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
         * createTime : 2023-11-17 16:42:37
         * updateBy : null
         * updateTime : 2023-11-17 16:42:36
         * remark : null
         * params : {}
         * id : 1355
         * userId : 1111423
         * name : 王大卫
         * phone : 15898135276
         * addressDetail : 大连理工大学教学楼 A3-118
         * houseNumber :
         * label : 学校
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private int userId;
        private String name;
        private String phone;
        private String addressDetail;
        private String houseNumber;
        private String label;

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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public static class ParamsBean {
        }
    }
}
