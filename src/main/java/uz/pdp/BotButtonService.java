package uz.pdp;

import org.glassfish.jersey.process.internal.Stages;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class BotButtonService {

    ReplyKeyboardMarkup mainBtns(){
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
         rows.add(row1);
         rows.add(row2);

        KeyboardButton button = new KeyboardButton();
        button.setText("Paraphraser \uD83E\uDD16");

        KeyboardButton button1 = new KeyboardButton();
        button1.setText("Text Fixer \uD83C\uDF10");

        KeyboardButton button2 = new KeyboardButton();
        button2.setText("Translater to En\uD83C\uDDEC\uD83C\uDDE7");

        KeyboardButton button3 = new KeyboardButton();
        button3.setText("Education channel \uD83D\uDCDA");
        row1.add(button);
        row1.add(button1);
        row2.add(button2);
        row2.add(button3);

        markup.setKeyboard(rows);
        markup.setResizeKeyboard(true);
        return markup;

    }
}
