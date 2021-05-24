package ru.ivanov.evgeny.eventscheduler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.ivanov.evgeny.eventscheduler.botapi.EventSchedulerBot;

@Configuration
@PropertySource("classpath:telegrambot.properties")
public class BotConfig {

    @Value("${webhook}")
    private String webHookPath;

    @Value("${username}")
    private String botUsername;

    @Value("${token}")
    private String botToken;

    public String getWebHookPath() {
        return webHookPath;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public String getBotUsername() {
        return botUsername;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    @Bean
    public EventSchedulerBot eventSchedulerBot() {
        EventSchedulerBot eventSchedulerBot = new EventSchedulerBot();
        eventSchedulerBot.setBotToken(getBotToken());
        eventSchedulerBot.setBotUsername(getBotUsername());
        eventSchedulerBot.setWebHookPath(getWebHookPath());
        return new EventSchedulerBot();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
