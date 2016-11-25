package app.parsers.json;

import java.io.IOException;

public interface JsonParser {

    <T> T importJson(Class<T> clazz, String fileName) throws IOException;

    <T> void exportJson(T object, String fileName) throws IOException;
}
