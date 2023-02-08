package com.bazydanychtest.security;

import com.bazydanychtest.user.tables.CommentService;
import com.bazydanychtest.user.tables.User;
import com.bazydanychtest.user.tables.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private CommentService commentService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> user = repository.findByUserName(userName);
        user.get().setLastVisit(commentService.dateCurrent()+" "+commentService.timeCurrent());
        repository.save(user.get());
        return user.map(UserInfoUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found exception " + userName));

    }
}
