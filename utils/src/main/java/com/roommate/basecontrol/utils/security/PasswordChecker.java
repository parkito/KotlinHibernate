package com.roommate.basecontrol.utils.security;

/**
 * @author Artem Karnov @date 08.04.17.
 *         artem.karnov@t-systems.com
 */

public class PasswordChecker {
    // TODO: 08.04.17 make this shit correctly
    public static boolean check(String password1, String password2) {
        return password1.equals(password2);
    }

    public static String cryptPassword(String password) {
        return password + "encrypted";
    }
}
