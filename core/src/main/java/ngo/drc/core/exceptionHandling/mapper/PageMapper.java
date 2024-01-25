package ngo.drc.core.exceptionHandling.mapper;

import ngo.drc.core.exceptionHandling.PageResponse;
import org.springframework.data.domain.Page;

public interface PageMapper {
    <T> PageResponse<T> toPageResponse(Page<T> page);
}
