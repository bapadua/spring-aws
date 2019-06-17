package br.com.bapadua.aws.service.util;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class HashUtilTests {
	
	@Test
	public void getSecuredHash() {
		String hash = HashUtil.getSecureHash("123");
		assertTrue(hash.length() == 64);
	}
}
