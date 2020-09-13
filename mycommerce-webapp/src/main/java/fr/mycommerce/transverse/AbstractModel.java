package fr.mycommerce.transverse;

import java.io.Serializable;

public abstract class AbstractModel<T> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private T id;

	/**
	 * @return the id
	 */
	public T getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(T id)
	{
		this.id = id;
	}
	
	

}
