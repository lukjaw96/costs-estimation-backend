package com.costsestimationbackend.costsestimationbackend.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextValidator {


    private Pattern pattern;
    private Matcher matcher;

    private static final String TEXT_PATTERN = "^[a-z0-9_-]{1,50}$";

    public TextValidator(){
        pattern = Pattern.compile(TEXT_PATTERN);
    }

    public boolean validate(final String text){

        matcher = pattern.matcher(text);
        return matcher.matches();

    }
}
