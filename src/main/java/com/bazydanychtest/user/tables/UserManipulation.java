package com.bazydanychtest.user.tables;

import org.springframework.stereotype.Service;

@Service
public class UserManipulation {
    private UserRepository repo;
    public String addNewUser(/*String firstname, String lastname,*/ String email, String password) {
        User user = new User();
        user.setEmail(email);
/*        user.setFirstName(firstname);
        user.setLastName(lastname);*/
        user.setPassword(password);
        repo.save(user);
        return "user created";
    }
}
