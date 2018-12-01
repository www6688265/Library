package cn.work.spring.config;

import cn.work.shiro.ReTryCredentialsMatcher;
import cn.work.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: libraryOs
 * @description: shiro配置
 * @author: Aaron Ke
 * @create: 2018-11-17 13:03
 **/
@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(retryLimitCredentialsMatcher());
        return shiroRealm;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(EhCacheManager ehCacheManager, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(ehCacheManager);
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<String, String>();
        //登出
        map.put("/background/**", "anon");
        map.put("/front/**", "anon");
        map.put("/admin/login", "anon");
        map.put("/login", "anon");//对所有用户认证
        map.put("/index*", "authc");
        map.put("/Admin_changePwd*", "authc");
        map.put("/Admin_table*", "authc");
        map.put("/Book_table*", "authc");
        map.put("/Borrow*", "authc");
        map.put("/Borrow_table*", "authc");
        map.put("/Return*", "authc");
        map.put("/Ticket_table*", "authc");
        map.put("/User_table*", "authc");
        map.put("/overDueReminder*", "authc");
        map.put("/admin/*", "authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/index");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


//    @Bean
//    public CacheManager cacheManager(){
//        return new EhCacheManager();
//    }


    @Bean
    public SessionDAO sessionDAO() {
        return new EnterpriseCacheSessionDAO();
    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO) {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        manager.setSessionDAO(sessionDAO);
        manager.setGlobalSessionTimeout(3600000);
        manager.setSessionValidationInterval(3600000);
        manager.setSessionIdUrlRewritingEnabled(false);
        return manager;
    }

    /**
     * 限制登录次数
     *
     * @return 匹配器
     */
    @Bean
    public CredentialsMatcher retryLimitCredentialsMatcher() {
        ReTryCredentialsMatcher retryLimitCredentialsMatcher = new ReTryCredentialsMatcher(ehCacheManager());
        retryLimitCredentialsMatcher.setMaxRetryNum(5);
        return retryLimitCredentialsMatcher;
    }


}
