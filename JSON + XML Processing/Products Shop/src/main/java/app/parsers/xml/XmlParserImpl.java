package app.parsers.xml;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component
public class XmlParserImpl implements XmlParser {

    private JAXBContext jaxbContext;

    private XmlParserImpl() {
    }

    public <T> void write(T object, String fileName) throws JAXBException, IOException {

        this.jaxbContext = JAXBContext.newInstance(object.getClass());

        try (
                OutputStream outputStream = new FileOutputStream(fileName);
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(outputStream))
                ) {

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(object, bfw);
        }
    }

    @Override
    public <T> T read(Class<T> clazz, String fileName) throws JAXBException, IOException {

        T object = null;
        this.jaxbContext = JAXBContext.newInstance(clazz);

        try (
                InputStream inputStream = getClass().getResourceAsStream(fileName);
                BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream))
                ) {

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            object = (T) unmarshaller.unmarshal(bfr);
        }

        return object;
    }
}
