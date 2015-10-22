package com.example.napster.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Napster on 10/10/2015.
 */
final public class ValidateUsernameAndPassword {

/*    Explanation:

            ^                 # start-of-string
            (?=.*[0-9])       # a digit must occur at least once
            (?=.*[a-z])       # a lower case letter must occur at least once
            (?=.*[A-Z])       # an upper case letter must occur at least once
            (?=.*[@#$%^&+=])  # a special character must occur at least once
            (?=\S+$)          # no whitespace allowed in the entire string
            .{8,}             # anything, at least eight places though
    $                 # end-of-string*/

    static Pattern emailPattern  = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
    static Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    public static boolean isValidEmail(String email) {

        Matcher m = emailPattern.matcher(email);
        return !m.matches();

    }

    public static boolean isValidPassword(String password) {

        Matcher m = passwordPattern.matcher(password);
        return !m.matches();

    }
}
