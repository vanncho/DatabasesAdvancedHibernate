package app.io.write;

import org.springframework.stereotype.Component;

@Component
public class TerminalWriter implements Writer {

    @Override
    public void write(String message) {
        System.out.print(message);
    }

    @Override
    public void writeLine(String message) {
        System.out.println(message);
    }
}
