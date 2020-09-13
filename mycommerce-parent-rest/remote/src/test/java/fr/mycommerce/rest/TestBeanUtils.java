package fr.mycommerce.rest;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;

import fr.mycommerce.tools.BeanTools;

public class TestBeanUtils {
	
	@Test
	public void testBeanUtils() throws IllegalAccessException, InvocationTargetException
	{
		ClassEntity orig = ClassEntity.newInstance();
		ClassDto dest = ClassDto.newInstance();
	
		BeanTools.copyProperties(dest, orig);
		BeanTools.copyProperties(dest.getData(), orig.getData());
		
			
		Assert.assertEquals("erreur copie", dest.getData().getTest(), orig.getData().getTest());
		Assert.assertEquals("erreur copie", dest.getData().getTestIntegerNull(), orig.getData().getTestIntegerNull());
		Assert.assertEquals("erreur copie", dest.getData().getTestStringNull(), orig.getData().getTestStringNull());
	}

}
