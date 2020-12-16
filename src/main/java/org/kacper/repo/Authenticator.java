package org.kacper.repo;

import org.kacper.UserType;

public class Authenticator {
    public static UserType checkUser(String pesel, String password) {
        UserType type = RepoUpdateOperation.getInstance().validateUser(pesel, password);
        
        return type;
    }
}
