package com.codi.base.dao;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PageImpl<T> extends org.springframework.data.domain.PageImpl<T> {
	private static final long serialVersionUID = 1L;
	
	private final long total;
	private final Pageable pageable;
	
	public PageImpl(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
		this.total = total;
		this.pageable = pageable;
	}

	public PageImpl(List<T> content) {
		this(content, null, null == content ? 0 : content.size());
	}
	
	@Override
	public int getTotalPages() {
		return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
	}

	@Override
	public long getTotalElements() {
		return total;
	}

	@Override
	public boolean hasNext() {
		return getNumber() + 1 < getTotalPages();
	}

	@Override
	public boolean isLast() {
		return !hasNext();
	}

	@Override
	public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
		return new PageImpl<S>(getConvertedContent(converter), pageable, total);
	}

	@Override
	public String toString() {

		String contentType = "UNKNOWN";
		List<T> content = getContent();

		if (content.size() > 0) {
			contentType = content.get(0).getClass().getName();
		}

		return String.format("Page %s of %d containing %s instances", getNumber() + 1, getTotalPages(), contentType);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageImpl<?>)) {
			return false;
		}

		PageImpl<?> that = (PageImpl<?>) obj;

		return this.total == that.total && super.equals(obj);
	}

	@Override
	public int hashCode() {

		int result = 17;

		result += 31 * (int) (total ^ total >>> 32);
		result += 31 * super.hashCode();

		return result;
	}

}
