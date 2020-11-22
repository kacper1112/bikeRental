package org.kacper.repo;

import org.kacper.UserType;

public class Authenticator {
    public static UserType checkUser(String username, String password) {
        return UserType.NOUSER;
    }
}
