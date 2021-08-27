package com.grid.main.GridOperation.interceptor;

import com.grid.main.GridOperation.config.db.DBContextHolder;
import com.grid.main.GridOperation.exceptions.DataSourceKeyNotPresent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class DataSourceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws DataSourceKeyNotPresent {
        String key = request.getHeader("key");
        log.info("interceptor called");
        if(key == null || key.isEmpty())
            throw new DataSourceKeyNotPresent("DataSource Key Not Present in Header");
        else
            DBContextHolder.setDBContext(key);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        DBContextHolder.clear();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
