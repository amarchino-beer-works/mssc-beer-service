package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

	private static final long serialVersionUID = 1L;

	public BrewBeerEvent(BeerDto beerDto) {
		super(beerDto);
	}

}
