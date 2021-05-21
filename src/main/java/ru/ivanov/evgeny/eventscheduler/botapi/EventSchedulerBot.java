package ru.ivanov.evgeny.eventscheduler.botapi;


import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class EventSchedulerBot extends TelegramWebhookBot {

    private String webHookPath;

    private String botUsername;

    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        Long chat_id = update.getMessage().getChatId();
        return new SendMessage(chat_id.toString(), update.getMessage().getText());
    }


    @Override
    public String getBotPath() {
        return webHookPath;
    }

    public String getWebHookPath() {
        return webHookPath;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public EventSchedulerBot(DefaultBotOptions options) {
        super(options);
    }

    public EventSchedulerBot() {
        super();
    }


}
