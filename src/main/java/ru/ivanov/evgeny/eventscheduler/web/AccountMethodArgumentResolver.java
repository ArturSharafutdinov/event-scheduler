package ru.ivanov.evgeny.eventscheduler.web;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.services.auth.AccountService;

public class AccountMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private AccountService accountService;

    public AccountMethodArgumentResolver(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Account.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = (Authentication) webRequest.getUserPrincipal();
        if (authentication != null) {
            return accountService.findAccount(authentication);
        }
        return null;
    }
}
