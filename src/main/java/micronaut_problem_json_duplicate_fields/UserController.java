package micronaut_problem_json_duplicate_fields;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Controller("/users")
public class UserController {

    @Get
    public String getUser(
            @Size(min = 2, max = 3) @QueryValue String par1,
            @Min(10) @Max(100) @QueryValue int par2,
            @Pattern(regexp = "[a-z]+") @QueryValue String par3
    ) {
        throw new ApiException(HttpStatus.NOT_FOUND, "User not found");
    }
}
