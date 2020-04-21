package io.holunda.camunda.example.prometheus.delegate;

import io.digitalstate.camunda.prometheus.collectors.SimpleGaugeMetric;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j(topic = "deliverPizza")
@Component("deliverPizza")
public class DeliverPizzaDelegate implements JavaDelegate {

    private static final SimpleGaugeMetric pizzaDelivered = new SimpleGaugeMetric("pizza_delivered", "Number of pizzas delivered", Collections.singletonList("type"));

    @Override
    public void execute(DelegateExecution execution) {
        String pizzaName = (String) execution.getVariable("pizza");
        log.info(String.format("Delivering pizza %s", execution.getVariable("pizza")));
        pizzaDelivered.increment(Collections.singletonList(pizzaName));
    }
}
