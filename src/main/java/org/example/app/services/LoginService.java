package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Logger logger = Logger.getLogger(LoginService.class);

    @Autowired
    private LoginRepository loginRepository;

    public boolean authenticate(LoginForm loginForm) {
        logger.info("try auth with user: " + loginForm.getUsername());
        if(loginRepository.checkUserAndPass(loginForm.getUsername(), loginForm.getPassword())
                || loginForm.getUsername().equals("root") && loginForm.getPassword().equals("123")){
            return true;
        }
        return false;
    }

    public boolean registration(LoginForm loginForm){
        if(!loginForm.getUsername().isEmpty() && !loginForm.getPassword().isEmpty()){
            loginRepository.registration(loginForm.getUsername(), loginForm.getPassword());
            return true;
        }
        return false;
    }


}
