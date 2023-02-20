package ru.ivanov.evgeny.eventscheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.generator.Generator;

@Component
public class StartupListener implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Generator generator;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        generator.genresInit(30);
        generator.companiesInit(15);
        generator.gamesInit(50);
        generator.platformsInit(10);
        generator.servicesInit(20);

        for (int i = 2020; i < 2023 ; i ++) {
            generator.generate(i, 8000 + (i%2020) * 3000);
        }

    }
}
