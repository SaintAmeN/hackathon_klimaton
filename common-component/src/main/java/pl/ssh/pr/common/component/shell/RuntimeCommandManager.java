package pl.ssh.pr.common.component.shell;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Executes a shell command and returns its output.
 */
@Component
@Slf4j
public class RuntimeCommandManager {
    private final Runtime runtime = Runtime.getRuntime();

    /**
     * Executes a command and returns it's output, since Runtime.exec() is a blocking method, flux cannot be returned.
     */
    public Optional<List<String>> executeCommand(String[] command) {
        try {
            Process p = runtime.exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            List<String> output = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                output.add(line);
                line = reader.readLine();
            }
            return Optional.of(output);
        } catch (IOException ex) {
            log.error("Cannot execute shell command: " + Arrays.toString(command) + ".");
            return Optional.empty();
        }
    }
}
