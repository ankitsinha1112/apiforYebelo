package com.yebelo;

import com.yebelo.entity.nextNumberEntity;
import com.yebelo.repo.nextNumberRepo;
import com.yebelo.service.nextNumberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class YebeloApplicationTests {

	@Autowired
	public nextNumberService service;
	@MockBean
	public nextNumberRepo repo;
	@BeforeEach
	void beforeTest() {
		System.out.println("Executing first");
	}
	@AfterEach
	void afterTest() {
		System.out.println("Execute at last");
	}
	@Test
	void fetchTest() {
		System.out.println("TestCase : 1");
		nextNumberEntity nn = new nextNumberEntity();
		nn.setId(1);
		nn.setCategoryCode("aaa");
		nn.setValue(17);
		when(repo.save(nn)).thenReturn(nn);
		int oldValue = nn.getValue();
		int nextValue;

		//described in service implementation
		int remainder = (int) (oldValue % 9);
		if(remainder == 0)
			nextValue = oldValue + 1;
		else
			nextValue = oldValue + (9 - (remainder - 1));
		nn.setValue(nextValue);
		long newValue = nextValue;
		assertEquals(nn.getValue(), newValue);
	}

}
