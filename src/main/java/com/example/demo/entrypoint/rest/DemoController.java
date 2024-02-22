package com.example.demo.entrypoint.rest;

import com.example.demo.entrypoint.request.*;
import com.example.demo.entrypoint.request.util.validation.AValidatable;
import io.github.renatolsjf.chassis.monitoring.request.HealthRequest;
import io.github.renatolsjf.chassis.request.EntryResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {

    @GetMapping("healthcheck")
    public ResponseEntity status() {
        return ResponseEntity.ok(new HealthRequest().process().render());
    }

    @GetMapping("projection-test")
    public ResponseEntity projectionTest(@RequestParam(required = false) List<String> projection) {
        return ResponseEntity.ok(new ProjectionTestRequest(projection).process().render());
    }

    @GetMapping("logging-test")
    public ResponseEntity requestEntriesTest(@RequestHeader(name = EntryResolver.HTTP_HEADER, required = false) String contextEntries) {
        return ResponseEntity.ok(new LoggingTestRequest(contextEntries).process().render());
    }

    @GetMapping("rendering-test")
    public ResponseEntity renderingTest(@RequestParam(required = false) List<String> projection,
                                        @RequestParam(required = false) Integer age) {
        return ResponseEntity.ok(new RenderingTestRequest(Operations.RENDERING_TEST_OPERATION, projection, age).process().render());
    }

    @GetMapping("rendering-test-2")
    public ResponseEntity renderingTest2(@RequestParam(required = false) List<String> projection,
                                        @RequestParam(required = false) Integer age) {
        return ResponseEntity.ok(new RenderingTestRequest(Operations.RENDERING_TEST_2_OPERATION, projection, age).process().render());
    }

    @GetMapping("rendering-test-3")
    public ResponseEntity renderingTest3(@RequestParam(required = false) List<String> projection) {
        return ResponseEntity.ok(new RenderingTest3Request(projection).process().render());
    }

    @PostMapping("validation-test")
    public ResponseEntity validationTest(@RequestBody AValidatable aValidatable) {
        return ResponseEntity.ok(new ValidatableTestRequest(Operations.VALIDATION_TEST_OPERATION, aValidatable).process().render());
    }

    @PostMapping("validation-test-2")
    public ResponseEntity validationTest2(@RequestBody AValidatable aValidatable) {
        return ResponseEntity.ok(new ValidatableTestRequest(Operations.VALIDATION_TEST_2_OPERATION, aValidatable).process().render());
    }

    @GetMapping("integration-test")
    public ResponseEntity integrationTest() {
        return ResponseEntity.ok(new IntegrationTestRequest().process().render());
    }

    @GetMapping("/timing-test")
    public ResponseEntity timingTest(@RequestParam String tag) {
        return ResponseEntity.ok(new TimingTestRequest(tag).process().render());
    }


}
