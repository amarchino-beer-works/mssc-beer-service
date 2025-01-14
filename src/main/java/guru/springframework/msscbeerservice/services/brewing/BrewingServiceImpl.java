package guru.springframework.msscbeerservice.services.brewing;

import java.util.List;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import guru.sfg.brewery.model.events.BrewBeerEvent;
import guru.sfg.brewery.util.JmsQueues;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingServiceImpl implements BrewingService {
	private final BeerRepository beerRepository;
	private final BeerInventoryService beerInventoryService;
	private final JmsTemplate jmsTemplate;
	private final BeerMapper beerMapper;
	
	@Scheduled(fixedRate = 5000)
	public void checkForLowInventory() {
		List<Beer> beers = beerRepository.findAll();
		beers.forEach(beer -> {
			Integer inventoryQuantityOnHand = beerInventoryService.getOnhandInventory(beer.getId());
			log.debug("Min on hand is " + beer.getMinOnHand());
			log.debug("Inventory is " + inventoryQuantityOnHand);
			if(beer.getMinOnHand().compareTo(inventoryQuantityOnHand) >= 0) {
				jmsTemplate.convertAndSend(JmsQueues.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
			}
		});
	}
}
