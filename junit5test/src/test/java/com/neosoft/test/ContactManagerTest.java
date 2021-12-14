package com.neosoft.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import com.neosoft.ContactManager;

public class ContactManagerTest {

	private ContactManager contactManager;
	
	@BeforeAll
	public static void setupAll() {
		System.out.println("Should print before all tests...");
	}
	
	@BeforeEach
	public void setup() {
		System.out.println("Instantiating Contact Manager");
		contactManager = new ContactManager();
	}
	
	@Test
	@DisplayName("Should Create Contact")
	public void shouldCreateContact() {
		contactManager.addContact("John", "Cena", "9898989898");
		assertFalse(contactManager.getAllContacts().isEmpty());
		assertEquals(1,contactManager.getAllContacts().size());
		System.out.println("Contact added");
	}
	
	@Test
	@DisplayName("Should not create a Contact if first name is null")
	public void shouldThrowRuntimeExceptionIfFirstNameNull() {
		assertThrows(RuntimeException.class,() -> {
			contactManager.addContact(null, "Xyz", "9999999999");
		});
	}
	
	@Test
	@DisplayName("Should not create a Contact if last name is null")
	public void shouldThrowRuntimeExceptionIfLastNameNull() {
		assertThrows(RuntimeException.class,() -> {
			contactManager.addContact("Abc", null, "9999999999");
		});
	}
	
	@Test
	@DisplayName("Should not create a Contact if mobile no. is null")
	public void shouldThrowRuntimeExceptionIfMobileNoIsNull() {
		assertThrows(RuntimeException.class,() -> {
			contactManager.addContact("Abc", "Xyz", "9");
		});
	}
	
	@Test
	@DisplayName("Should not create a Contact if phone is non digit")
	public void shouldThrowRuntimeExceptionIfPhoneNonDigit() {
		assertThrows(RuntimeException.class,() -> {
			contactManager.addContact("Abc", "Xyz", "99ab999999");
		});
	}
	
	
	
	@Test
	@DisplayName("Should not create a Contact if phone is not 10 digit")
	public void shouldThrowRuntimeExceptionIfPhoneNon10Digit() {
		assertThrows(RuntimeException.class,() -> {
			contactManager.addContact("Abc", "Xyz", "999999999999");
		});
	}
	
	@Test
	@DisplayName("Test should be ignored/disabled")
	@Disabled
	public void shouldBeDisabled() {
		throw new RuntimeException("Test should Not be executed..");
	}
	
		
	@AfterEach
	public void afterEach() {
		System.out.println("Should execute after each test");
		contactManager = null;
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("Should execute at the end of all");
	}
}
