package kr.co.ooweat.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(value = "kr.co.ooweat.mappers", sqlSessionFactoryRef = "couponSqlSessionFactory")
public class DBConfig {

    @Primary
    @Bean(name = "coupon")
    @ConfigurationProperties(prefix = "spring.datasource.coupon")
    public DataSource couponDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "couponSqlSessionFactory")
    public SqlSessionFactory couponSqlSessionFactory(@Qualifier("coupon") DataSource membershipDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(membershipDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("kr.co.ooweat.model");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "couponSessionTemplate")
    public SqlSessionTemplate couponSqlSessionTemplate(@Qualifier("couponSqlSessionFactory") SqlSessionFactory couponSqlSessionFactory) {
        return new SqlSessionTemplate(couponSqlSessionFactory);
    }
}
