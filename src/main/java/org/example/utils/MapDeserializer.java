package org.example.utils;

import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import javax.json.bind.serializer.DeserializationContext;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MapDeserializer implements JsonbDeserializer<Map<String, String>> {

    @Override
    public Map<String, String> deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        Map<String, String> map = new HashMap<>();
        String key = null;

        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    key = parser.getString();
                    break;
                case VALUE_STRING:
                    if (key != null) {
                        map.put(key, parser.getString());
                    }
                    break;
                default:
                    break;
            }
        }
        return map;
    }
}

