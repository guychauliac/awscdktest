package chabernac.cdk;

import java.util.Arrays;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.LayerVersion;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.s3.Bucket;

public class BuilderFactory {
    private final Stack stack;

    public BuilderFactory() {
        this(new Stack());
    }

    public BuilderFactory(Stack stack) {
        super();
        this.stack = stack;
    }

    public Function.Builder getDefaultFunctionBuilder(String jar, String handler, Runtime javaRuntime) {
        return Function.Builder.create(stack, "CDKTestHandler")
                .runtime(javaRuntime)
                .code(Code.fromAsset(jar))
                .layers(Arrays.asList(LayerVersion.Builder.create(stack, stack.getStackName() + "Layer")
                        .code(Code.fromAsset(jar))
                        .compatibleRuntimes(Arrays.asList(javaRuntime))
                        .build()))
                .handler(handler);

    }

    public Bucket.Builder getDefaultBucketBuilder(String bucketName) {
        return Bucket.Builder.create(stack, bucketName).bucketName(bucketName);
    }

    public RestApi.Builder getDefaultRestAPIBuilder(String apiName, String description) {
        return RestApi.Builder.create(stack, apiName)
                .restApiName(apiName).description(description);
    }

    public Stack getStack() {
        return stack;
    }


}
