package guru.springframework.msscbeerservice.services.inventory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.springframework.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
@RequiredArgsConstructor
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {
	private static final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
	private final RestTemplate restTemplate;
	@Setter
	private String beerInventoryServiceHost;

	@Override
	public Integer getOnhandInventory(UUID beerId) {
		log.debug("Calling Inventory Service");
		
		ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate.exchange(
				beerInventoryServiceHost + INVENTORY_PATH,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<BeerInventoryDto>>() {},
				beerId);
		return Objects.requireNonNull(responseEntity.getBody())
			.stream()
			.mapToInt(BeerInventoryDto::getQuantityOnHand)
			.sum();
	}

}
