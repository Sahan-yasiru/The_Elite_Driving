package org.example.orm_final.dao.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;


public class BCryptHashing {

    public static String getHashedPassword(String password) {
        Hasher hasher=BCrypt.withDefaults();
        String hashedPassword=hasher.hashToString(12,password.toCharArray());
        return hashedPassword;

    }
    public static boolean chackHashedPassword(String password,String hashedPassword) {
        Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }
}
