package guru.springframework.msscbeerservice.services.order;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import guru.sfg.brewery.model.events.ValidateOrderRequest;
import guru.sfg.brewery.model.events.ValidateOrderResult;
import guru.sfg.brewery.util.JmsQueues;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {
	
	private final BeerOrderValidator beerOrderValidator;
	private final JmsTemplate jmsTemplate;

	@JmsListener(destination = JmsQueues.VALIDATE_ORDER_QUEUE)
	public void listen(ValidateOrderRequest validateOrderRequest) {
		Boolean valid = beerOrderValidator.validateOrder(validateOrderRequest.getBeerOrderDto());
		jmsTemplate.convertAndSend(JmsQueues.VALIDATE_ORDER_RESPONSE_QUEUE,
			ValidateOrderResult.builder()
			.orderId(validateOrderRequest.getBeerOrderDto().getId())
			.valid(valid)
			.build());
	}
}
