package ch.martinelli.demo.jdv.common;

import jakarta.json.bind.annotation.JsonbTypeDeserializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Metadata {

    @JsonbTypeDeserializer(RawDeserializer.class)
    private String asof;
    @JsonbTypeDeserializer(RawDeserializer.class)
    private String etag;
}
