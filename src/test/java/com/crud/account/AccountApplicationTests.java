package com.crud.account;

import com.crud.account.model.User;
import com.crud.account.repo.UserRepository;
import com.crud.account.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
class AccountApplicationTests {


	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;


	@Test
	public void findAllTest(){
		when(repository.findAll()).thenReturn(Stream.of(new User(1, "Sheikh Shoeb", "KA", "SBI", "SBIN0007065", "Current", "20288091922", "6000"), new User(2, "Ashu", "CG", "ICICI", "ICIC07065", "Savings", "70288091922", "60000")).collect(Collectors.toList()));
		assertEquals(2, service.findAll().size());
	}

//	@Test
//	public void findByIdTest(){
//		int id = 1;
//		when(repository.findById(id)).thenReturn(new User(1, "Sheikh Shoeb", "KA", "SBI", "SBIN0007065", "Current", "20288091922", "6000").collect(Collectors.toList()));
//		assertEquals(1, service.findById(id).size());
//	}

	@Test
	public void saveTest(){
		User user = new User(1, "Sheikh Shoeb", "KA", "SBI", "SBIN0007065", "Current", "20288091922", "6000");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.save(user));
	}

	@Test
	public void deleteAllTest(){
		service.deleteAll();
		verify(repository,times(1)).deleteAll();
	}

	
//	@Test
//	void contextLoads() {
//	}

}
