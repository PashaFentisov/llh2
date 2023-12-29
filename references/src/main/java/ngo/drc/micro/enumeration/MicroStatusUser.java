package ngo.drc.micro.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public enum MicroStatusUser {
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
    private String name;

    public static Set<MicroStatusUser> getNextStatuses(MicroStatus status) {
        switch (status) {
            case FORM_MICRO_NEW -> {
                return Set.of(MicroStatusUser.FORM_MICRO_VETTING, MicroStatusUser.FORM_MICRO_REJECT_VETTING); //todo check if correct
            }
            case FORM_MICRO_VETTING -> {
                return Set.of(MicroStatusUser.FORM_MICRO_REJECT_VETTING, MicroStatusUser.FORM_MICRO_PHONE_INTERVIEW);
            }
            case FORM_MICRO_REJECT_VETTING -> {
                return Set.of(MicroStatusUser.FORM_MICRO_PHONE_INTERVIEW, MicroStatusUser.FORM_MICRO_REJECT_PHONE_INTERVIEW);
            }
            case FORM_MICRO_PHONE_INTERVIEW -> {
                return Set.of(MicroStatusUser.FORM_MICRO_REJECT_PHONE_INTERVIEW, MicroStatusUser.FORM_MICRO_FIELD_VISIT);
            }
            case FORM_MICRO_REJECT_PHONE_INTERVIEW -> {
                return Set.of(MicroStatusUser.FORM_MICRO_FIELD_VISIT, MicroStatusUser.FORM_MICRO_REJECT_FIELD_VISIT);
            }

            case FORM_MICRO_FIELD_VISIT -> {
                return Set.of(MicroStatusUser.FORM_MICRO_REJECT_FIELD_VISIT, MicroStatusUser.FORM_MICRO_WAITING_FOR_APPROVAL);
            }

            case FORM_MICRO_REJECT_FIELD_VISIT -> {
                return Set.of(MicroStatusUser.FORM_MICRO_WAITING_FOR_APPROVAL, MicroStatusUser.FORM_MICRO_APPROVED);
            }

            case FORM_MICRO_WAITING_FOR_APPROVAL -> {
                return Set.of(MicroStatusUser.FORM_MICRO_APPROVED, MicroStatusUser.FORM_MICRO_DOCUMENTS);
            }
            case FORM_MICRO_APPROVED -> {
                return Set.of(MicroStatusUser.FORM_MICRO_DOCUMENTS, MicroStatusUser.FORM_MICRO_WAITING_SIGNING);
            }
            case FORM_MICRO_DOCUMENTS -> {
                return Set.of(MicroStatusUser.FORM_MICRO_WAITING_SIGNING, MicroStatusUser.FORM_MICRO_SIGNED);
            }
            case FORM_MICRO_WAITING_SIGNING -> {
                return Set.of(MicroStatusUser.FORM_MICRO_SIGNED, MicroStatusUser.FORM_MICRO_FUNDED);
            }
            case FORM_MICRO_SIGNED -> {
                return Set.of(MicroStatusUser.FORM_MICRO_FUNDED, MicroStatusUser.FORM_MICRO_WAITING_RECEIPTS);
            }
            case FORM_MICRO_FUNDED -> {
                return Set.of(MicroStatusUser.FORM_MICRO_WAITING_RECEIPTS, MicroStatusUser.FORM_MICRO_RECEIPTS_SUBMITTED);
            }
            case FORM_MICRO_WAITING_RECEIPTS -> {
                return Set.of(MicroStatusUser.FORM_MICRO_RECEIPTS_SUBMITTED, MicroStatusUser.FORM_MICRO_MONITORING);
            }
            case FORM_MICRO_RECEIPTS_SUBMITTED -> {
                return Set.of(MicroStatusUser.FORM_MICRO_MONITORING, MicroStatusUser.FORM_MICRO_MONITORING_COMPLETED);
            }
            case FORM_MICRO_MONITORING -> {
                return Set.of(MicroStatusUser.FORM_MICRO_MONITORING_COMPLETED);
            }
            default -> {
                return Set.of();
            }
            //todo якщо приходить статус який оператор бачити не може тобто reject approval ..... то повертає пустий список
        }
    }
}
//dont return pending and reject forever reject approved to operator
