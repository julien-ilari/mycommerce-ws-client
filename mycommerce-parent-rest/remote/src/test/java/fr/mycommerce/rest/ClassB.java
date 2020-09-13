package fr.mycommerce.rest;

import lombok.Data;

@Data
public class ClassB {
	
	private String test = "string à mélyssa B";
	private String testStringNull;
	private Integer testIntegerNull;

	public static ClassB newInstance() {
		return new ClassB();
	}

}
