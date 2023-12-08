package com.nhnacademy.shoppingmall.common.page;

public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String keyword = "";
    // 검색시 카테고리 분류 옵션
    private Integer option = 0;


    public SearchCondition(Integer page, Integer pageSize, String keyword, Integer option) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getOption() {
        return option;
    }

    public void setOption(Integer option) {
        this.option = option;
    }

    public Integer getOffset() {
        return (page - 1) * pageSize;
    }
    public String getQueryString(Integer page) {
        StringBuilder sb = new StringBuilder();
        sb.append("?page=" + getPage() + "&pageSize=" + getPageSize() + "& option=" + getOption() + "&keyword=" +
                getKeyword());
        return sb.toString();
    }

    public String getQueryString() {
        return getQueryString(getPage());
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", keyword='" + keyword + '\'' +
                ", option=" + option +
                '}';
    }
}
