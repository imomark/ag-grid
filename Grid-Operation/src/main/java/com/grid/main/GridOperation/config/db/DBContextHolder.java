package com.grid.main.GridOperation.config.db;

public class DBContextHolder {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setBranchContext(String dbType) {
        threadLocal.set(dbType);
    }

    public static String getDbType() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}
