package com.example.salle;

import java.util.Locale;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class SalleApplication extends SpringBootServletInitializer {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:/resources/application.properties,"
            + "classpath:/resources/aws.properties";
	
    public static void main(String[] args) {
        new SpringApplicationBuilder(SalleApplication.class)
        .properties(APPLICATION_LOCATIONS)
        .run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SalleApplication.class);
    }
    
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		//Member 클래스 경로
		//sessionFactoryBean.setTypeAliasesPackage("com.example.salle.domain");
		return sessionFactoryBean.getObject();
	}

	
	@Bean
	public MessageSource messageSource() {
		Locale.setDefault(Locale.KOREA);
		ResourceBundleMessageSource messageSource =
				new ResourceBundleMessageSource();
		messageSource.setBasename("label/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
