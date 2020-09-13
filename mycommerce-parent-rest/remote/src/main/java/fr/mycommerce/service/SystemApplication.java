package fr.mycommerce.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

//Activation authentication JWT (MicroProfil)
//@LoginConfig(authMethod = "MP-JWT", realmName = "myrealm")
//@DeclareRoles({ "ADMIN", "USER" })
@ApplicationPath("api")
@OpenAPIDefinition(info = @Info(title = "web-maker.fr api", version = "1.0"))
public class SystemApplication extends Application
{


}