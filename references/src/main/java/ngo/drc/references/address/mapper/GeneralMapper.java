package ngo.drc.references.address.mapper;

public interface GeneralMapper <T, E>{
    T toDto(E entity);
    E toEntity(T dto);
}
