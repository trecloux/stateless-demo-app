package fr.letitzen.demo.web;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.letitzen.demo.web.Pager;

public class PagerTest {
	
	@Test
	// Page : 1, RowCount : 57, PageSize : 20, PageControlsSize : 10
	public void firstPageLowPageNumber() {
		Pager pager = new Pager(1, 57, 20, 10);
		assertThat(pager.isPagingNeeded()).isTrue();
		assertThat(pager.getCurrentPageIndex()).isEqualTo(1);
		assertThat(pager.getFirstRow()).isEqualTo(0);
		
		assertThat(pager.getFirstPageIndex()).isEqualTo(1);
		assertThat(pager.isFirstPage()).isTrue();
		
		assertThat(pager.getLastPageIndex()).isEqualTo(3);
		assertThat(pager.isLastPage()).isFalse();
		
		assertThat(pager.getPreviousPageIndex()).isEqualTo(1);
		assertThat(pager.getNextPageIndex()).isEqualTo(2);
		
		assertThat(pager.getFirstPageControl()).isEqualTo(1);
		assertThat(pager.getLastPageControl()).isEqualTo(3);
		
	}
	
	@Test
	// Page : 1, RowCount : 57, PageSize : 20, PageControlsSize : 10
	public void lastPageLowPageNumber() {
		Pager pager = new Pager(3, 57, 20, 10);
		assertThat(pager.isPagingNeeded()).isTrue();
		assertThat(pager.getCurrentPageIndex()).isEqualTo(3);
		assertThat(pager.getFirstRow()).isEqualTo(40);
		
		assertThat(pager.getFirstPageIndex()).isEqualTo(1);
		assertThat(pager.isFirstPage()).isFalse();
		
		assertThat(pager.getLastPageIndex()).isEqualTo(3);
		assertThat(pager.isLastPage()).isTrue();
		
		assertThat(pager.getPreviousPageIndex()).isEqualTo(2);
		assertThat(pager.getNextPageIndex()).isEqualTo(3);
		
		assertThat(pager.getFirstPageControl()).isEqualTo(1);
		assertThat(pager.getLastPageControl()).isEqualTo(3);
	}
	
	@Test
	// Page : 1, RowCount : 57, PageSize : 10, PageControlsSize : 10
	public void firstPageHighPageNumber() {
		Pager pager = new Pager(1, 155, 10, 10);
		assertThat(pager.isPagingNeeded()).isTrue();
		assertThat(pager.getCurrentPageIndex()).isEqualTo(1);
		assertThat(pager.getFirstRow()).isEqualTo(0);
		
		assertThat(pager.getFirstPageIndex()).isEqualTo(1);
		assertThat(pager.isFirstPage()).isTrue();
		
		assertThat(pager.getLastPageIndex()).isEqualTo(16);
		assertThat(pager.isLastPage()).isFalse();
		
		assertThat(pager.getPreviousPageIndex()).isEqualTo(1);
		assertThat(pager.getNextPageIndex()).isEqualTo(2);
		
		assertThat(pager.getFirstPageControl()).isEqualTo(1);
		assertThat(pager.getLastPageControl()).isEqualTo(10);		
	}	
	
	@Test
	// Page : 1, RowCount : 5, PageSize : 20, PageControlsSize : 10
	public void noPagingNeeded() {
		Pager pager = new Pager(1, 5, 10, 10);
		assertThat(pager.isPagingNeeded()).isFalse();		
	}	

}
