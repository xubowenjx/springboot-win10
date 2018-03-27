package com.xbw.website.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebConfig {
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;

		return new HttpMessageConverters(converter);

	}

	@Bean
	@SuppressWarnings("rawtypes")
	public RedisSerializer fastJson2JsonRedisSerializer() {
		return new FastJson2JsonRedisSerializer<Object>(Object.class);
	}
	/**
	 * s使用fastJson进行redis的序列号
	 * @param factory
	 * @param fastJson2JsonRedisSerializer
	 * @return
	 */
	@Bean
	@SuppressWarnings("rawtypes")
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory,
			RedisSerializer fastJson2JsonRedisSerializer) {
		StringRedisTemplate template = new StringRedisTemplate(factory);

		template.setValueSerializer(fastJson2JsonRedisSerializer);

		template.afterPropertiesSet();
		return template;
	}
	@Bean
	public LocaleResolver localeResolver() {
		return new I18nResolver();
	}
}