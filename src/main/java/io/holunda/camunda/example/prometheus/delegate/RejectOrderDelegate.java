package io.holunda.camunda.example.prometheus.delegate;

import io.digitalstate.camunda.prometheus.collectors.SimpleGaugeMetric;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j(topic = "rejectOrder")
@Component("rejectOrder")
public class RejectOrderDelegate implements JavaDelegate {

    private static final SimpleGaugeMetric pizzaRejected = new SimpleGaugeMetric("pizza_rejected", "Number of pizza orders rejected", Collections.singletonList("type"));

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Order rejected");
        pizzaRejected.increment(Collections.singletonList("rejected"));
    }
}
