package io.holunda.camunda.example.prometheus;

import io.digitalstate.camunda.prometheus.collectors.SimpleGaugeMetric;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class CloseCaseListener implements ExecutionListener {

    private static final SimpleGaugeMetric openCases = new SimpleGaugeMetric("open_cases", "Number of Open Cases, labeled by Case Type", Collections.singletonList("type"));
    private static final SimpleGaugeMetric closedCases = new SimpleGaugeMetric("closed_cases", "Number of Closed Cases, labeled by Case Type", Collections.singletonList("type"));

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        log.info("A case has been closed.");
        openCases.decrement(Collections.singletonList("standard"));
        closedCases.increment(Collections.singletonList("standard"));
    }
}