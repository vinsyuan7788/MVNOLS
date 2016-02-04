package cn.itcast.temporary.bean.pagination;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.temporary.bean.query.bean.QueryBean;

/**
 * 	This is a global generic JavaBean for back-end paging query processing
 * 	1. Can provide the getters and setters for all fields for simplicity
 *  2. Then modify the getter of totalPages, startingQueryIndex, displayPageCodes
 *     -- See getTotalPages(), getStartingQueryIndex(), getDisplayPageCodes()
 * @author Vince Xu Yuan
 * @param <T>
 */
public class PageBean<T> extends QueryBean {

	private Integer currentPageCode;			// current page code (default is 1)
	private Integer pageSize;					// page size
	private Integer totalRecords;				// total record number
	private Integer totalPages;					// total page number
	private Integer startingQueryIndex;			// the starting index for paging query
	private List<T> beanListForCurrentPage;		// the list to store the query results to display in current page
	private Integer maximumDisplayPages;		// the maximum number of display pages
	private Integer offSetPosition;				// the offset position relative to leftmost page code
	private List<Integer> displayPageCodes;		// the list to store all display page codes
	
	public Integer getCurrentPageCode() {
		return currentPageCode;
	}
	public void setCurrentPageCode(Integer currentPageCode) {
		this.currentPageCode = currentPageCode;
	}
	//------------------------------------------------------------------------------------
	/*	These 4 getters are only for testing	*/
	public Integer getPageSize() {
		return pageSize;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public Integer getMaximumDisplayPages() {
		return maximumDisplayPages;
	}
	public Integer getOffSetPosition() {
		return offSetPosition;
	}
	//------------------------------------------------------------------------------------
	/*	For PageSize: only provide the setter	*/
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/*	For totalPages: modify the getter as below & remove the setter	*/
	public Integer getTotalPages() {
		Integer totalPages = totalRecords / pageSize;
		return totalRecords%pageSize==0?totalPages:(totalPages+1);
	}
	/*	For TotalRecords: only provide the setter	*/
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	/*	For startingQueryIndex: modify the getter as below & remove the setter  */
	public Integer getStartingQueryIndex() {
		return (currentPageCode-1)*pageSize;
	}
	/*	For MaximumDisplayPages: only provides the setter	*/
	public void setMaximumDisplayPages(Integer maximumDisplayPages) {
		this.maximumDisplayPages = maximumDisplayPages;
	}
	/*	For OffSetPosition: only provides the setter	*/
	public void setOffSetPosition(Integer offSetPosition) {
		this.offSetPosition = offSetPosition;
	}
	/*	For DisplayPageCodes: modify the getter as below & remove the setter  */
	public List<Integer> getDisplayPageCodes () {
		
		/*	New a List<Integer> object to store the display page code	*/
		displayPageCodes = new ArrayList<Integer>();
		
		/*	Process the startPageCode & endPageCode	 */
		Integer startPageCode = Math.min(currentPageCode - offSetPosition + 1, this.getTotalPages() - maximumDisplayPages + 1);
		if (startPageCode <= 0) {
			startPageCode = 1;
		}
		Integer endPageCode = Math.max(maximumDisplayPages, currentPageCode + (maximumDisplayPages - offSetPosition));
		if (endPageCode > this.getTotalPages()) {
			endPageCode = this.getTotalPages();
		}
		
		/*	Store the display page code & return the List<Integer>	*/
		for (Integer pageCode = startPageCode; pageCode <= endPageCode; pageCode++) {
			displayPageCodes.add(pageCode);
		}
		return displayPageCodes;
	}
	//------------------------------------------------------------------------------------
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
