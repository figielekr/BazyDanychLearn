package com.bazydanychtest;

import com.bazydanychtest.user.tables.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //nie bedzie tworzyc testowej bazy danych w pamieci
@Rollback(value = false)
class BazyDanychTestApplicationTests {
	@Autowired private UserRepository repo;
	@Autowired private CommentRepository repoComment;
	@Autowired private CommentService serviceComment;

	@Test
	public void addNewUSer() {
		User user = new User();
		user.setEmail("uzytkownik@gmail.com");
		user.setFirstName("Ola");
		user.setLastName("Kowalska");
		user.setPassword("123456");

		repo.save(user);
	}
	@Test
	public void findById(){
		int id = 1;
		Optional<User> user = repo.findById(id);
		System.out.println(user);
	}
	@Test
	public void testUpdate(){
		int id = 1;
		Optional<User> user = repo.findById(id);
		if(user.isPresent()){
			User userToModify = user.get();
			userToModify.setLastName("klara");
			System.out.println(user);
		} else {
			System.out.println("nie ma takiego uzytkownika");
		}
	}
	@Test
	public void testDisplay(){
		System.out.println(serviceComment);
	}

}
