package com.zzy.utils.Shiro;

import com.zzy.utils.properties.ShiroProperties;
import com.zzy.utils.properties.ZzyProperties;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Zzy
 * @Date 2020/12/27
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);
        filter.setSuccessUrl("/system");
        filter.setLoginUrl("/login");
        Map<String, String> filterChain = new LinkedHashMap<>();

        filterChain.put("/api/login","anon");
        filterChain.put("/api/logout","anon");
        filterChain.put("/api/register","anon");
        filterChain.put("/api/user/info/*","anon");
        filterChain.put("/api/article/list/**","anon");
        filterChain.put("/api/article/*","anon");
        filterChain.put("/api/comment/**","anon");
        filterChain.put("/api/link/**","anon");

        //放行Swagger2页面，需要放行这些
        filterChain.put("/swagger-ui.html","anon");
        filterChain.put("/swagger/**","anon");
        filterChain.put("/webjars/**", "anon");
        filterChain.put("/swagger-resources/**","anon");
        filterChain.put("/v2/**","anon");
        filterChain.put("/static/**", "anon");

        filterChain.put("/**", "user");
        filter.setFilterChainDefinitionMap(filterChain);
        return filter;
    }

    @Bean
    public SecurityManager securityManager(AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        // 需要和前端<input name="remember">中的name对应
        SimpleCookie simpleCookie = new SimpleCookie("remember");
        simpleCookie.setMaxAge(86400);
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("zzy"));
        return cookieRememberMeManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public CacheManager cacheManager(){
        return new EhCacheManager();
    }
    @Bean
    public SessionDAO sessionDAO() {
        return new EnterpriseCacheSessionDAO();
    }
    @Bean
    public AuthSessionManager sessionManager() {
        // 自定义SessionManager，校验请求头中的Token信息
        AuthSessionManager sessionManager = new AuthSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());
        // 设置 session超时时间
        sessionManager.setGlobalSessionTimeout(3600* 1000L);
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }
}
