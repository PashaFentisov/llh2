package ngo.drc.micro.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MicroStatus {
    FORM_MICRO_NEW("New"), FORM_MICRO_VETTING("Vetting"), FORM_MICRO_REJECT_VETTING("Reject vetting"),
    FORM_MICRO_PHONE_INTERVIEW("Phone interview"), FORM_MICRO_REJECT_PHONE_INTERVIEW("Reject phone interview"),
    FORM_MICRO_FIELD_VISIT("Field visit"), FORM_MICRO_REJECT_FIELD_VISIT("Reject field visit"),
    FORM_MICRO_WAITING_FOR_APPROVAL("Waiting approval"), FORM_MICRO_APPROVED("Approved"),
    FORM_MICRO_REJECT_APPROVED("Reject after approved"), FORM_MICRO_DOCUMENTS("Documents"),
    FORM_MICRO_WAITING_SIGNING("Waiting signing"), FORM_MICRO_SIGNED("Signed"),
    FORM_MICRO_FUNDED("Funded"), FORM_MICRO_WAITING_RECEIPTS("Waiting receipts"),
    FORM_MICRO_RECEIPTS_SUBMITTED("Receipts submitted"), FORM_MICRO_REJECT_FOREVER("Reject forever"),
    FORM_MICRO_MONITORING("Monitoring"), FORM_MICRO_MONITORING_COMPLETED("Monitoring completed"),
    FORM_MICRO_PENDING("Pending");
    private String name;
}
