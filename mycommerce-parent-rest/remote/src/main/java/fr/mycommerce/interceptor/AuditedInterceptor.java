package fr.mycommerce.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.logging.log4j.LogManager;

@Interceptor
@Audited
public class AuditedInterceptor { 
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(AuditedInterceptor.class.getName());

    @AroundInvoke
    public Object audit(InvocationContext context) throws Exception {
      LOGGER.warn("##############Calling" + context.getMethod().getName());
      return context.proceed();
    }
}