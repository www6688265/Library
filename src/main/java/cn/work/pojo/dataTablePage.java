package cn.work.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataTablePage {
    /**
     * columns : [{"data":null,"name":"","searchable":false,"orderable":false,"search":{"value":"","regex":false}},{"data":"bookname","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"author","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"booktype","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"isbn","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"total","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"left_num","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"display","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"pic","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"bookid","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"press","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"floor","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"bookcase","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}},{"data":"level","name":"","searchable":true,"orderable":true,"search":{"value":"","regex":false}}]
     * order : [{"column":1,"dir":"asc"}]
     * search : {"value":"{\"bookname\":null,\"author\":null,\"type\":\"B\",\"press\":null,\"isbn\":null,\"total\":null,\"left\":null,\"display\":null}","regex":false}
     */

    private SearchBean search;
    private List<ColumnsBean> columns;
    @SerializedName("order")
    private List<OrderBean> order;
    private int start;
    private int length;
    private int draw;

    public String getOrderList() {
        OrderBean orderBean = this.order.get(0);
        String orderType = orderBean.getDir();
        int column = orderBean.getColumn();
        String orderName = this.columns.get(column).getData().toString();
        return "`" + orderName + "`" + " " + orderType;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public SearchBean getSearch() {
        return search;
    }

    public void setSearch(SearchBean search) {
        this.search = search;
    }

    public List<ColumnsBean> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnsBean> columns) {
        this.columns = columns;
    }

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> orderX) {
        this.order = orderX;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public static class SearchBean {
        /**
         * value : {"bookname":null,"author":null,"type":"B","press":null,"isbn":null,"total":null,"left":null,"display":null}
         * regex : false
         */

        private String value;
        private boolean regex;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isRegex() {
            return regex;
        }

        public void setRegex(boolean regex) {
            this.regex = regex;
        }
    }

    public static class ColumnsBean {
        /**
         * data : null
         * name :
         * searchable : false
         * orderable : false
         * search : {"value":"","regex":false}
         */

        private Object data;
        private String name;
        private boolean searchable;
        private boolean orderable;
        private SearchBeanX search;

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSearchable() {
            return searchable;
        }

        public void setSearchable(boolean searchable) {
            this.searchable = searchable;
        }

        public boolean isOrderable() {
            return orderable;
        }

        public void setOrderable(boolean orderable) {
            this.orderable = orderable;
        }

        public SearchBeanX getSearch() {
            return search;
        }

        public void setSearch(SearchBeanX search) {
            this.search = search;
        }

        public static class SearchBeanX {
            /**
             * value :
             * regex : false
             */

            private String value;
            private boolean regex;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public boolean isRegex() {
                return regex;
            }

            public void setRegex(boolean regex) {
                this.regex = regex;
            }
        }
    }

    public static class OrderBean {
        /**
         * column : 1
         * dir : asc
         */

        private int column;
        private String dir;

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }
    }
}
