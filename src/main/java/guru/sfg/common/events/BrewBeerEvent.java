package guru.sfg.common.events;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

	private static final long serialVersionUID = 1L;

	public BrewBeerEvent(BeerDto beerDto) {
		super(beerDto);
	}

}
