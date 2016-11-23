package app.core;

import app.io.Reader;
import app.io.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Engine implements CommandLineRunner {

    @Autowired
    private CommandDispatcher commandDispatcher;

    @Autowired
    private Reader reader;

    @Autowired
    private Writer writer;

    @Override
    public void run(String... strings) throws Exception {

        try {
            String input = this.reader.readLine();
            String[] data = input.split("[\\s]+");
            String commandName = data[0];
            String result = this.commandDispatcher.dispatchCommand(commandName, data).Execute();
            this.writer.writeLine(result);

        } catch (Exception e) {

            this.writer.writeLine(e.getMessage());
        }
    }
}

