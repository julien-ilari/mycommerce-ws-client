package fr.mycommerce.rest;

import java.beans.Beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClassEntity extends Beans {

	private ClassB data;
	

	public ClassEntity(ClassB data) {
		super();
		this.data = data;
	}

	public static ClassEntity newInstance() {
		return new ClassEntity(ClassB.newInstance());
	}


}
