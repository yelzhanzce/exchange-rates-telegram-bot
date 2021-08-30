package com.yelzhan.tgbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "@KZTCurrencyChangesBOT";
    }

    @Override
    public String getBotToken() {
        return "1935914387:AAFLiQScWBy6yda6r1OfBEoSWuNI9cOF8w0";
    }

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        SendMessage sendMessage = new SendMessage();

        if (update.hasMessage()) {
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            sendMessage.setText(showInfo(update.getMessage(), sendMessage));

            try {
                try {
                    execute(sendMessage);
                } catch (TelegramApiRequestException e) {
                    sendMessage.setText("You are sent text too long time, pls retry after 2 minutes");
                    execute(sendMessage);
                }

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            String text = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            sendMessage.setText(infoCallback(text));
            sendMessage.setChatId(chatId + "");
            setInline(sendMessage);
            try {
                try {
                    execute(sendMessage);
                } catch (TelegramApiRequestException e) {
                    sendMessage.setText("You are sent text too long time, pls retry after 2 minutes");
                    execute(sendMessage);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String showInfo(Message message, SendMessage sendMessage) {
        if (message.hasText()) {
            if (message.getText().equals("/start")) {
                String text = "Hello, " + message.getFrom().getFirstName() + "!\n" +
                        "You can look information about Currency Changes of KZT \n" +
                        "Just send me command: /get_kzt_currency";
                return text;
            }
            if (message.getText().equals("/help")) {
                String text = "/get_kzt_currency - It will return information about Currency changes of KZT last 10 days!\n" +
                        "/help - List of commands!";
                return text;
            }
            if (message.getText().equals("/get_kzt_currency")) {
                setInline(sendMessage);
                String text = "Choose...";
                return text;
            }
        }
        return "I dont understand you! \nOkay bro, I will help you \nJust send me this command: /help";
    }

    private String infoCallback(String text) {

        if (text.equals("USD")) {
            Currency currency = new Currency("USD");
            return currency.getChanges();
        }
        if (text.equals("EUR")) {
            Currency currency = new Currency("EUR");
            return currency.getChanges();
        }
        if (text.equals("RUB")) {
            Currency currency = new Currency("RUB");
            return currency.getChanges();
        }

        return text;
    }

    private void setInline(SendMessage sendMessage) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("USD");
        inlineKeyboardButton.setCallbackData("USD");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("EUR");
        inlineKeyboardButton2.setCallbackData("EUR");
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("RUB");
        inlineKeyboardButton3.setCallbackData("RUB");

        inlineKeyboardButtons.add(inlineKeyboardButton);
        inlineKeyboardButtons.add(inlineKeyboardButton2);
        inlineKeyboardButtons.add(inlineKeyboardButton3);


        buttons.add(inlineKeyboardButtons);

        inlineKeyboardMarkup.setKeyboard(buttons);
    }


    public static void main(String[] args) throws TelegramApiException {
        Bot bot = new Bot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }
}