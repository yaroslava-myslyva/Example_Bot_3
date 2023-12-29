package example_bot_3.model;

import example_bot_3.enums.ConversationState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSession {
    private Long chatId;
    private ConversationState state;
    private int points;
    private int answerAboutColor;
}
