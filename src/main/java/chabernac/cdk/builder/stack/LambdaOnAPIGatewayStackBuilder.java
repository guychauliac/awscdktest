package chabernac.cdk.builder.stack;

import java.util.HashSet;
import java.util.Set;
import chabernac.cdk.builder.CustomFunctionBuilder;
import chabernac.cdk.builder.CustomLambdaIntegrationBuilder;
import chabernac.cdk.builder.CustomRestAPIBuilder;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.IFunction;
import software.constructs.Construct;

public class LambdaOnAPIGatewayStackBuilder implements IStackBuilder {
    private final String stackName;
    private String stackDescription;
    private String jarInTargetFolder;
    private String restAPIName;
    private String restAPIDescription;
    private final Set<LambdaFunction> functions = new HashSet<>();
    private Environment environment;

    private Stack stack;
    private RestApi api;

    public Stack getStack() {
        return stack;
    }

    public RestApi getCreatedRestApi() {
        return api;
    }

    public Set<IFunction> getFunctions() {
        return new HashSet<>();
    }

    public LambdaOnAPIGatewayStackBuilder(String stackName) {
        super();
        this.stackName = stackName;
    }

    public LambdaOnAPIGatewayStackBuilder setJarInTargetFolder(String jarInTargetFolder) {
        this.jarInTargetFolder = jarInTargetFolder;
        return this;
    }

    public LambdaOnAPIGatewayStackBuilder setStackDescription(String stackDescription) {
        this.stackDescription = stackDescription;
        return this;
    }

    public LambdaOnAPIGatewayStackBuilder addFunction(LambdaFunction function) {
        functions.add(function);
        return this;
    }

    @Override
    public Stack build(Construct scope) {
        Stack stack = Stack.Builder.create(scope, stackName)
                .stackName(stackName)
                .description(stackDescription)
                .env(environment)
                .build();
        RestApi api = new CustomRestAPIBuilder(stack, stackName)
                .restApiName(restAPIName)
                .description(restAPIDescription).build();

        functions.stream().forEach(function -> createFunction(stack, api, function));

        return stack;
    }

    private void createFunction(Stack stack, RestApi api, LambdaFunction function) {
        api.getRoot()
                .resourceForPath(function.getRelativeEndPoint())
                .addMethod(function.getHttpMethod(), CustomLambdaIntegrationBuilder.defaultIntegration(buildFunction(stack, function)).build());
    }

    private IFunction buildFunction(Stack stack, LambdaFunction function) {
        CustomFunctionBuilder builder = new CustomFunctionBuilder(stack, "Function")
                .runtime(function.getRuntime())
                .jarInTargetFolder(jarInTargetFolder)
                .handler(function.getHandler());
        function.getDecorator().decorateDuringBuild(builder);
        Function lambdaFunction = builder.build();
        function.getDecorator().decorateAfterBuild(lambdaFunction);
        return lambdaFunction;
    }

    public LambdaOnAPIGatewayStackBuilder setRestAPIName(String restAPIName) {
        this.restAPIName = restAPIName;
        return this;
    }

    public LambdaOnAPIGatewayStackBuilder setRestAPIDescription(String restAPIDescription) {
        this.restAPIDescription = restAPIDescription;
        return this;
    }

    public LambdaOnAPIGatewayStackBuilder setEnvironment(Environment environment) {
        this.environment = environment;
        return this;
    }

}
