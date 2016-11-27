package app.parsers.json;

import app.parsers.file.FileParser;
import app.parsers.file.FileParserImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonParserImpl implements JsonParser {

    private Gson gson;

    @Autowired
    private FileParser fileParser;

    private JsonParserImpl() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        this.fileParser = new FileParserImpl();
    }

    @Override
    public <T> T importJson(Class<T> clazz, String fileName) throws IOException {

        T object = null;
        String file = this.fileParser.readFile(fileName);
        object = this.gson.fromJson(file, clazz);
        return object;
    }

    @Override
    public <T> void exportJson(T object, String fileName) throws IOException {

        String content = this.gson.toJson(object);
        this.fileParser.writeFile(fileName, content);
    }
}
