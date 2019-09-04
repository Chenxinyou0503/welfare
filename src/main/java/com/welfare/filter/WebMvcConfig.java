package com.welfare.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.util.Arrays;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/20 10:42
 * @Description:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<ServerLoginFilter> loginFilterFilterRegistrationBean() {
        FilterRegistrationBean<ServerLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ServerLoginFilter());
        registrationBean.setOrder(1);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }
    
    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigRegistrationBean() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(DataSize.of(5, DataUnit.MEGABYTES));
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }
}
