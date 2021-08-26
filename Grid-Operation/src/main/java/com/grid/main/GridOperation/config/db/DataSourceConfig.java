package com.grid.main.GridOperation.config.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grid.main.GridOperation.domain.DBConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


@Configuration
public class DataSourceConfig {

    @Value("${db.file.path}")
    private String dbFilePath;

    @Bean
    public DataSource dataSource() throws IOException {
        DataSourceRouting dataSourceRouting = new DataSourceRouting();
        dataSourceRouting.initDataSource(dataSourceMap());
        return dataSourceRouting;
    }

    private Map<Object,Object> dataSourceMap() throws IOException {
        return Arrays.asList(getConfigs())
                .stream()
                .collect(Collectors
                        .toMap(config ->config.getType()+"_"+config.getDatabase()
                                ,config->getDataSource(config)));

    }

    private Resource getDbResource(){
        return new FileSystemResource(new File(dbFilePath));
    }

    private DBConfig[] getConfigs() throws IOException {
        return new ObjectMapper().readValue(getDbResource().getFile(),DBConfig[].class);
    }

    private DataSource getDataSource(DBConfig dbConfig){
       return DataSourceBuilder.create()
                .driverClassName(dbConfig.getDriver())
                .url(dbConfig.getUrl())
                .username(dbConfig.getUsername())
                .password(dbConfig.getPassword()).build();
    }


}
