package ru.ivanov.evgeny.eventscheduler.cache;

import ru.ivanov.evgeny.eventscheduler.botapi.BotState;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.UserProfileData;

public interface DataCache {
    void setUsersCurrentBotState(Long userId, BotState botState);

    BotState getUsersCurrentBotState(Long userId);

    UserProfileData getUserProfileData(Long userId);

    void saveUserProfileData(Long userId, UserProfileData userProfileData);
}
