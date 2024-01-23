package ngo.drc.statistic.entity;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Target {
    private Map<String, ModuleStatistic> allModuleStatistics = new HashMap<>();
    private Map<String, Long> waitingForApproval = new HashMap<>();
}
