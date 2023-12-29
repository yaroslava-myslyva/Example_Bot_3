package example_bot_3.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import example_bot_3.sender.VolunteerHelpBotSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.File;
import java.io.FileInputStream;

/**
 * This example_bot_3.service allows to communicate with Telegram API
 */
@Slf4j
@Component
public class TelegramService {

    private final VolunteerHelpBotSender botSender;

    public TelegramService(VolunteerHelpBotSender botSender) {
        this.botSender = botSender;
    }

    public void sendMessage(Long chatId, String text) {
        sendMessage(chatId, text, null);
    }

    public void sendMessage(Long chatId, String text, ReplyKeyboard replyKeyboard) {
        SendMessage sendMessage = SendMessage
                .builder()
                .text(text)
                .chatId(chatId.toString())
                //Other possible parse modes: MARKDOWNV2, MARKDOWN, which allows to make text bold, and all other things
                .parseMode(ParseMode.HTML)
                .replyMarkup(replyKeyboard)
                .build();
        try {
            botSender.execute(sendMessage);
        } catch (Exception e) {
            log.error("Exception: ", e);
        }
    }

    public void sendPhoto(Long chatId, String pathName, ReplyKeyboard replyKeyboard) {
        SendPhoto sendPhoto = SendPhoto.builder()
                .chatId(chatId.toString())
                .photo(new InputFile(new File(pathName)))
                .replyMarkup(replyKeyboard)
                .build();
        try {
            botSender.execute(sendPhoto);
        } catch (Exception e) {
            log.error("Exception: ", e);
        }
    }

//    private void execute(BotApiMethod botApiMethod) {
//        try {
//            botSender.execute(botApiMethod);
//        } catch (Exception e) {
//            log.error("Exception: ", e);
//        }
//    }
}
