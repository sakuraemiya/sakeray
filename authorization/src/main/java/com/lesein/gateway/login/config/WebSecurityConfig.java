//package com.lesein.gateway.login.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * @author WangJie
// * @date 2023/3/29
// */
//@Configuration
//@EnableWebSecurity// 添加 security 过滤器
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法级别的权限认证
//public class WebSecurityConfig{
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.antMatcher("/**").authorizeRequests(authorize -> authorize.anyRequest().authenticated()).build();
//    }
//}
