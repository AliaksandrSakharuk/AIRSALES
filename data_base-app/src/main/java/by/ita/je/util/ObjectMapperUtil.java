package by.ita.je.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ObjectMapperUtil {

    public <E,D> List<D> convertEToD(List<E> eClass, Class<D> dClass){
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new JavaTimeModule());
        return eClass.stream()
                .map(e -> objectMapper.convertValue(e, dClass))
                .collect(Collectors.toList());
    }

    public <E,D> D convertEToD(E eClass, Class<D> dClass){
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new JavaTimeModule());
        return objectMapper.convertValue(eClass, dClass);
    }
}
