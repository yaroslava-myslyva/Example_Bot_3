package example_bot_3.handler.impl;

import example_bot_3.enums.ConversationState;
import example_bot_3.handler.UserRequestHandler;
import example_bot_3.helper.KeyboardHelper;
import example_bot_3.model.UserRequest;
import example_bot_3.model.UserSession;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import example_bot_3.service.TelegramService;
import example_bot_3.service.UserSessionService;

import java.util.List;

@Component
public class INeedHelpHandler extends UserRequestHandler {

    public static String I_NEED_HELP = "❗️Потрібна допомога";
    public static List<String> cities = List.of("Київ", "Львів");

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;
    private final UserSessionService userSessionService;

    public INeedHelpHandler(TelegramService telegramService, KeyboardHelper keyboardHelper, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
        this.userSessionService = userSessionService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate(), I_NEED_HELP);
    }

    @Override
    public void handle(UserRequest userRequest) {
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardHelper.buildCitiesMenu(cities);
        telegramService.sendMessage(userRequest.getChatId(),"Оберіть місто або опишіть вручну⤵️", replyKeyboardMarkup);

        UserSession userSession = userRequest.getUserSession();
        userSession.setState(ConversationState.WAITING_FOR_CITY);
        userSessionService.saveSession(userSession.getChatId(), userSession);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }

}
