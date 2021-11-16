package guru.sfg.brewery.model.events;

import guru.sfg.brewery.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * Created by jt on 2019-07-21.
 */
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

	private static final long serialVersionUID = 1L;

	public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
