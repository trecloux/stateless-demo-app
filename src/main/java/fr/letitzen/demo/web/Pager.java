package fr.letitzen.demo.web;

import java.util.ArrayList;
import java.util.List;



public class Pager {
	
	private static final int DEFAULT_PAGE_SIZE = 12;
	private static final int DEFAULT_PAGECONTROLS_SIZE = 8;
	
	private final int currentPage;
	private final int firstPage = 1;
	private final int pageSize;
	private final int pageControlsSize;
	
	private final int rowCount;

	public Pager(int currentPage, int rowCount) {
		this(currentPage, rowCount, DEFAULT_PAGE_SIZE, DEFAULT_PAGECONTROLS_SIZE);
	}
	
	public Pager(int currentPage, int rowCount, int pageSize, int pageControlsSize) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.pageControlsSize = pageControlsSize;
		this.rowCount = rowCount;
	}


	public int getNextPageIndex() {
		if (isLastPage()) {
			return currentPage;
		} else {
			return currentPage + 1;
		}
	}

	public int getPreviousPageIndex() {
		if (isFirstPage()) {
			return currentPage;
		} else {
			return currentPage - 1;
		}
	}

	public int getLastPageIndex() {
		int lastPage = rowCount / pageSize;
		if ((rowCount % pageSize) > 0 ) {
			lastPage ++;
		}
		return lastPage;
	}
	
	public boolean isFirstPage() {
		return currentPage == firstPage;
	}

	public boolean isLastPage() {
		return currentPage == getLastPageIndex();
	}
	
	
	public List<Integer> getControlPages() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i=getFirstPageControl(); i<= getLastPageControl(); i++) {
			result.add(i);
		}
		return result;
	}

	public int getFirstPageControl() {
		int halfPageControl = pageControlsSize / 2;
		int firstPageControl = currentPage - halfPageControl;
		if (firstPageControl < firstPage) {
			return firstPage;
		} else {
			return firstPageControl;
		}
	}

	public int getLastPageControl() {
		int lastPageControl = getFirstPageControl() + pageControlsSize - 1;
		if (lastPageControl > getLastPageIndex()) {
			return getLastPageIndex();
		} else {
			return lastPageControl;
		}
	}

	public int getCurrentPageIndex() {
		return currentPage;
	}
	public int getFirstPageIndex() {
		return firstPage;
	}
	
	
	public int getPageSize() {
		return pageSize;
	}

	public int getPageControlsSize() {
		return pageControlsSize;
	}

	public int getRowCount() {
		return rowCount;
	}

	public boolean isPagingNeeded() {
		return rowCount > pageSize;
		
	}

	public int getFirstRow() {
		return ((currentPage - firstPage) * pageSize);
	}
	
}
