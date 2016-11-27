package app.parsers.file;

import java.io.IOException;

public interface FileParser {

    String readFile(String file) throws IOException;

    void writeFile(String fileName, String content) throws IOException;
}
