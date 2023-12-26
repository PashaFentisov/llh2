package ngo.drc.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericPageFormResponse<T, E> {
    private T formReferenceInfo;
    private E pageFormResponse;
}
