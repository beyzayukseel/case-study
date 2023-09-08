package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.entity.User;
import com.beyzanuryuksel.amadeuscasestudy.security.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        final User user = userService.findByUserName(username);
        return MyUserDetails.build(user);
    }
}