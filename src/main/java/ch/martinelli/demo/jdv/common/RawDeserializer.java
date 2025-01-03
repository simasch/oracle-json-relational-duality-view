package ch.martinelli.demo.jdv.common;

import jakarta.json.JsonValue;
import jakarta.json.bind.JsonbException;
import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.stream.JsonParser;
import oracle.sql.json.OracleJsonBinary;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.HexFormat;

/**
 * JSON-B deserializer for raw JSON data.
 * <p>
 * Workaround for <a href="https://github.com/oracle/spring-cloud-oracle/issues/164#issuecomment-2568499039">...</a>
 */
public class RawDeserializer implements JsonbDeserializer<String> {

    @Override
    public String deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {

        try {
            JsonValue value = parser.getValue();
            OracleJsonBinary oracleJsonBinary = ((java.sql.Wrapper) value).unwrap(OracleJsonBinary.class);
            byte[] raw = oracleJsonBinary.getBytes();
            return HexFormat.of().withUpperCase().formatHex(raw);
        } catch (SQLException e) {
            throw new JsonbException(e.getMessage(), e);
        }
    }
}
