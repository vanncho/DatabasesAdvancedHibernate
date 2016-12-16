package app.parsers.xml;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface XmlParser {

    <T> void write(T object, String fileName) throws JAXBException, IOException;

    <T> T read(Class<T> clazz, String fileName) throws JAXBException, IOException;
}

