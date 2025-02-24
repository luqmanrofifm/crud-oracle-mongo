package com.example.crud_oracle_mongo.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.nio.ByteBuffer;
import java.util.UUID;

@Converter(
        autoApply = true
)
public class UUIDConverter implements AttributeConverter<UUID, byte[]> {
    public byte[] convertToDatabaseColumn(UUID uuid) {
        if (uuid == null) {
            return null;
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(16);
            buffer.putLong(uuid.getMostSignificantBits());
            buffer.putLong(uuid.getLeastSignificantBits());
            return buffer.array();
        }
    }

    public UUID convertToEntityAttribute(byte[] dbData) {
        if (dbData == null) {
            return null;
        } else {
            ByteBuffer buffer = ByteBuffer.wrap(dbData);
            return new UUID(buffer.getLong(), buffer.getLong());
        }
    }
}
