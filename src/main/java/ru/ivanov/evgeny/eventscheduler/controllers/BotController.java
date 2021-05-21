package ru.ivanov.evgeny.eventscheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.ivanov.evgeny.eventscheduler.botapi.EventSchedulerBot;

@RestController
public class BotController {

    private final EventSchedulerBot eventSchedulerBot;

    public BotController(EventSchedulerBot eventSchedulerBot) {
        this.eventSchedulerBot = eventSchedulerBot;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
       return eventSchedulerBot.onWebhookUpdateReceived(update);
    }


}
