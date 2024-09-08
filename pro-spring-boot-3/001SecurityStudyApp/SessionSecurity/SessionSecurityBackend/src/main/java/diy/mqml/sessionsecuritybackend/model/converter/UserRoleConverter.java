package diy.mqml.sessionsecuritybackend.model.converter;


import diy.mqml.sessionsecuritybackend.model.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class UserRoleConverter implements AttributeConverter<List<UserRole>, String> {

    @Override
    public String convertToDatabaseColumn(List<UserRole> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return attribute.stream()
                .map(UserRole::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<UserRole> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        return Arrays.stream(dbData.split(","))
                .map(UserRole::valueOf)
                .collect(Collectors.toList());
    }
}
