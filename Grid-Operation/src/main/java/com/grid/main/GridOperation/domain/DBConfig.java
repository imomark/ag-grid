package com.grid.main.GridOperation.domain;

import com.grid.main.GridOperation.constant.DBType;
import lombok.Data;

@Data
public class DBConfig {
    private DBType type;
    private String database;
    private String username;
    private String password;
    private String driver;
    private String url;
}
