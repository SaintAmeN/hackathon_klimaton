package pl.ssh.pr.common.component.rest;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.PrintWriter;
import java.util.List;


/**
 * @author Paweł Recław, AmeN
 * @created 04/12/2021
 * @project klimaton
 * <p>
 * Reactive response factory
 */
@Component
public class ResponseFactory {

    /**
     * Pack Mono content into ResponseEntity with certain status
     *
     * @param status  http
     * @param content
     * @return reactiveResponse
     */
    public <T> Mono<ResponseEntity<T>> createReactiveResponseFromMono(HttpStatus status, Mono<T> content) {
        return content.map(t -> ResponseEntity.status(status).body(t));
    }

    /**
     * Pack Flux content into ResponseEntity with certain status
     *
     * @param status  http
     * @param content
     * @return reactiveResponse
     */
    public <T> Flux<ResponseEntity<T>> createReactiveResponseFromFlux(HttpStatus status, Flux<T> content) {
        return content.map(t -> ResponseEntity.status(status).body(t));
    }

    public <T> void downloadCSVFile(PrintWriter responseWriter, List<T> content) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        StatefulBeanToCsv<T> writer = new StatefulBeanToCsvBuilder<T>(responseWriter)
                .withQuotechar(CSVWriter.NO_ESCAPE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();
        writer.write(content);
    }
}
