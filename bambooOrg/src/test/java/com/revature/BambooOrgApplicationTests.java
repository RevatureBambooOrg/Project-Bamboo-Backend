package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.revature.dao.UsersDao;
import com.revature.model.Users;
import com.revature.service.UserService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = Users.class, loader = AnnotationConfigContextLoader.class)
class BambooOrgApplicationTests {


	//	@Autowired
	//	private UsersDao mockDao;


	@InjectMocks
	@Autowired
	private UsersDao mockDao;

	// @Mock
	//	private UserRepository useRep;
	@Autowired
	// @InjectMocks
	// @Resource
	private UserService uServ;

	@BeforeEach
	void initMock() {
		// uServ = new UserService();
		// mockDao = new UsersDao(); // Uncommenting this skips the mocking
		mockDao = Mockito.mock(UsersDao.class);
		// uServ = Mockito.mock(UserService.class);
		uServ = new UserService(mockDao);
		// Mockito.mock(UsersDao.class);
		// MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	public void init() {
		MockitoAnnotations.openMocks(this);
		// MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getUserByIdSuccess() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");
		bob.setId(1);
		System.out.println(bob);
		System.out.println(mockDao);
		Mockito.when(mockDao.selectById(1)).thenReturn(bob);
		// Mockito.when(mockDao.findAll()).thenReturn(Collections.singletonList(user));
		System.out.println("Succesfully made mockito");
		Users recieved = uServ.getUserById(1);
		assertEquals(bob, recieved);
	}

	@Test
	public void getUserByUsernameSuccess() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");
		bob.setId(1);
		System.out.println(bob);
		System.out.println(mockDao);
		Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.findAll()).thenReturn(Collections.singletonList(user));
		System.out.println("Succesfully made mockito");
		Users recieved = uServ.getUserByUsername("TestUsername");
		assertEquals(bob, recieved);
	}

	@Test
	public void getUserByEmailSuccess() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");
		bob.setId(1);
		System.out.println(bob);
		System.out.println(mockDao);
		Mockito.when(mockDao.selectByEmail("SuperKool@email.com")).thenReturn(bob);
		System.out.println("Succesfully made mockito");
		Users recieved = uServ.getUserByEmail("SuperKool@email.com");
		assertEquals(bob, recieved);
	}

	@Test
	public void makeSureLoggedOutReturnsFalse() {
		assertTrue(uServ.logOut());
	}

	@Test
	public void deleteShouldReturnId() {
		int expected = 5;
		Mockito.when(mockDao.removeById(expected)).thenReturn(expected);
		int returned = uServ.deleteUserById(expected);
		assertEquals(expected, returned);
	}


	@Test
	public void testRegisterExists() {

		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(null);
		Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertNotNull(username);
	}

	@Test
	public void testRegisterReturnsCorrectUserName() {

		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(null);
		Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals(bob.getUsername(), username);
	}

	@Test
	public void testRegisterIfNameAlreadyExists() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfIdReturnedIsBad() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(null);
		Mockito.when(mockDao.insert(bob)).thenReturn(0);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfUsernameIsNull() {
		Users bob = new Users();
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		// Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfPwdIsNull() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		// Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfEmailIsNull() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		// Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfFNameIsNull() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setLName("Last");

		Users testReturn = new Users();
		// Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfLNameIsNull() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");

		Users testReturn = new Users();
		// Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfUsernameIsEmpty() {
		Users bob = new Users();
		bob.setUsername("");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		// Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfPwdIsBlank() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		// Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfEmailIsBlank() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		// Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfEmailMissingAtSymbol() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKoolemail.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		// Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfEmailMissingPeriod() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@emailcom");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfFNameIsBlank() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("");
		bob.setLName("Last");

		Users testReturn = new Users();
		// Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testRegisterIfLNameIsBlank() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("");

		Users testReturn = new Users();
		// Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.register(bob);
		assertEquals("", username);
	}

	@Test
	public void testLoginSuccessful() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		testReturn.setUsername("TestUsername");
		testReturn.setPwd("ThisisSecurePassword");
		Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.login(testReturn);
		assertEquals(bob.getUsername(), username);
	}

	@Test
	public void testLoginSuccessfulWithEmail() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		testReturn.setUsername("SuperKool@email.com");
		testReturn.setPwd("ThisisSecurePassword");

		//Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(null);
		Mockito.when(mockDao.selectByEmail("SuperKool@email.com")).thenReturn(bob);
		String username = uServ.login(testReturn);
		assertEquals(bob.getUsername(), username);
	}

	@Test
	public void testLoginBadPassword() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		testReturn.setUsername("TestUsername");
		testReturn.setPwd("NotTheRightPassword");
		Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(bob);
		// Mockito.when(mockDao.insert(bob)).thenReturn(2);
		String username = uServ.login(testReturn);
		assertEquals("", username);
	}

	@Test
	public void testLoginSuccessfulWithEmailAndBadPassword() {
		Users bob = new Users();
		bob.setUsername("TestUsername");
		bob.setPwd("ThisisSecurePassword");
		bob.setEmail("SuperKool@email.com");
		bob.setFName("First");
		bob.setLName("Last");

		Users testReturn = new Users();
		testReturn.setUsername("SuperKool@email.com");
		testReturn.setPwd("NotTheRightPassword");

		//Mockito.when(mockDao.selectByUsername("TestUsername")).thenReturn(null);
		Mockito.when(mockDao.selectByEmail("SuperKool@email.com")).thenReturn(bob);
		String username = uServ.login(testReturn);
		assertEquals("", username);
	}


}
