package io.holunda.camunda.example;

import io.digitalstate.camunda.prometheus.PrometheusHelpers;
import io.digitalstate.camunda.prometheus.PrometheusProcessEnginePlugin;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableProcessApplication
public class CamundaExamplePrometheus {
    public static void main(String[] args) {
        SpringApplication.run(CamundaExamplePrometheus.class, args);
    }

    @Bean
    PrometheusProcessEnginePlugin prometheusProcessMetricsProcessEnginePlugin() {
        final PrometheusProcessEnginePlugin plugin = new PrometheusProcessEnginePlugin();
        plugin.setCollectorYmlFilePath("prometheus-metrics.yml");
        plugin.setCamundaReportingIntervalInSeconds("5");
        plugin.setPort("9999");
        plugin.setBpmnDurationParseListener("true");
        return plugin;
    }
}
