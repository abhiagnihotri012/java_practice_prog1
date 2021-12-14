package com.neosoft;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(LifeCycleCallBack.class);

		System.out.println("Total Failures: " + result.getFailureCount());

		for (Failure failure : result.getFailures())
			System.out.println(failure.getTrimmedTrace());

		System.out.println("Run Count: " + result.getRunCount());
		System.out.println("Result: " + result.wasSuccessful());
	}

}