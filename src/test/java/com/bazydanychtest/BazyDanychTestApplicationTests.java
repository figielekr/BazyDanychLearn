package com.bazydanychtest;

import com.bazydanychtest.security.UserInfoUserDetails;
import com.bazydanychtest.user.tables.*;
import org.hibernate.Length;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.ui.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //nie bedzie tworzyc testowej bazy danych w pamieci
@Rollback(value = false)
class BazyDanychTestApplicationTests {
	@Autowired private UserRepository repo;
	@Autowired private CommentRepository repoComment;
	@Autowired private CommentService serviceComment;
	@Autowired private ArticleRepository articleRepository;
	@Autowired private ArticleLikesRepository articleLikesRepository;

	@Test
	public void addNewUSer() {
		User user = new User();
		user.setEmail("uzytkownik@gmail.com");
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
			System.out.println(user);
		} else {
			System.out.println("nie ma takiego uzytkownika");
		}
	}
	@Test
	public void testDisplay(){
		System.out.println(serviceComment);
	}
	@Test
	public void timeCurrent(){
		Date date = new Date();
		String strDateFormat = "HH:mm:ss-dd:MM:yyyy";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);
	}
	@Test
	public void findUser(){
		int id = 1;
		Optional<User> user = repo.findById(id);
		//String path = repo.findById(id).get().getPath();
		String path = repo.findByUserName("noyss").get().getPath();

		System.out.println(path);
	}
	@Test
	public void testObject(){
		Optional<User> user = repo.findById(1);
		Object object = (user.get().getPath());
		System.out.println(object);

	}
	@Test
	public void test(){
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		numbers.stream()
				.map(n -> n * 2)
				.forEach(System.out::println);
	}
	@Test
	public void test2(){
		List<String> words = Arrays.asList("Java", "Python", "JavaScript", "Ruby", "C++");
		words.stream()
				.map(String::length)
				.filter(length -> length > 2)
				.forEach(System.out::println);
	}

}
