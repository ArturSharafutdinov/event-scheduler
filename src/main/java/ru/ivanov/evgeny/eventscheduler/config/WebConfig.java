package ru.ivanov.evgeny.eventscheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.ivanov.evgeny.eventscheduler.services.auth.AccountService;
import ru.ivanov.evgeny.eventscheduler.web.AccountMethodArgumentResolver;

import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AccountService accountService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AccountMethodArgumentResolver(accountService));
    }

}
