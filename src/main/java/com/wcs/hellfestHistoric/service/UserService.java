package com.wcs.hellfestHistoric.service;

import com.wcs.hellfestHistoric.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface UserService {

    void autoLogin(HttpServletRequest request, String username, String password);

    User getLoggedUsername();
}
