package example_bot_3.handler.impl;

import example_bot_3.enums.ColourAndPoint;
import example_bot_3.enums.ConversationState;
import example_bot_3.enums.FoodAndPoint;
import example_bot_3.handler.UserRequestHandler;
import example_bot_3.helper.KeyboardHelper;
import example_bot_3.model.UserRequest;
import example_bot_3.model.UserSession;
import example_bot_3.service.TelegramService;
import example_bot_3.service.UserSessionService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Map;

@Component
public class AboutHumanHandler extends UserRequestHandler {

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;
    private final UserSessionService userSessionService;
    private final Map<String, Integer> colourPointMap = Map.of(
            ColourAndPoint.FIRST.name, ColourAndPoint.FIRST.point,
            ColourAndPoint.SECOND.name, ColourAndPoint.SECOND.point,
            ColourAndPoint.THIRD.name, ColourAndPoint.THIRD.point,
            ColourAndPoint.FOURTH.name, ColourAndPoint.FOURTH.point
    );

    public AboutHumanHandler(TelegramService telegramService, KeyboardHelper keyboardHelper, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
        this.userSessionService = userSessionService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate())
                && ConversationState.WAITING_FOR_COLOUR_2.equals(userRequest.getUserSession().getState());
    }

    @Override
    public void handle(UserRequest userRequest) {
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardHelper.buildHumanMenu();
        telegramService.sendMessage(userRequest.getChatId(),"Кто для вас человек:", replyKeyboardMarkup);

        String colour = userRequest.getUpdate().getMessage().getText();
        int points = colourPointMap.getOrDefault(colour, 0);

        UserSession userSession = userRequest.getUserSession();
        int oldPoints = userSession.getPoints();
        userSession.setPoints(oldPoints + points);
        userSession.setAnswerAboutColor(points);
        userSession.setState(ConversationState.WAITING_FOR_HUMAN_3);
        userSessionService.saveSession(userSession.getChatId(), userSession);
    }

    @Override
    public boolean isGlobal() {
        return false;
    }

}
