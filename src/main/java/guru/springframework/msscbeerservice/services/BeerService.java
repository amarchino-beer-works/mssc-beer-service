package guru.springframework.msscbeerservice.services;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPagedList;
import guru.sfg.brewery.model.BeerStyleEnum;

/**
 * Created by jt on 2019-06-06.
 */
public interface BeerService {
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, boolean showInventoryOnHand);

    BeerDto getById(UUID beerId, boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerDto getByUpc(String upc);
}
