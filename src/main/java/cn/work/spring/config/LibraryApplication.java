package cn.work.spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.SpringServletContainerInitializer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@PropertySource(value = "classpath:jdbc.properties")
@ComponentScan(basePackages = "cn.work")
@MapperScan("cn.work.dao")
public class LibraryApplication extends SpringServletContainerInitializer {

    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${c3p0.minPoolSize}")
    private int minPoolSize;
    @Value("${c3p0.initialPoolSize}")
    private int initialPoolSize;
    @Value("${c3p0.maxPoolSize}")
    private int maxPoolSize;

    @Value("${c3p0.idleConnectionTestPeriod}")
    private int idleConnectionTestPeriod;
    private ComboPooledDataSource dataSource;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setInitialPoolSize(initialPoolSize);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setTestConnectionOnCheckin(true);
        dataSource.setIdleConnectionTestPeriod(maxPoolSize);
        return dataSource;
    }


}
