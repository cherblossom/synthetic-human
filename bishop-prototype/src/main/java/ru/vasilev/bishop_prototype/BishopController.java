package ru.vasilev.bishop_prototype;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.starter.audit.WeylandWatchingYou;
import ru.vasilev.starter.model.Command;
import ru.vasilev.starter.model.ErrorResponse;
import ru.vasilev.starter.monitoring.MetricsService;
import ru.vasilev.starter.service.CommandService;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class BishopController {
    
    private final CommandService commandService;
    private final MetricsService metricsService;

    public BishopController(CommandService commandService, MetricsService metricsService) {
        this.commandService = commandService;
        this.metricsService = metricsService;
    }

    @WeylandWatchingYou
    @PostMapping("/commands")
    public ResponseEntity<String> submitCommand(@RequestBody Command command) {
        commandService.submitCommand(command);
        return ResponseEntity.ok("Команда успешно принята: " + command.getDescription());
    }


    @WeylandWatchingYou
    @GetMapping("/metrics")
    public ResponseEntity<Map<String, Integer>> getMetrics() {
        Map<String, Integer> metrics = new HashMap<>();
        metrics.put("queue_size", metricsService.getQueueSize());
        return ResponseEntity.ok(metrics);
    }
}