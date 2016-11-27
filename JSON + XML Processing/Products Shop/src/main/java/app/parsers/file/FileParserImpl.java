package app.parsers.file;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileParserImpl implements FileParser {

    @Override
    public String readFile(String fileName) throws IOException {

        StringBuilder fileContent = new StringBuilder();

        try (
                InputStream is = getClass().getResourceAsStream(fileName);
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is))
        ) {

            String line;
            while ((line = bfr.readLine()) != null) {

                fileContent.append(line);
            }
        }
        return fileContent.toString();
    }

    @Override
    public void writeFile(String fileName, String content) throws IOException {

        try (OutputStream os = new FileOutputStream(fileName);
             BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os))
        ) {
            bfw.write(String.valueOf(content));
        }
    }
}
