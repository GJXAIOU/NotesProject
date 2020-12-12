package com.gjxaiou.config;

import com.gjxaiou.MyTypeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @Author GJXAIOU
 * @Date 2020/3/3 21:27
 */
@Configuration
@ComponentScan(value = "com.gjxaiou",
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM,
                classes = MyTypeFilter.class)},
        useDefaultFilters = false)
public class ScanConfig3 {
}
