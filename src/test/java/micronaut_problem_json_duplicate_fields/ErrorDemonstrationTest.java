package micronaut_problem_json_duplicate_fields;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MicronautTest
class ErrorDemonstrationTest {

    @Inject
    @Client("/users")
    private HttpClient client;

    @ParameterizedTest
    @CsvSource({
            "type",
            "status",
            "detail",
            "parameters"
    })
    public void test1(String regexp) {
        var response = "";
        try {
            client.toBlocking().retrieve("?par1=ab&par2=10&p");
            Assertions.fail();

        } catch (HttpClientException e) {
            response = e.getMessage();
        }
/*
        {
            "type":"about:blank",
            "status":400,
            "detail":"Required QueryValue [par1] not specified",
            "parameters":{"path":"/par1"},
            "type":"about:blank",
            "parameters":{"path":"/par1"},
            "status":400,
            "detail":"Required QueryValue [par1] not specified"
        }
*/
        Assertions.assertEquals(1, presentInText("\""+ regexp + "\":" , response));
    }

    @ParameterizedTest
    @CsvSource({
            "type",
            "title",
            "status",
            "detail"
    })
    public void test2(String regexp) {
        var response = "";
        try {
            client.toBlocking().retrieve("?par1=ab&par2=10&par3=a");
            Assertions.fail();

        } catch (HttpClientException e) {
            response = e.getMessage();
        }
/*
        {"type":"about:blank",
        "title":"Not Found",
        "status":404,
        "detail":"User not found",
        "type":"about:blank",
        "title":"Not Found",
        "status":404,
        "detail":"User not found"}
*/
        Assertions.assertEquals(1, presentInText("\""+ regexp + "\":" , response));
    }


    private int presentInText(String regexp, String text) {
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(text);
        int count = 0;
        while (m.find()) {
            count++;
        }
        return count;
    }
}

