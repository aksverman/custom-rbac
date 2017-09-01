package com.rudra.aks.util;

import org.apache.log4j.Logger;


//@Service(value = "customAuthorizationUtil")
public class AuthorizeUtilImpl implements AuthorizeUtil {

	private static Logger logger = Logger.getLogger(AuthorizeUtilImpl.class);
	
	public AuthorizeUtilImpl() {	
		logger.info("AuthorizationUtil instantiated");
	}
	
	public boolean isAuthorize(Object principal, int userid) {
		logger.info("Start : " + getClass().getName() + " : isAuthorize()");
		
		return (principal != null && userid != 0);
	}
}
/*@Component(value = "permissionEvaluator")
public class AuthorizeUtilImpl implements AuthorizeUtil {

	@Autowired
	UserSession session;
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	public AuthorizeUtilImpl() {	
		logger.info("AuthorizationUtil instantiated");
	}
	
	
	public boolean isAuthorize(CurrentUser currentUser, int userid) {
		if( obj instanceof UserSession)
			return true;
		logger.info(session);
		return userid > 0;
	}

}
*/