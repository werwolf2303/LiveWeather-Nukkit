package com.liveweather;

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
}
