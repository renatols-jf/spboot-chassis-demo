package com.example.demo;

import io.github.renatolsjf.chassis.Chassis;

public class MetricDemo {

    public MetricDemo() {

        /*
        Increases the value by 1
         */
        Chassis.getInstance().getMetricRegistry().createBuilder("a_counter")
                .withTag("aTagName", "aTagValue")
                .buildCounter()
                .inc();
        /*
        Increases the value by the desired amount
         */
        Chassis.getInstance().getMetricRegistry().createBuilder("a_counter")
                .withTag("aTagName", "aTagValue")
                .buildCounter()
                .inc(2d);

        /*
        Exported to Prometheus as:
        a_counter_total{aTagName="aTagValue",} 3.0
         */
    }

}