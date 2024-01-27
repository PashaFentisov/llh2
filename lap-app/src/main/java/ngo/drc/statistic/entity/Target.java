package ngo.drc.statistic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Target {
    @Builder.Default
    private Map<String, ModuleStatistic> allModuleStatistics = new HashMap<>();
    @Builder.Default
    private Map<String, Long> waitingForApproval = new HashMap<>();
}
