package com.tony.tonna.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TokenInterceptorConfig implements WebMvcConfigurer {
    /**
     * 这里需要将拦截器注入Bean，不然无法获取到拦截器中的 redisTemplate
     */
    @Bean
    public TokenInterceptor getTokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /** 这里的addInterceptor方法中需要添加的是：对应新注入Bean的拦截器方法 */
        registry.addInterceptor(getTokenInterceptor())
//                .addPathPatterns("/admin/validateTokenSayHello","/user/showUserInfoByToken","/logout");
                .excludePathPatterns("/login")
                .excludePathPatterns("/tonna/findAllArticle")
                .excludePathPatterns("/tonna/findAritcleByIdUser")
                .excludePathPatterns("/tonna/findAllTalk")
                .excludePathPatterns("/tonna/findUserLikeByAticleId")
                .excludePathPatterns("/tonna/findUserCollectByAticleId")
                .excludePathPatterns("/uploadFile/**")
                .excludePathPatterns("/**/*/*/*.JPEG","/**/*/*/*.jpeg")
                .excludePathPatterns("/**/*/*/*.JPG","/**/*/*/*.jpg")
                .excludePathPatterns("/**/*/*/*.PNG","/**/*/*/*.png")
                .excludePathPatterns("/**/*/*/*.GIF","/**/*/*/*.gif");
        ;
    }
}
