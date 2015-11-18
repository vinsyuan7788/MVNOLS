package cn.itcast.temporary.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 	This is a global generic JavaBean for back-end paging query processing
 * @author Administrator
 * @param <T>
 */
public class PageBean<T> {

	private int currentPageCode;			// current page code
	private int pageSize;					// page size
	private int totalRecords;				// total record number
	private int totalPages;					// total page number
	private int startingQueryIndex;			// the starting index for paging query
	private List<T> beanListForCurrentPage;	// the list to store the query results to display in current page
	private int maximumDisplayPages;		// the maximum number of display pages
	private int offSetPosition;				// the offset position relative to leftmost page code
	private List<Integer> displayPageCodes;	// the list to store all display page codes
	
	public int getCurrentPageCode() {
		return currentPageCode;
	}
	public void setCurrentPageCode(int currentPageCode) {
		this.currentPageCode = currentPageCode;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	//------------------------------------------------------------------------------------
	/*	For totalPages: modify the getter as below & remove the setter	*/
	public int getTotalPages() {
		int totalPages = totalRecords / pageSize;
		return totalRecords%pageSize==0?totalPages:(totalPages+1);
	}
	/*	For startingQueryIndex: modify the getter as below & remove the setter  */
	public int getStartingQueryIndex() {
		return (currentPageCode-1)*pageSize;
	}
	/*	For MaximumDisplayPages: only provides the setter	*/
	public void setMaximumDisplayPages(int maximumDisplayPages) {
		this.maximumDisplayPages = maximumDisplayPages;
	}
	/*	For OffSetPosition: only provides the setter	*/
	public void setOffSetPosition(int offSetPosition) {
		this.offSetPosition = offSetPosition;
	}
	/*	For DisplayPageCodes: modify the getter as below & remove the setter  */
	public List<Integer> getDisplayPageCodes () {
		
		/*	New a List<Integer> object to store the display page code	*/
		displayPageCodes = new ArrayList<Integer>();
		
		/*	Process the beginPageCode & endPageCode	 */
		int beginPageCode = currentPageCode - offSetPosition;
		if (beginPageCode <= 0) {
			beginPageCode = 1;
		}
		int endPageCode = currentPageCode + (maximumDisplayPages - offSetPosition);
		if (endPageCode > this.getTotalPages()) {
			endPageCode = this.getTotalPages();
		}
		
		/*	Store the display page code & return the List<Integer>	*/
		for (int pageCode = beginPageCode; pageCode <= endPageCode; pageCode++) {
			displayPageCodes.add(pageCode);
		}
		return displayPageCodes;
	}
	//------------------------------------------------------------------------------------
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<T> getBeanListForCurrentPage() {
		return beanListForCurrentPage;
	}
	public void setBeanListForCurrentPage(List<T> beanListForCurrentPage) {
		this.beanListForCurrentPage = beanListForCurrentPage;
	}
	@Override
	public String toString() {
		return "PageBean [currentPageCode=" + currentPageCode + ", pageSize="
				+ pageSize + ", totalRecords=" + totalRecords + ", totalPages="
				+ totalPages + ", startingQueryIndex=" + startingQueryIndex
				+ ", beanListForCurrentPage=" + beanListForCurrentPage
				+ ", maximumDisplayPages=" + maximumDisplayPages
				+ ", offSetPosition=" + offSetPosition + ", displayPageCodes="
				+ displayPageCodes + "]";
	}
}
