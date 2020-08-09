package piyush.springframework.msscbeerservice.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;

public class BeerPageList extends PageImpl<BeerDto> {

	public BeerPageList(List<BeerDto> content) {
		super(content);
	}

}
