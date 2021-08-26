package com.grid.main.GridOperation.config.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

public class DataSourceRouting extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.getDbType();
    }

    public void initDataSource(Map<Object,Object> dataSourceMap){
        this.setTargetDataSources(dataSourceMap);
    }
}
