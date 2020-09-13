package fr.mycommerce.view;

import java.io.Serializable;
import java.util.List;

import fr.mycommerce.service.ID;

public interface Manager <M extends ID<I>, I extends Serializable> extends Serializable {
	
//	ICrudResource<M, I> getService();
	
	M getModel();
	
	List<M> callServiceGet();
	
	void callServicePost();
	
	void callServicePut();
	
	void callServiceDelete(I id);

}
