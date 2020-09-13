package fr.mycommerce.view;

import java.io.Serializable;
import java.util.List;

import fr.mycommerce.service.ICrudResource;
import fr.mycommerce.service.ID;

public interface ManagerCrud <M extends ID<I>, I extends Serializable> extends Manager<M, I> {
	
	ICrudResource<M, I> getService();
	
	M getModel();
	
	default List<M> callServiceGet()
	{
		return getService().get();
	}
	
	default void callServicePost()
	{
		getService().post(getModel());
	}
	
	
	default void callServicePut()
	{
		getService().put(getModel());
	}
	
	default void callServiceDelete(I id)
	{
		getService().delete(id);
	}

}
