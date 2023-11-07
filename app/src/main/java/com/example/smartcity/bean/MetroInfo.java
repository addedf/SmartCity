package com.example.smartcity.bean;

import java.util.List;

public class MetroInfo {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"id":31,"name":"2号线","first":"内环(积水潭)","end":"积水潭","startTime":"05:10","endTime":"22:20","cityId":1,"stationsNumber":24,"km":120,"runStationsName":"宣武门","metroStepList":[{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":457,"name":"积水潭","seq":1,"lineId":31,"firstCh":"J"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":458,"name":"鼓楼大街","seq":2,"lineId":31,"firstCh":"G"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":459,"name":"安定门","seq":3,"lineId":31,"firstCh":"A"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":460,"name":"雍和宫","seq":4,"lineId":31,"firstCh":"Y"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":461,"name":"东直门","seq":5,"lineId":31,"firstCh":"D"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":462,"name":"东四十条","seq":6,"lineId":31,"firstCh":"D"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":463,"name":"朝阳门","seq":7,"lineId":31,"firstCh":"C"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":464,"name":"建国门","seq":8,"lineId":31,"firstCh":"J"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":465,"name":"北京站","seq":9,"lineId":31,"firstCh":"B"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":466,"name":"崇文门","seq":10,"lineId":31,"firstCh":"C"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":467,"name":"前门","seq":11,"lineId":31,"firstCh":"Q"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":468,"name":"和平门","seq":12,"lineId":31,"firstCh":"H"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":469,"name":"宣武门","seq":13,"lineId":31,"firstCh":"X"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":470,"name":"长椿街","seq":14,"lineId":31,"firstCh":"C"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":471,"name":"复兴门","seq":15,"lineId":31,"firstCh":"F"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":472,"name":"阜成门","seq":16,"lineId":31,"firstCh":"F"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":473,"name":"车公庄","seq":17,"lineId":31,"firstCh":"C"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":474,"name":"西直门","seq":18,"lineId":31,"firstCh":"X"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":475,"name":"积水潭","seq":19,"lineId":31,"firstCh":"J"}],"remainingTime":24}
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
         * id : 31
         * name : 2号线
         * first : 内环(积水潭)
         * end : 积水潭
         * startTime : 05:10
         * endTime : 22:20
         * cityId : 1
         * stationsNumber : 24
         * km : 120
         * runStationsName : 宣武门
         * metroStepList : [{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":457,"name":"积水潭","seq":1,"lineId":31,"firstCh":"J"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":458,"name":"鼓楼大街","seq":2,"lineId":31,"firstCh":"G"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":459,"name":"安定门","seq":3,"lineId":31,"firstCh":"A"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":460,"name":"雍和宫","seq":4,"lineId":31,"firstCh":"Y"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":461,"name":"东直门","seq":5,"lineId":31,"firstCh":"D"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:12","remark":null,"params":{},"id":462,"name":"东四十条","seq":6,"lineId":31,"firstCh":"D"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":463,"name":"朝阳门","seq":7,"lineId":31,"firstCh":"C"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":464,"name":"建国门","seq":8,"lineId":31,"firstCh":"J"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":465,"name":"北京站","seq":9,"lineId":31,"firstCh":"B"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":466,"name":"崇文门","seq":10,"lineId":31,"firstCh":"C"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":467,"name":"前门","seq":11,"lineId":31,"firstCh":"Q"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":468,"name":"和平门","seq":12,"lineId":31,"firstCh":"H"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":469,"name":"宣武门","seq":13,"lineId":31,"firstCh":"X"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":470,"name":"长椿街","seq":14,"lineId":31,"firstCh":"C"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":471,"name":"复兴门","seq":15,"lineId":31,"firstCh":"F"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":472,"name":"阜成门","seq":16,"lineId":31,"firstCh":"F"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":473,"name":"车公庄","seq":17,"lineId":31,"firstCh":"C"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":474,"name":"西直门","seq":18,"lineId":31,"firstCh":"X"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 15:47:13","remark":null,"params":{},"id":475,"name":"积水潭","seq":19,"lineId":31,"firstCh":"J"}]
         * remainingTime : 24
         */

        private int id;
        private String name;
        private String first;
        private String end;
        private String startTime;
        private String endTime;
        private int cityId;
        private int stationsNumber;
        private int km;
        private String runStationsName;
        private int remainingTime;
        private List<MetroStepListBean> metroStepList;

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

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getStationsNumber() {
            return stationsNumber;
        }

        public void setStationsNumber(int stationsNumber) {
            this.stationsNumber = stationsNumber;
        }

        public int getKm() {
            return km;
        }

        public void setKm(int km) {
            this.km = km;
        }

        public String getRunStationsName() {
            return runStationsName;
        }

        public void setRunStationsName(String runStationsName) {
            this.runStationsName = runStationsName;
        }

        public int getRemainingTime() {
            return remainingTime;
        }

        public void setRemainingTime(int remainingTime) {
            this.remainingTime = remainingTime;
        }

        public List<MetroStepListBean> getMetroStepList() {
            return metroStepList;
        }

        public void setMetroStepList(List<MetroStepListBean> metroStepList) {
            this.metroStepList = metroStepList;
        }

        public static class MetroStepListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2018-07-23 02:28:40
             * updateBy : null
             * updateTime : 2021-04-05 15:47:12
             * remark : null
             * params : {}
             * id : 457
             * name : 积水潭
             * seq : 1
             * lineId : 31
             * firstCh : J
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
            private int seq;
            private int lineId;
            private String firstCh;

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

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public int getLineId() {
                return lineId;
            }

            public void setLineId(int lineId) {
                this.lineId = lineId;
            }

            public String getFirstCh() {
                return firstCh;
            }

            public void setFirstCh(String firstCh) {
                this.firstCh = firstCh;
            }

            public static class ParamsBean {
            }
        }
    }
}
