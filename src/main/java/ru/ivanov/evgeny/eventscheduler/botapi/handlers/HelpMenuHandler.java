package ru.ivanov.evgeny.eventscheduler.botapi.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.ivanov.evgeny.eventscheduler.botapi.BotState;
import ru.ivanov.evgeny.eventscheduler.botapi.InputMessageHandler;
import ru.ivanov.evgeny.eventscheduler.services.bot.MainMenuService;
import ru.ivanov.evgeny.eventscheduler.services.bot.ReplyMessagesService;

@Component
public class HelpMenuHandler implements InputMessageHandler {
    private MainMenuService mainMenuService;
    private ReplyMessagesService messagesService;

    public HelpMenuHandler(MainMenuService mainMenuService, ReplyMessagesService messagesService) {
        this.mainMenuService = mainMenuService;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId(),
                messagesService.getReplyText("reply.showHelpMenu"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_HELP_MENU;
    }
}
