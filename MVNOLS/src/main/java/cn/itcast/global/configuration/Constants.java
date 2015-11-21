package cn.itcast.global.configuration;

/**
 * 	This is a class to store the system & business constants
 */
public interface Constants {

	/*	Captcha parameters	*/
	public static final int CAPTCHA_WIDTH = 150;
	public static final int CAPTCHA_HEIGHT = 40;
	public static final int CAPTCHA_CODE_COUNT = 4;
	public static final int CAPTCHA_LINE_COUNT = 80;	
	
	/*	Upload file server 	*/
	public static final String UPLOAD_FILE_URL = "http://localhost:8081";
	public static final String UPLOAD_IMAGE_URL = "http://localhost:8082";
	
	/*	Item pagination parameters	 */
	public static final int ITEM_PAGE_SIZE = 4;
	public static final int ITEM_MAXIMUM_DISPLAY_PAGES = 7;
	public static final int ITEM_OFFSET_POSITION = 4;
	
	public static final int ADMIN_ITEM_PAGE_SIZE = 10;
	public static final int ADMIN_ITEM_MAXIMUM_DISPLAY_PAGES = 7;
	public static final int ADMIN_ITEM_OFFSET_POSITION = 2;
	
	/*	User pagination	parameters	 */
	public static final int USER_PAGE_SIZE = 5;
	public static final int USER_MAXIMUM_DISPLAY_PAGES = 5;
	public static final int USER_OFFSET_POSITION = 3;
	
	/*	Public URL	*/
	public static final String PUBLIC_URL = "/redirection/success.action, /redirection/login.action, /redirection/itemCriteria.action, /redirection/itemList.action, " +
			"/captchaAjax/captchaValidation.action, " +
			"/user/userLogin.action, /user/userLogout.action, /user/userRegister.action, /user/userActivate.action, " +
			"/userAjax/duplicationValidation.action, /userAjax/existenceValidation.action, /userAjax/updateValidation.action, " +
			"/nationalityAjax/queryNationality.action, " +
			"/itemPagination/queryItemListByCriteria.action";
	
	/*	Token URL	*/
	public static final String TOKEN_URL = "/redirection/userAccount.action, /redirection/userAdminList.action, " +
			"/user/updateUser.action, /user/deleteUsers.action, /user/recoverUsers.action";
}
