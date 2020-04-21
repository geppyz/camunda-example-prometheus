package io.holunda.camunda.example.prometheus;

import io.digitalstate.camunda.prometheus.collectors.SimpleCounterMetric;
import io.digitalstate.camunda.prometheus.collectors.SimpleGaugeMetric;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class UserTaskCompleteListener implements TaskListener {

    private static final SimpleGaugeMetric counter = new SimpleGaugeMetric("complete_user_tasks_count", "Number of completed User Tasks", Collections.singletonList("taskType"));
    //private static final SimpleGaugeMetric active = new SimpleGaugeMetric("active_user_tasks_count", "Number of assigned User Tasks", Collections.singletonList("taskType"));

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("A user task has been completed!");
        counter.increment(Collections.singletonList(delegateTask.getTaskDefinitionKey()));
        //active.decrement(Collections.singletonList(delegateTask.getTaskDefinitionKey()));
    }
}
