package example_bot_3.handler.impl;

import example_bot_3.enums.ConversationState;
import example_bot_3.enums.FoodAndPoint;
import example_bot_3.handler.UserRequestHandler;
import example_bot_3.helper.KeyboardHelper;
import example_bot_3.model.UserRequest;
import example_bot_3.model.UserSession;
import example_bot_3.service.TelegramService;
import example_bot_3.service.UserSessionService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.HashMap;
import java.util.Map;

@Component
public class WhatColourHandler extends UserRequestHandler {

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;
    private final UserSessionService userSessionService;
    private final Map<String, Integer> foodPointMap = Map.of(
            FoodAndPoint.FIRST.name, FoodAndPoint.FIRST.point,
            FoodAndPoint.SECOND.name, FoodAndPoint.SECOND.point,
            FoodAndPoint.THIRD.name, FoodAndPoint.THIRD.point,
            FoodAndPoint.FOURTH.name, FoodAndPoint.FOURTH.point
    );

    public WhatColourHandler(TelegramService telegramService, KeyboardHelper keyboardHelper, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
        this.userSessionService = userSessionService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate())
                && ConversationState.WAITING_FOR_FOOD_1.equals(userRequest.getUserSession().getState());
    }

    @Override
    public void handle(UserRequest userRequest) {
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardHelper.buildColourMenu();
        telegramService.sendMessage(userRequest.getChatId(),"Выберите цвет:", replyKeyboardMarkup);

        String food = userRequest.getUpdate().getMessage().getText();
        int points = foodPointMap.getOrDefault(food, 0);

        UserSession userSession = userRequest.getUserSession();
        userSession.setPoints(points);
        userSession.setState(ConversationState.WAITING_FOR_COLOUR_2);
        userSessionService.saveSession(userSession.getChatId(), userSession);
    }

    @Override
    public boolean isGlobal() {
        return false;
    }

}
