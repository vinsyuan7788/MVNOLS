package cn.itcast.global.upload.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import cn.itcast.global.configuration.BusinessConstants;
import cn.itcast.global.utils.AjaxResponseUtils;

import com.sun.jersey.api.client.Client;

/**
 * 	This is the action class to process AJAX request for upload files
 */
@Controller
@RequestMapping("/uploadAjax")
public class UploadAjaxAction {
	
	/**
	 * 	This is an action method to process the upload files for users
	 * 	1. To make sure this method works: run the tomcat under the file "D:\apache-tomcat-7.0.42-2"
	 * @param uploadFile
	 * @param httpSession
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/uploadFile")
	public void uploadFile (@RequestParam(required = false) MultipartFile uploadFile, HttpSession httpSession, HttpServletResponse response) throws Exception {
		
		/*	If there is anything being uploaded, process the upload file	 */
		if (uploadFile.getSize() > 0) {
		
			/*	Get the extension name of the upload file	*/
			String extensionName = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
			
			/*	
			 * 	Upload file name generation strategy	
			 * 	1. Get the current time in the form of "yyyyMMddHHmmssSSS"
			 * 	2. Generate a 3-digit random numbers
			 */
			String dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			int randomFormat = (int)(Math.random()*899+100);
			
			/*	Get the upload file relative path & Request URL 	*/
			String relativePath = "/uploadFiles/Files/" + dateFormat + "_" +randomFormat + "." + extensionName;
			String requestURL = BusinessConstants.UPLOAD_FILE_URL + relativePath;
		
			/*	Send the upload file to another server (e.g. tomcat)	*/
			new Client().resource(requestURL).put(String.class, uploadFile.getBytes());	
			
			/*	Return relativePath & requestURL to front-end view (e.g. JSP view)	*/
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("relativePath", relativePath);
			jsonObject.put("requestURL", requestURL);
			AjaxResponseUtils.responseWithJson(response, jsonObject.toString());
		}
	}
	
	/**
	 * 	This is an action method to process the upload images for items
	 * 	1. To make sure this method works: run the tomcat under the file "D:\apache-tomcat-7.0.42-3"
	 * @param uploadImage
	 * @param httpSession
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/uploadImage")
	public void uploadImage (@RequestParam(required = false) MultipartFile uploadImage, HttpSession httpSession, HttpServletResponse response) throws Exception {
		
		/*	If there is anything being uploaded, process the upload file	 */
		if (uploadImage.getSize() > 0) {
		
			/*	Get the extension name of the upload file	*/
			String extensionName = FilenameUtils.getExtension(uploadImage.getOriginalFilename());
			
			/*	
			 * 	Upload file name generation strategy	
			 * 	1. Get the current time in the form of "yyyyMMddHHmmssSSS"
			 * 	2. Generate a 3-digit random numbers
			 */
			String dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			int randomFormat = (int)(Math.random()*899+100);
			
			/*	Get the upload file relative path & Request URL 	*/
			String relativePath = "/uploadFiles/Files/" + dateFormat + "_" +randomFormat + "." + extensionName;
			String requestURL = BusinessConstants.UPLOAD_IMAGE_URL + relativePath;
		
			/*	Send the upload file to another server (e.g. tomcat)	*/
			new Client().resource(requestURL).put(String.class, uploadImage.getBytes());	
			
			/*	Return relativePath & requestURL to front-end view (e.g. JSP view)	*/
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("relativePath", relativePath);
			jsonObject.put("requestURL", requestURL);
			AjaxResponseUtils.responseWithJson(response, jsonObject.toString());
		}
	}
	
	/**
	 * 	For fck upload:
	 * 	1. To accept the upload file:
	 *     -- Cast "HttpServletRequest request" to "MultipartHttpServletRequest multipartRequest" to accept the upload file
	 *  2.	To write back the upload file:
	 *     -- use "HttpServletResponse response.getWriter().print(UploadResponse.getOK(requestURL))" to return the upload file
	 */
}
