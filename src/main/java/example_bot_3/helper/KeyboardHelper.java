package example_bot_3.helper;

import example_bot_3.enums.ColourAndPoint;
import example_bot_3.enums.FoodAndPoint;
import example_bot_3.enums.HumanAndPoint;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

import static example_bot_3.constant.Constants.BTN_CANCEL;

/**
 * Helper class, allows to build keyboards for users
 */
@Component
public class KeyboardHelper {



    public ReplyKeyboardMarkup buildMainMenu() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Узнать");

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(keyboardRow))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    public ReplyKeyboardMarkup buildFoodMenu() {
        List<KeyboardButton> buttons1 = List.of(
                new KeyboardButton(FoodAndPoint.FIRST.name),
                new KeyboardButton(FoodAndPoint.SECOND.name));
        KeyboardRow row1 = new KeyboardRow(buttons1);

        List<KeyboardButton> buttons2 = List.of(
                new KeyboardButton(FoodAndPoint.THIRD.name),
                new KeyboardButton(FoodAndPoint.FOURTH.name));
        KeyboardRow row2 = new KeyboardRow(buttons2);

        KeyboardRow row3 = new KeyboardRow(List.of(new KeyboardButton(BTN_CANCEL)));

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(row1, row2, row3))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    public ReplyKeyboardMarkup buildColourMenu() {
        List<KeyboardButton> buttons1 = List.of(
                new KeyboardButton(ColourAndPoint.FIRST.name),
                new KeyboardButton(ColourAndPoint.SECOND.name));
        KeyboardRow row1 = new KeyboardRow(buttons1);

        List<KeyboardButton> buttons2 = List.of(
                new KeyboardButton(ColourAndPoint.THIRD.name),
                new KeyboardButton(ColourAndPoint.FOURTH.name));
        KeyboardRow row2 = new KeyboardRow(buttons2);

        KeyboardRow row3 = new KeyboardRow(List.of(new KeyboardButton(BTN_CANCEL)));

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(row1, row2, row3))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    public ReplyKeyboardMarkup buildHumanMenu() {
        List<KeyboardButton> buttons1 = List.of(
                new KeyboardButton(HumanAndPoint.FIRST.name),
                new KeyboardButton(HumanAndPoint.SECOND.name));
        KeyboardRow row1 = new KeyboardRow(buttons1);

        List<KeyboardButton> buttons2 = List.of(
                new KeyboardButton(HumanAndPoint.THIRD.name),
                new KeyboardButton(HumanAndPoint.FOURTH.name));
        KeyboardRow row2 = new KeyboardRow(buttons2);

        KeyboardRow row3 = new KeyboardRow(List.of(new KeyboardButton(BTN_CANCEL)));

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(row1, row2, row3))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    public ReplyKeyboardMarkup buildMenuWithCancel() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(BTN_CANCEL);

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(keyboardRow))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
}
