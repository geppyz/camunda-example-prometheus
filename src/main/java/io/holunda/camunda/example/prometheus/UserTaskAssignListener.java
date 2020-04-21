package io.holunda.camunda.example.prometheus;

import io.digitalstate.camunda.prometheus.collectors.SimpleCounterMetric;
import io.digitalstate.camunda.prometheus.collectors.SimpleGaugeMetric;
import io.prometheus.client.Gauge;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class UserTaskAssignListener implements TaskListener {

    private static final SimpleGaugeMetric counter = new SimpleGaugeMetric("active_user_tasks_count", "Number of assigned User Tasks", Collections.singletonList("taskType"));
    //private static final SimpleGaugeMetric unassigned = new SimpleGaugeMetric("pizza_active_unassigned_user_tasks_count", "Number of created User Tasks", Collections.singletonList("taskType"));

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("A user task has been assigned!");
        counter.increment(Collections.singletonList(delegateTask.getTaskDefinitionKey()));
        //unassigned.decrement(Collections.singletonList(delegateTask.getTaskDefinitionKey()));
    }
}
