package uz.pdp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserStatus {
    private Long chatId;
    private Status status;
    private Language language = Language.UZ;

    public UserStatus(Long chatId, Status status) {
        this.chatId = chatId;
        this.status = status;
    }
}

