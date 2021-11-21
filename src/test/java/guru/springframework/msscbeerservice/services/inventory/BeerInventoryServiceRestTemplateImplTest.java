package guru.springframework.msscbeerservice.services.inventory;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {
	
	@Autowired BeerInventoryService beerInventoryService;

	@BeforeEach
	void setUp() {}

	@Test
	void getOnhandInventory() {
		Integer qoh = beerInventoryService.getOnhandInventory(UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb"));
		System.out.println(qoh);
	}

}
