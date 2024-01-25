package ngo.drc.micro.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
public enum MicroStatusOperator {
    FORM_MICRO_NEW("New"),
    FORM_MICRO_VETTING("Vetting"),
    FORM_MICRO_REJECT_VETTING("Reject vetting"),
    FORM_MICRO_PHONE_INTERVIEW("Phone interview"),
    FORM_MICRO_REJECT_PHONE_INTERVIEW("Reject phone interview"),
    FORM_MICRO_FIELD_VISIT("Field visit"),
    FORM_MICRO_REJECT_FIELD_VISIT("Reject field visit"),
    FORM_MICRO_WAITING_FOR_APPROVAL("Waiting approval"),
    FORM_MICRO_APPROVED("Approved"),
    FORM_MICRO_DOCUMENTS("Documents"),  //return one
    FORM_MICRO_WAITING_SIGNING("Waiting signing"),
    FORM_MICRO_SIGNED("Signed"),
    FORM_MICRO_FUNDED("Funded"),
    FORM_MICRO_WAITING_RECEIPTS("Waiting receipts"),
    FORM_MICRO_RECEIPTS_SUBMITTED("Receipts submitted"),
    FORM_MICRO_MONITORING("Monitoring"),
    FORM_MICRO_MONITORING_COMPLETED("Monitoring completed");
    private final String name;

    public static Set<MicroStatus> getNextStatuses(MicroStatus status) {
        LinkedHashSet<MicroStatus> statusSet = new LinkedHashSet<>();
        switch (status) {
            case FORM_MICRO_NEW -> {
                statusSet.add(MicroStatus.FORM_MICRO_NEW);
                statusSet.add(MicroStatus.FORM_MICRO_VETTING);
                statusSet.add(MicroStatus.FORM_MICRO_REJECT_VETTING);
                return statusSet;
            }
            case FORM_MICRO_VETTING -> {
                statusSet.add(MicroStatus.FORM_MICRO_VETTING);
                statusSet.add(MicroStatus.FORM_MICRO_REJECT_VETTING);
                statusSet.add(MicroStatus.FORM_MICRO_PHONE_INTERVIEW);
                return statusSet;
            }
            case FORM_MICRO_REJECT_VETTING -> {
                statusSet.add(MicroStatus.FORM_MICRO_REJECT_VETTING);
                statusSet.add(MicroStatus.FORM_MICRO_PHONE_INTERVIEW);
                statusSet.add(MicroStatus.FORM_MICRO_REJECT_PHONE_INTERVIEW);
                return statusSet;
            }
            case FORM_MICRO_PHONE_INTERVIEW -> {
                statusSet.add(MicroStatus.FORM_MICRO_PHONE_INTERVIEW);
                statusSet.add(MicroStatus.FORM_MICRO_REJECT_PHONE_INTERVIEW);
                statusSet.add(MicroStatus.FORM_MICRO_FIELD_VISIT);
                return statusSet;
            }
            case FORM_MICRO_REJECT_PHONE_INTERVIEW -> {
                statusSet.add(MicroStatus.FORM_MICRO_REJECT_PHONE_INTERVIEW);
                statusSet.add(MicroStatus.FORM_MICRO_FIELD_VISIT);
                statusSet.add(MicroStatus.FORM_MICRO_REJECT_FIELD_VISIT);
                return statusSet;
            }
            case FORM_MICRO_FIELD_VISIT -> {
                statusSet.add(MicroStatus.FORM_MICRO_FIELD_VISIT);
                statusSet.add(MicroStatus.FORM_MICRO_REJECT_FIELD_VISIT);
                statusSet.add(MicroStatus.FORM_MICRO_WAITING_FOR_APPROVAL);
                return statusSet;
            }
            case FORM_MICRO_REJECT_FIELD_VISIT -> {
                statusSet.add(MicroStatus.FORM_MICRO_REJECT_FIELD_VISIT);
                statusSet.add(MicroStatus.FORM_MICRO_WAITING_FOR_APPROVAL);
                statusSet.add(MicroStatus.FORM_MICRO_APPROVED);
                return statusSet;
            }
            case FORM_MICRO_WAITING_FOR_APPROVAL -> {
                statusSet.add(MicroStatus.FORM_MICRO_WAITING_FOR_APPROVAL);
                statusSet.add(MicroStatus.FORM_MICRO_APPROVED);
                statusSet.add(MicroStatus.FORM_MICRO_DOCUMENTS);
                return statusSet;
            }
            case FORM_MICRO_APPROVED -> {
                statusSet.add(MicroStatus.FORM_MICRO_APPROVED);
                statusSet.add(MicroStatus.FORM_MICRO_DOCUMENTS);
                statusSet.add(MicroStatus.FORM_MICRO_WAITING_SIGNING);
                return statusSet;
            }
            case FORM_MICRO_DOCUMENTS -> {
                statusSet.add(MicroStatus.FORM_MICRO_DOCUMENTS);
                statusSet.add(MicroStatus.FORM_MICRO_WAITING_SIGNING);
                return statusSet;
            }
            case FORM_MICRO_WAITING_SIGNING -> {
                statusSet.add(MicroStatus.FORM_MICRO_WAITING_SIGNING);
                statusSet.add(MicroStatus.FORM_MICRO_SIGNED);
                return statusSet;
            }
            case FORM_MICRO_SIGNED -> {
                statusSet.add(MicroStatus.FORM_MICRO_SIGNED);
                statusSet.add(MicroStatus.FORM_MICRO_FUNDED);
                return statusSet;
            }
            case FORM_MICRO_FUNDED -> {
                statusSet.add(MicroStatus.FORM_MICRO_FUNDED);
                statusSet.add(MicroStatus.FORM_MICRO_WAITING_RECEIPTS);
                return statusSet;
            }
            case FORM_MICRO_WAITING_RECEIPTS -> {
                statusSet.add(MicroStatus.FORM_MICRO_WAITING_RECEIPTS);
                statusSet.add(MicroStatus.FORM_MICRO_RECEIPTS_SUBMITTED);
                return statusSet;
            }
            case FORM_MICRO_RECEIPTS_SUBMITTED -> {
                statusSet.add(MicroStatus.FORM_MICRO_RECEIPTS_SUBMITTED);
                statusSet.add(MicroStatus.FORM_MICRO_MONITORING);
                return statusSet;
            }
            case FORM_MICRO_MONITORING -> {
                statusSet.add(MicroStatus.FORM_MICRO_MONITORING);
                statusSet.add(MicroStatus.FORM_MICRO_MONITORING_COMPLETED);
                return statusSet;
            }
            case FORM_MICRO_REJECT_APPROVED -> {
                statusSet.add(MicroStatus.FORM_MICRO_REJECT_APPROVED);
                return statusSet;
            }
            case FORM_MICRO_PENDING -> {
                statusSet.add(MicroStatus.FORM_MICRO_PENDING);
                return statusSet;
            }
            case FORM_MICRO_REJECT_FOREVER -> {
                statusSet.add(MicroStatus.FORM_MICRO_REJECT_FOREVER);
                return statusSet;
            }
            default -> {
                return Set.of();
            }
        }
    }
}
//dont return pending and reject forever reject approved to operator
