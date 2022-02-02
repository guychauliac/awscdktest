package chabernac.cdk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigateway.LambdaIntegration;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.LayerVersion;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.s3.Bucket;
import software.constructs.Construct;

public class LambdaOnAPIGatewayStack implements IStackBuilder {
    private final String stackName;
    private final String jarInTargetFolder;
    private final Set<LambdaFunction> functions = new HashSet<>();

    public LambdaOnAPIGatewayStack(String stackName, String jarInTargetFolder) {
        super();
        this.stackName = stackName;
        this.jarInTargetFolder = jarInTargetFolder;
    }

    public LambdaOnAPIGatewayStack addFunction(LambdaFunction function) {
        functions.add(function);
        return this;
    }


    @Override
    public Stack build(Construct scope, String id, StackProps props) {
        BuilderFactory builderFactory = new BuilderFactory();

        Bucket bucket = builderFactory.getDefaultBucketBuilder("cdk.lambda.test").build();

        String jar = "target/" + jarInTargetFolder;

        Function handler = builderFactory.getDefaultFunctionBuilder(jar, "chabernac.cdk.Handler", Runtime.JAVA_8_CORRETTO)
                .environment(new HashMap<String, String>() {
                    {
                        put("BUCKET", bucket.getBucketName());
                    }
                }).build();

        bucket.grantReadWrite(handler);

        RestApi api = builderFactory.getDefaultRestAPIBuilder("Lambda test service", "CDK Test API")
                .build();
        LambdaIntegration testHandlerIntegration = LambdaIntegration.Builder.create(handler)
                .requestTemplates(new HashMap<String, String>() {
                    {
                        put("application/json", "{ \"statusCode\": \"200\" }");
                    }
                }).build();
        api.getRoot().addMethod("GET", testHandlerIntegration);

        return stack;
    }
}
