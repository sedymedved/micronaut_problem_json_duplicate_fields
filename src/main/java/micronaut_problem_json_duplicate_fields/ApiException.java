package micronaut_problem_json_duplicate_fields;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.http.HttpStatus;
import io.micronaut.problem.HttpStatusType;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.ThrowableProblem;

public class ApiException extends AbstractThrowableProblem {

    public ApiException(HttpStatus status, String detail) {
        super(null, status.getReason(), new HttpStatusType(status), detail);
    }

    @JsonIgnore
    @Override
    public ThrowableProblem getCause() {
        throw new IllegalStateException("Not yet implemented.");
    }

}
