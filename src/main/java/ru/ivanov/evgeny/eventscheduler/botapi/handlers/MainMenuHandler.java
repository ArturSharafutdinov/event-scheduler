package ru.ivanov.evgeny.eventscheduler.botapi.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.ivanov.evgeny.eventscheduler.botapi.BotState;
import ru.ivanov.evgeny.eventscheduler.botapi.InputMessageHandler;
import ru.ivanov.evgeny.eventscheduler.services.bot.MainMenuService;
import ru.ivanov.evgeny.eventscheduler.services.bot.ReplyMessagesService;

@Component
public class MainMenuHandler implements InputMessageHandler {
    private ReplyMessagesService messagesService;
    private MainMenuService mainMenuService;

    public MainMenuHandler(ReplyMessagesService messagesService, MainMenuService mainMenuService) {
        this.messagesService = messagesService;
        this.mainMenuService = mainMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId(), messagesService.getReplyText("reply.showMainMenu"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_MAIN_MENU;
    }


}
