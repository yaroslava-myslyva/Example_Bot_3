package example_bot_3;

import example_bot_3.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import example_bot_3.model.UserRequest;
import example_bot_3.model.UserSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import example_bot_3.service.UserSessionService;

@Slf4j
@Component
public class ExampleBot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.username}")
    private String botUsername;

    private final Dispatcher dispatcher;
    private final UserSessionService userSessionService;

    public ExampleBot(Dispatcher dispatcher, UserSessionService userSessionService) {
        this.dispatcher = dispatcher;
        this.userSessionService = userSessionService;
    }

    /**
     * This is an entry point for any messages, or updates received from user<br>
     * Docs for "Update object: https://core.telegram.org/bots/api#update
     */
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String textFromUser = update.getMessage().getText();

            Long userId = update.getMessage().getFrom().getId();
            String userFirstName = update.getMessage().getFrom().getFirstName();
            Constants.USERNAME = userFirstName;

            log.info("[{}, {}] : {}", userId, userFirstName, textFromUser);

            Long chatId = update.getMessage().getChatId();
            UserSession session = userSessionService.getSession(chatId);

            UserRequest userRequest = UserRequest
                    .builder()
                    .update(update)
                    .userSession(session)
                    .chatId(chatId)
                    .build();

            boolean dispatched = dispatcher.dispatch(userRequest);

            if (!dispatched) {
                log.warn("Unexpected update from user");
            }
        } else {
            log.warn("Unexpected update from user");
        }
    }


    @Override
    public String getBotUsername() {
        // username which you give to your bot bia BotFather (without @)
        return botUsername;
    }

    @Override
    public String getBotToken() {
        // do not expose the token to the repository,
        // always provide it externally(for example as environmental variable)
        return botToken;
    }
}