package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static Matcher getMatcher(String input, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        return pattern.matcher(input);
    }
}
