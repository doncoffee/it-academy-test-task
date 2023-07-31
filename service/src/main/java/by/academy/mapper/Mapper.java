package by.academy.mapper;

public interface Mapper<T, R> {

    T mapToEntity(R object);
    R mapToDto(T object);
}
