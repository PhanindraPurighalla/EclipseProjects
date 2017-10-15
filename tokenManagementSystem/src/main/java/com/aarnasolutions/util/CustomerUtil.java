package com.aarnasolutions.util;

public class CustomerUtil {
	
	private CustomerUtil() {

    }

    public static String convertToFullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

}
