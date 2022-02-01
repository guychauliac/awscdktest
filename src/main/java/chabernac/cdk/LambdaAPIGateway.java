package chabernac.cdk;

import java.util.HashMap;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigateway.LambdaIntegration;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.s3.Bucket;
import software.constructs.Construct;

public class LambdaAPIGateway extends Stack {
    public LambdaAPIGateway(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public LambdaAPIGateway(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Bucket bucket = new Bucket(this, "cdk.lambda.test");

        Function handler = Function.Builder.create(this, "WidgetHandler")
                .runtime(software.amazon.awscdk.services.lambda.Runtime.JAVA_8_CORRETTO)
                .code(Code.fromAsset("C:\\Data\\git\\cdktest\\target\\cdktest-jar-with-dependencies.jar"))
                .handler("chabernac.cdk.Handler")
                .environment(new HashMap<String, String>() {
                    {
                        put("BUCKET", bucket.getBucketName());
                    }
                }).build();

        bucket.grantReadWrite(handler);

        RestApi api = RestApi.Builder.create(this, "LambdaAPI")
                .restApiName("Lambda test service").description("CDK Test API")
                .build();

        LambdaIntegration getWidgetsIntegration = LambdaIntegration.Builder.create(handler)
                .requestTemplates(new HashMap<String, String>() {
                    {
                        put("application/json", "{ \"statusCode\": \"200\" }");
                    }
                }).build();

        api.getRoot().addMethod("GET", getWidgetsIntegration);
    }
}
