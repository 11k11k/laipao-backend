package com.laioj.project.once;

import org.springframework.util.DigestUtils;

public class md5 {
    public static void main(String[] args) {
         String SALT = "laipao";
        String userPassword = "123456789";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        System.out.println(encryptPassword);
    }
}
