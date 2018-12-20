package br.com.devdojo.awesome.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.devdojo.awesome.util.CustomSortDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageableResponse<T> extends PageImpl<T> {
	private boolean last;
	private boolean first;
	private int totalPages;

	private static final long serialVersionUID = 1L;
	private int number;
	private int size;
	private int numberOfElements;
	private long totalElements;
	private boolean previousPage;
	private boolean firstPage;
	private boolean nextPage;
	private boolean lastPage;
	private List<T> content;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public boolean isPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(boolean previousPage) {
		this.previousPage = previousPage;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Sort sort;

	public PageableResponse(@JsonProperty("content") List<T> content, @JsonProperty("number") int page,
			@JsonProperty("size") int size, @JsonProperty("totalElements") long totalElements,
			@JsonProperty("sort") @JsonDeserialize(using = CustomSortDeserializer.class) Sort sort) {
		super(content, new PageRequest(page, size, sort), totalElements);
	}

	@JsonCreator
	public PageableResponse() {
		super(new ArrayList<>());
	}

	@Override
	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	@Override
	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	@Override
	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}