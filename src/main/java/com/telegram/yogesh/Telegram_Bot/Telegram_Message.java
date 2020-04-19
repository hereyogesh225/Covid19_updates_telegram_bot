package com.telegram.yogesh.Telegram_Bot;

import com.vdurmont.emoji.EmojiParser;

public class Telegram_Message {

    public static String welcomeMessage = "\n\nGet the latest COVID-19 progress at your fingertips"
            + EmojiParser.parseToUnicode(":blush:") + "\n\n" + EmojiParser.parseToUnicode(":one:")
            + " To get latest state wise statistics, send : /state \n\n"
            + EmojiParser.parseToUnicode(":two:")+" To roll dice as you got bored this qurantine send: /dice\n\n"
            + EmojiParser.parseToUnicode(":three:") + " Data source: https://www.covid19india.org/\n\n"
            + "\nCreated by: Yogesh Here ( https://t.me/Yogesh_here )\n\n";
}
