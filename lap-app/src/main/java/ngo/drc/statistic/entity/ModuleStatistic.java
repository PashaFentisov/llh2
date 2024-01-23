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
    private Map<String, Stats> moduleStatisticMap = new HashMap<>();
}
