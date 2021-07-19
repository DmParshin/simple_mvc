package org.example.app.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LoginRepository {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final HashMap<String, String> accounts = new HashMap<>();

    public void registration(String login, String password){
        logger.info("Registration new user: " + login);
        accounts.put(login, password);
    }

    public boolean checkUserAndPass(String user, String pass){
        for(Map.Entry<String, String> entry : accounts.entrySet()){
            if(entry.getKey().equals(user) && entry.getValue().equals(pass)){
                return true;
            }
        }
        return false;
    }

}
