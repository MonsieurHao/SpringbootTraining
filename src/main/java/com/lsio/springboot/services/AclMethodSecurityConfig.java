package com.lsio.springboot.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)    //enable Expression-Based Access Control by setting prePostEnabled to true
public class AclMethodSecurityConfiguration 
  extends GlobalMethodSecurityConfiguration {

    @Autowired
    MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler;

    @Autowired
    DataSource dataSource;

    
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return defaultMethodSecurityExpressionHandler;
    }

    @Bean
    public MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler() {
      DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
      AclPermissionEvaluator permissionEvaluator = new AclPermissionEvaluator(aclService());
      expressionHandler.setPermissionEvaluator(permissionEvaluator);
      return expressionHandler;
    }
    
    @Bean 
    public JdbcMutableAclService aclService() { 
        return new JdbcMutableAclService(dataSource, lookupStrategy(), aclCache());     //JdbcMutableAclService uses JDBCTemplate to simplify database access
    }


    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Bean
    public PermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
    }
    
    @Bean
    public EhCacheBasedAclCache aclCache() {
        return new EhCacheBasedAclCache(
          aclEhCacheFactoryBean().getObject(), 
          permissionGrantingStrategy(), 
          aclAuthorizationStrategy()
        );
    }

    @Bean
    public EhCacheFactoryBean aclEhCacheFactoryBean() {
        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(aclCacheManager().getObject());
        ehCacheFactoryBean.setCacheName("aclCache");
        return ehCacheFactoryBean;
    }
    
    @Bean
    public EhCacheManagerFactoryBean aclCacheManager() {
        return new EhCacheManagerFactoryBean();
    }
    
    @Bean 
    public LookupStrategy lookupStrategy() { 
        return new BasicLookupStrategy(
          dataSource, 
          aclCache(), 
          aclAuthorizationStrategy(), 
          new ConsoleAuditLogger()
        );
    }
}
