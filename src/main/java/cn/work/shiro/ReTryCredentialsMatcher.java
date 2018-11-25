package cn.work.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: libraryOs
 * @description: 登录限制
 * @author: Aaron Ke
 * @create: 2018-11-22 16:33
 **/
public class ReTryCredentialsMatcher extends SimpleCredentialsMatcher {

    private int maxRetryNum = 5;
    private EhCacheManager ehCacheManager;

    public void setMaxRetryNum(int maxRetryNum) {
        this.maxRetryNum = maxRetryNum;
    }

    public ReTryCredentialsMatcher(EhCacheManager ehCacheManager) {
        this.ehCacheManager = ehCacheManager;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Cache<String, AtomicInteger> passwordRetryCache = ehCacheManager.getCache("passwordRetryCache");
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > maxRetryNum) {
            throw new ExcessiveAttemptsException("username: " + username + " tried to login more than " + maxRetryNum + " times in period");
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
