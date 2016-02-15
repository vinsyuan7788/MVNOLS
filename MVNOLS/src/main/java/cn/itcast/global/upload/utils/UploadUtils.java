package cn.itcast.global.upload.utils;

/**
 * 	This is an upload utility interface that can be implemented by necessary JavaBeans to get upload file FULL URL
 * 	1. In front-end the full URL can be obtained by, for example, using EL like "${xxx.fullURL}" in JSP view for display
 * 	2. This method is necessary since if save the full upload URL into database, once the location of upload files are changed
 *     e.g. move to another server, then all uploaded files needs to be uploaded again to get a new full upload URL
 *     -- If using this way, then all it needs to do is:
 *     	  1. Make sure relevant beans implements this interface & override the method (which is very strait-forward)
 *           -- E.g. refer to "/MVNOLS/src/main/java/cn/itcast/item/bean/Item.java"
 *        2. Change the relevant configuration|business constant 
 * @author Vince Xu Yuan        
 */
public interface UploadUtils {

	public String getFullUploadURL();
}
