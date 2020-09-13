package fr.mycommerce.rest;

import java.beans.Beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClassDto extends Beans {

	private ClassA data;

	public ClassDto(ClassA data) {
		super();
		this.data = data;
	}

	public static ClassDto newInstance() {
		return new ClassDto(ClassA.newInstance());
	}

}
