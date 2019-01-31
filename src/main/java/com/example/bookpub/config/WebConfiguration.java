package com.example.bookpub.config;

import com.example.bookpub.formatters.BookFormatter;
import com.example.bookpub.repository.BookRepository;
import java.util.List;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

  @Autowired
  private BookRepository bookRepository;

  @Bean
  public RemoteIpFilter remoteIpFilter() {
    return new RemoteIpFilter();
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    return new LocaleChangeInterceptor();
  }

  @Bean
  public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
    return new ByteArrayHttpMessageConverter();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(byteArrayHttpMessageConverter());
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addFormatter(new BookFormatter(bookRepository));
  }

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    configurer.setUseSuffixPatternMatch(false)
        .setUseTrailingSlashMatch(true);
  }

  //  @Override
//  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//    converters.clear();
//    converters.add(byteArrayHttpMessageConverter());
//  }
}
