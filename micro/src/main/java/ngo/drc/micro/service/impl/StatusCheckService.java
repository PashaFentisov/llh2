package ngo.drc.micro.service.impl;

import ngo.drc.core.exception.PermissionException;
import ngo.drc.core.exception.StatusException;
import ngo.drc.core.security.entity.User;
import ngo.drc.micro.dto.ApplicationFormMicroUpdateDto;
import ngo.drc.micro.entity.ApplicationFormMicro;
import ngo.drc.micro.enumeration.MicroStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class StatusCheckService {

    public void isStatusValid(ApplicationFormMicroUpdateDto applicationFormMicroUpdateDto,
                              ApplicationFormMicro applicationFormMicro, User user) {
        MicroStatus status = MicroStatus.valueOf(applicationFormMicroUpdateDto.getStatus());
        if (applicationFormMicroUpdateDto.getStatus() != null) {
            if (applicationFormMicro.getStatus().getName().contains("Reject") && user.getRole().getName().equals("ROLE_OPERATOR")) {
                throw new PermissionException("You don't have permission to change status");
                //якщо поточний статус reject і роль оператор то не дозволяємо змінювати статус
            }
            if (status == MicroStatus.FORM_MICRO_FUNDED && (applicationFormMicroUpdateDto.getDateOfFunding() == null ||
                    (applicationFormMicro.getDonor() == null && applicationFormMicroUpdateDto.getDonor() == null))) {
                throw new StatusException("Date of funding and donor must be set when status is funded");
                //якщо статус funded то дата фандінгу і донор мають бути встановлені
                //якщо на оновлення приходить статус funded то має прийти і дата фандінгу
                // і якщо донор до цього був null то має прийти значення для нього
            }
            if (user.getRole().getName().equals("ROLE_ADMIN")) {
                if (MicroStatus.getStatusesAfter(applicationFormMicro.getStatus()).contains(status) || status == MicroStatus.getPreviousStatus(applicationFormMicro.getStatus())) {
                    applicationFormMicro.setStatus(status);
                    //якщо роль admin і прийшов будь який статус який стоїть після поточного або той який перед поточним то міняємо
                } else {
                    throw new StatusException(String.format("You can't change status from %s to %s", applicationFormMicro.getStatus(), status));
                }
            }
            applicationFormMicro.setStatus(status); //якщо роль не адмін і поточний статус не reject то міняємо статус на той який прийшов
        }
        if (applicationFormMicro.getStatus() == MicroStatus.FORM_MICRO_APPROVED) {
            applicationFormMicro.setDateOfApproval(OffsetDateTime.now());
        }
    }
}
