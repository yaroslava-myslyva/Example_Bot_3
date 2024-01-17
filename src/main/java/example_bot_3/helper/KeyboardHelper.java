package example_bot_3.helper;

import example_bot_3.enums.ColourAndPoint;
import example_bot_3.enums.FoodAndPoint;
import example_bot_3.enums.HumanAndPoint;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
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

        List<KeyboardRow> keyboard = new java.util.ArrayList<>();
        keyboard.add(keyboardRow);
        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboard)
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    public ReplyKeyboardMarkup buildFoodMenu() {
        List<KeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new KeyboardButton(FoodAndPoint.FIRST.name));
        buttons1.add(new KeyboardButton(FoodAndPoint.SECOND.name));
        KeyboardRow row1 = new KeyboardRow(buttons1);

        List<KeyboardButton> buttons2 = new ArrayList<>();
        buttons2.add(new KeyboardButton(FoodAndPoint.THIRD.name));
        buttons2.add(new KeyboardButton(FoodAndPoint.FOURTH.name));
        KeyboardRow row2 = new KeyboardRow(buttons2);

        List<KeyboardButton> keyboardButtons = new ArrayList<>();
        keyboardButtons.add(new KeyboardButton(BTN_CANCEL));
        KeyboardRow row3 = new KeyboardRow(keyboardButtons);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboard)
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    public ReplyKeyboardMarkup buildColourMenu() {
        List<KeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new KeyboardButton(ColourAndPoint.FIRST.name));
        buttons1.add(new KeyboardButton(ColourAndPoint.SECOND.name));
        KeyboardRow row1 = new KeyboardRow(buttons1);

        List<KeyboardButton> buttons2 = new ArrayList<>();
        buttons2.add(new KeyboardButton(ColourAndPoint.THIRD.name));
        buttons2.add(new KeyboardButton(ColourAndPoint.FOURTH.name));
        KeyboardRow row2 = new KeyboardRow(buttons2);

        List<KeyboardButton> keyboardButtons = new ArrayList<>();
        keyboardButtons.add(new KeyboardButton(BTN_CANCEL));
        KeyboardRow row3 = new KeyboardRow(keyboardButtons);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboard)
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    public ReplyKeyboardMarkup buildHumanMenu() {
        List<KeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new KeyboardButton(HumanAndPoint.FIRST.name));
        buttons1.add(new KeyboardButton(HumanAndPoint.SECOND.name));
        KeyboardRow row1 = new KeyboardRow(buttons1);

        List<KeyboardButton> buttons2 = new ArrayList<>();
        buttons2.add(new KeyboardButton(HumanAndPoint.THIRD.name));
        buttons2.add(new KeyboardButton(HumanAndPoint.FOURTH.name));
        KeyboardRow row2 = new KeyboardRow(buttons2);

        List<KeyboardButton> keyboardButtons = new ArrayList<>();
        keyboardButtons.add(new KeyboardButton(BTN_CANCEL));
        KeyboardRow row3 = new KeyboardRow(keyboardButtons);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboard)
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    public ReplyKeyboardMarkup buildMenuWithCancel() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(BTN_CANCEL);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(keyboardRow);
        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboard)
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
}
