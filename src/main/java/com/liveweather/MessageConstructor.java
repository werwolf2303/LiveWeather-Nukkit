package com.liveweather;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageConstructor {
    public static String constructMessage(Object... elements) throws UnsupportedOperationException {
        StringBuilder stringBuilder = new StringBuilder();
        for(Object element : elements) {
            if(element instanceof String
            || element instanceof Integer
            || element instanceof Double
            || element instanceof Boolean
            || element instanceof Float
            || element instanceof Long
            || element instanceof Character
            || element instanceof Short) {
                stringBuilder.append(element);
            } else if(element instanceof ChatColors) {
                stringBuilder.append(((ChatColors) element).getCode());
            } else {
                throw new UnsupportedOperationException("Unsupported object type: " + element.getClass().getName());
            }
        }
        return stringBuilder.toString();
    }

    public static String fromTranslation(String translationKey) {
        Pattern pattern = Pattern.compile("\\$(\\w+)\\$");
        Matcher matcher = pattern.matcher(PublicValues.language.translate(translationKey));
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String colorCode = ChatColors.valueOf(matcher.group(1)).getCode();
            matcher.appendReplacement(result, colorCode);
        }
        matcher.appendTail(result);
        return constructMessage("[", ChatColors.BOLD, ChatColors.RED, "Live", ChatColors.BLUE, "Weather", ChatColors.RESET, "] ", result.toString());
    }
}
