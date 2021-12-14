package com.neosoft;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class LifeCycleCallBack {
   
	// execute before class
		@BeforeClass
		public static void beforeClass() {
			System.out.println("In before class");
		}

		// execute after class
		@AfterClass
		public static void afterClass() {
			System.out.println("In after class");
		}

		// execute before test
		@Before
		public void before() {
			System.out.println("In before");
		}

		// Test case
		@Test
		public void test() {
			System.out.println("In test");
		}

		// execute after the test
		@After
		public void after() {
			System.out.println("In after");
		}

		// test case ignore and will not execute
		@Ignore
		public void ignoreTest() {
			System.out.println("In ignore test");
		}
}