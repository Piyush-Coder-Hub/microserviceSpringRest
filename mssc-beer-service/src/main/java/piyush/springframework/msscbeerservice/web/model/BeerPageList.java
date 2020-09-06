package piyush.springframework.msscbeerservice.web.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class BeerPageList extends PageImpl<BeerDto> implements Serializable {

	private static final long serialVersionUID = -1155421422300042842L;

	public BeerPageList(List<BeerDto> content) {
		super(content);
	}

	public BeerPageList(List<BeerDto> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

}
