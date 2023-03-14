package com.example.em.config;

import com.example.em.dto.response.AdminLoginDTO;
import com.example.em.dto.response.ManagerLoginDTO;
import com.example.em.dto.response.UserLoginDTO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class Author {
    public static Boolean isAuthorOfAdmin(HttpSession session){
        boolean result = false;
        AdminLoginDTO admin = (AdminLoginDTO) session.getAttribute("AD");
        if(admin != null){
            result = true;
        }
        return result;
    }

    public static Boolean isAuthorOfManager(HttpSession session){
        boolean result = false;
        ManagerLoginDTO manager = (ManagerLoginDTO) session.getAttribute("MA");
        if(manager != null){
            result = true;
        }
        return result;
    }

    public static Boolean isAuthorOfUser(HttpSession session){
        boolean result = false;
        UserLoginDTO user = (UserLoginDTO) session.getAttribute("US");
        if(user != null){
            result = true;
        }
        return result;
    }
}