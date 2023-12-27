package ngo.drc.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericFormResponse<T, E> {
    private T formReferenceInfo;
    private E form;
    private Map<String, String> statuses;
}
