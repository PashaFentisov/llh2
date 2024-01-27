package ngo.drc.statistic.entity;

import lombok.*;
import ngo.drc.statistic.dto.Stats;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ModuleStatistic {
    @Builder.Default
    private Map<String, Stats> moduleStatisticMap = new HashMap<>();
}
