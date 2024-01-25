package ngo.drc.statistic.service;

import lombok.RequiredArgsConstructor;
import ngo.drc.micro.dto.ApplicationFormMicroStatsDto;
import ngo.drc.micro.enumeration.MicroDonor;
import ngo.drc.micro.enumeration.MicroStatus;
import ngo.drc.micro.repository.ApplicationFormMicroRepository;
import ngo.drc.statistic.dto.Stats;
import ngo.drc.statistic.entity.ModuleStatistic;
import ngo.drc.statistic.entity.Target;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TargetServiceSecVersion {

    private final ApplicationFormMicroRepository applicationFormMicroRepository;

    public Target calculateTarget() {
        List<ApplicationFormMicroStatsDto> allMicroFormsInRightStatus = getAllMicroFormsInRightStatus();
        Target target = new Target();

        Map<String, ModuleStatistic> moduleStats = new HashMap<>();
        moduleStats.put("Micro", buildModuleStatistic(allMicroFormsInRightStatus));

        Map<String, Long> waitingForApproval = new HashMap<>();
        waitingForApproval.put("Micro", countMicroWaitingForApproval(allMicroFormsInRightStatus));

        target.setAllModuleStatistics(moduleStats);
        target.setWaitingForApproval(waitingForApproval);
        return target;
    }

    private Long countMicroWaitingForApproval(List<ApplicationFormMicroStatsDto> allMicroFormsInRightStatus) {
        return allMicroFormsInRightStatus.stream()
                .filter(apl -> apl.getStatus().equals(MicroStatus.FORM_MICRO_WAITING_FOR_APPROVAL))
                .count();
    }

    private ModuleStatistic buildModuleStatistic(List<ApplicationFormMicroStatsDto> allMicroFormsInRightStatus) {
        Map<String, Stats> microStatisticMap = new LinkedHashMap<>();
        List<MicroDonor> microDonors = Arrays.stream(MicroDonor.values()).toList();

        int totalMaxPossibleValue = microDonors.stream()
                .mapToInt(MicroDonor::getMaxPossibleAmountOfApplications)
                .sum();

        microStatisticMap.put("Total", new Stats(allMicroFormsInRightStatus.size(), totalMaxPossibleValue));
        for (MicroDonor microDonor : microDonors) {
            microStatisticMap.put(microDonor.name(), buildStatsByDonor(allMicroFormsInRightStatus, microDonor));
        }
        return ModuleStatistic.builder()
                .moduleStatisticMap(microStatisticMap)
                .build();
    }

    private Stats buildStatsByDonor(List<ApplicationFormMicroStatsDto> allMicroFormsInRightStatus, MicroDonor donor) {
        long count = allMicroFormsInRightStatus.stream()
                .filter(form -> form.getDonor() == donor)
                .count();
        return new Stats(count, donor.getMaxPossibleAmountOfApplications());
    }

    public List<ApplicationFormMicroStatsDto> getAllMicroFormsInRightStatus() {
        List<MicroStatus> statusesAfterFunded = getStatusesAfterFunded();
        return applicationFormMicroRepository.getApplicationFormsInStatus(statusesAfterFunded);
    }

    private List<MicroStatus> getStatusesAfterFunded() {
        return MicroStatus.getStatusesAfter(MicroStatus.FORM_MICRO_SIGNED).stream()
                .filter(status -> !(status.equals(MicroStatus.FORM_MICRO_PENDING) ||
                        status.equals(MicroStatus.FORM_MICRO_REJECT_FOREVER))
                )
                .toList();
    }
}

