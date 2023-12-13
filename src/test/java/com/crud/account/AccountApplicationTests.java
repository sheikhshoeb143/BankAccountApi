package com.crud.account;

import com.crud.account.model.User;
import com.crud.account.repo.UserRepository;
import com.crud.account.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class AccountApplicationTests {




	@Test
	void contextLoads() {
	}

}
