package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {

	private static final long serialVersionUID = 1L;

	public NewInventoryEvent(BeerDto beerDto) {
		super(beerDto);
	}

}