package guru.springframework.msscbeerservice.services.brewing;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.events.BrewBeerEvent;
import guru.sfg.brewery.model.events.NewInventoryEvent;
import guru.sfg.brewery.util.JmsQueues;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {
	private final BeerRepository beerRepository;
	private final JmsTemplate jmsTemplate;

	@Transactional
	@JmsListener(destination = JmsQueues.BREWING_REQUEST_QUEUE)
	public void listen(BrewBeerEvent event) {
		BeerDto beerDto = event.getBeerDto();
		Beer beer = beerRepository.getById(beerDto.getId());
		beerDto.setQuantityOnHand(beer.getQuantityToBrew());
		
		NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);
		log.debug("Brewed beer " + beer.getMinOnHand() + " : Quantity on hand: " + beerDto.getQuantityOnHand());
		jmsTemplate.convertAndSend(JmsQueues.NEW_INVENTORY_QUEUE, newInventoryEvent);
	}
}
