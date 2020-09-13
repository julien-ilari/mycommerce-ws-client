package fr.mycommerce.rest;

import lombok.Data;

@Data
public class ClassA {
	
	private String test = "string à mélyssa A";
	private String testStringNull = "test";
	private Integer testIntegerNull;
	
	

	
	public static ClassA newInstance() {
		return new ClassA();
	}

}
