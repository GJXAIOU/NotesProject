package com.gjxaiou.dao.split;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author GJXAIOU
 * @create 2019-10-29-21:54
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        // 这里通过一个方法返回不同的 key 来区分不同的数据源
        return DynamicDataSourceHolder.getDbType();
    }
}