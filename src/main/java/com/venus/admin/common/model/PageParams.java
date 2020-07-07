package com.venus.admin.common.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.venus.admin.common.constants.CommonConstants;
import com.venus.admin.utils.BeanConvertUtils;
import com.venus.admin.utils.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: tcg
 * @Date: 2020/6/22 14:27
 * @Version 1.0
 */

public class PageParams extends Page implements Serializable {

    private static final long serialVersionUID = 2415817880677528038L;

    private int page = CommonConstants.DEFAULT_PAGE;
    private int limit = CommonConstants.DEFAULT_LIMIT;
    private String sort;
    private String order;
    private Map<String, Object> requestMap = Maps.newHashMap();

    /**
     * 排序
     */
    private String orderBy;

    public PageParams() {
        requestMap = Maps.newHashMap();
    }

    public PageParams(Map map) {
        if (map == null) {
            map = Maps.newHashMap();
        }
        this.page = Integer.parseInt(map.getOrDefault(CommonConstants.PAGE_KEY, CommonConstants.DEFAULT_PAGE).toString());
        this.limit = Integer.parseInt(map.getOrDefault(CommonConstants.PAGE_LIMIT_KEY, CommonConstants.DEFAULT_LIMIT).toString());
        this.sort = (String) map.getOrDefault(CommonConstants.PAGE_SORT_KEY, "");
        this.order = (String) map.getOrDefault(CommonConstants.PAGE_ORDER_KEY, "");
        super.setCurrent(page);
        super.setSize(limit);
        map.remove(CommonConstants.PAGE_KEY);
        map.remove(CommonConstants.PAGE_LIMIT_KEY);
        map.remove(CommonConstants.PAGE_SORT_KEY);
        map.remove(CommonConstants.PAGE_ORDER_KEY);
        requestMap.putAll(map);
    }

    public PageParams(int page, int limit) {
        this(page, limit, "", "");
    }

    public PageParams(int page, int limit, String sort, String order) {
        this.page = page;
        this.limit = limit;
        this.sort = sort;
        this.order = order;
    }

    public int getPage() {
        if (page <= CommonConstants.MIN_PAGE) {
            page = 1;
        }
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        if (limit <= CommonConstants.MAX_LIMIT) {
            limit = CommonConstants.MAX_LIMIT;
        }
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderBy() {
        if (StringUtils.isBlank(order)) {
            order = "asc";
        }
        if (StringUtils.isNotBlank(sort)) {
            this.setOrderBy(String.format("%s %s",StringUtils.camelToUnderline(sort),order));
        }
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public <T> T mapToObject(Class<T> t) {
        return BeanConvertUtils.mapToObject(this.requestMap, t);
    }

    public Map<String, Object> getRequestMap() {
        return requestMap;
    }

    public void setRequestMap(Map<String, Object> requestMap) {
        this.requestMap = requestMap;
    }

}
