package example_bot_3.handler.impl;

import example_bot_3.constant.Constants;
import example_bot_3.enums.ColourAndPoint;
import example_bot_3.enums.ConversationState;
import example_bot_3.enums.HumanAndPoint;
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
public class ResultHandler extends UserRequestHandler {

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;
    private final UserSessionService userSessionService;
    private final Map<String, Integer> humanPointMap;

    {
        humanPointMap = new HashMap<>();
        humanPointMap.put(HumanAndPoint.FIRST.name, HumanAndPoint.FIRST.point);
        humanPointMap.put(HumanAndPoint.SECOND.name, HumanAndPoint.SECOND.point);
        humanPointMap.put(HumanAndPoint.THIRD.name, HumanAndPoint.THIRD.point);
        humanPointMap.put(HumanAndPoint.FOURTH.name, HumanAndPoint.FOURTH.point);
    }

    public ResultHandler(TelegramService telegramService, KeyboardHelper keyboardHelper, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
        this.userSessionService = userSessionService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate())
                && ConversationState.WAITING_FOR_HUMAN_3.equals(userRequest.getUserSession().getState());
    }

    @Override
    public void handle(UserRequest userRequest) {
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardHelper.buildMainMenu();

        String human = userRequest.getUpdate().getMessage().getText();
        int points = humanPointMap.getOrDefault(human, 0);

        UserSession userSession = userRequest.getUserSession();
        int colourPoints = userSession.getAnswerAboutColor();
        int oldPoints = userSession.getPoints();
        int resultPoints = oldPoints + points;

        String textResult;
        String imageResult;

        switch (resultPoints) {
            case 3:
            case 4:
                textResult = Constants.WHITE_TEXT;
                imageResult = Constants.WHITE_IMAGE;
                break;
            case 5:
            case 6:
                if (colourPoints == 1) {
                    textResult = Constants.WHITE_TEXT;
                    imageResult = Constants.WHITE_IMAGE;
                } else if (colourPoints == 2 || colourPoints == 3) {
                    textResult = Constants.GRAY_TEXT;
                    imageResult = Constants.GRAY_IMAGE;
                } else {
                    textResult = Constants.THREE_COLOR_TEXT;
                    imageResult = Constants.THREE_COLOR_IMAGE;
                }
                break;
            case 7:
            case 8:
                if (colourPoints == 1 || colourPoints == 4) {
                    textResult = Constants.THREE_COLOR_TEXT;
                    imageResult = Constants.THREE_COLOR_IMAGE;
                } else if (colourPoints == 2) {
                    textResult = Constants.GRAY_TEXT;
                    imageResult = Constants.GRAY_IMAGE;
                } else {
                    textResult = Constants.GINGER_TEXT;
                    imageResult = Constants.GINGER_IMAGE;
                }
                break;
            case 9:
            case 10:
                if (colourPoints == 1) {
                    textResult = Constants.THREE_COLOR_TEXT;
                    imageResult = Constants.THREE_COLOR_IMAGE;
                } else if (colourPoints == 2 || colourPoints == 3) {
                    textResult = Constants.GINGER_TEXT;
                    imageResult = Constants.GINGER_IMAGE;
                } else {
                    textResult = Constants.BLACK_TEXT;
                    imageResult = Constants.BLACK_IMAGE;
                }
                break;
            case 11:
            case 12:
                textResult = Constants.BLACK_TEXT;
                imageResult = Constants.BLACK_IMAGE;
                break;
            default:
                textResult = Constants.WHITE_TEXT;
                imageResult = Constants.WHITE_IMAGE;
                System.out.println("Ошибка");
                System.out.println("resultPoints " + resultPoints);

        }

        System.out.println("resultPoints " + resultPoints);
        System.out.println("colourPoints " + colourPoints);
        System.out.println(textResult);

        telegramService.sendMessage(userRequest.getChatId(), textResult, replyKeyboardMarkup);
        telegramService.sendPhoto(userRequest.getChatId(), imageResult, replyKeyboardMarkup);

        userSession.setPoints(0);
        userSession.setAnswerAboutColor(0);
        userSession.setState(ConversationState.CONVERSATION_STARTED);
        userSessionService.saveSession(userSession.getChatId(), userSession);
    }

    @Override
    public boolean isGlobal() {
        return false;
    }

}
