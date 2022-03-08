## Sample Controller
[UserController.java](src/main/java/micronaut_problem_json_duplicate_fields/UserController.java)


## Failing Test to demonstrate this bug:
[ErrorDemonstrationTest.java](src/test/java/micronaut_problem_json_duplicate_fields/ErrorDemonstrationTest.java)

## Workaround
Toggle problem.stack-trace to true in [src/main/resources/application.yaml](src/main/resources/application.yml) 
```yaml
problem:
    stack-trace: false
```

