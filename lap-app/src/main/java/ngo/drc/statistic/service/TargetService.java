package ngo.drc.statistic.service;

import lombok.RequiredArgsConstructor;
import ngo.drc.micro.enumeration.MicroDonor;
import ngo.drc.micro.enumeration.MicroStatus;
import ngo.drc.micro.repository.ApplicationFormMicroRepository;
import ngo.drc.statistic.dto.Stats;
import ngo.drc.statistic.entity.ModuleStatistic;
import ngo.drc.statistic.entity.Target;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TargetService {//todo create interface
    private final ApplicationFormMicroRepository applicationFormMicroRepository;

    public Target calculateTarget() {
        Target target = new Target();
        Map<String, ModuleStatistic> allModuleStatistic = new HashMap<>();

        ModuleStatistic micro = buildModuleStatistic();
        allModuleStatistic.put("Micro", micro);
        //todo add other modules

        target.setAllModuleStatistics(allModuleStatistic);

        Map<String, Long> waitingForApproval = new HashMap<>();
        waitingForApproval.put("Micro", countApplicationMicroFormsInStatusWaitingForApproval());
        //todo add other modules
        target.setWaitingForApproval(waitingForApproval);
        return target;
    }

    private Long countApplicationMicroFormsInStatusWaitingForApproval() {
        return applicationFormMicroRepository.countApplicationFormsInStatus(
                List.of(MicroStatus.FORM_MICRO_WAITING_FOR_APPROVAL));
    }

    private ModuleStatistic buildModuleStatistic() {
        Map<String, Stats> microStatisticMap = new HashMap<>();
        microStatisticMap.put("Total", buildTotalStats(600));
        microStatisticMap.put("SDC", buildStatsByDonor(MicroDonor.SDC1, 300)); //todo в нас є два SDC яке з них має бути
        microStatisticMap.put("DFID1", buildStatsByDonor(MicroDonor.DFID1, 60));
        microStatisticMap.put("DFID2", buildStatsByDonor(MicroDonor.DFID2, 150));
        microStatisticMap.put("DFID3", buildStatsByDonor(MicroDonor.DFID3, 90));
        return ModuleStatistic.builder()
                .moduleStatisticMap(microStatisticMap)
                .build();
    }

    private Stats buildStatsByDonor(MicroDonor donor, int maxPossibleValue) {
        return new Stats(applicationFormMicroRepository
                .countApplicationFormsInStatusAndByDonor(getProcessedStatuses(), donor), maxPossibleValue);
    }

    private Stats buildTotalStats(int maxPossibleValue) {
        List<MicroStatus> processedStatuses = getProcessedStatuses();
        return new Stats(applicationFormMicroRepository
                .countApplicationFormsInStatus(processedStatuses), maxPossibleValue);
    }

    private List<MicroStatus> getProcessedStatuses() {
        return MicroStatus.getStatusesAfter(MicroStatus.FORM_MICRO_FUNDED).stream()
                .filter(status -> !(status.equals(MicroStatus.FORM_MICRO_PENDING) ||
                        status.equals(MicroStatus.FORM_MICRO_REJECT_FOREVER))
                )
//                .map(MicroStatus::getName)
                .toList();
    }

}
