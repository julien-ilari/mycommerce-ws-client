package fr.mycommerce.service;

import java.io.Serializable;

public interface ID<I extends Serializable> extends Serializable {

	public I getId();

	public void setId(I id);

}