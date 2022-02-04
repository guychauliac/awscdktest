package chabernac.cdk.builder.stack;

import chabernac.cdk.builder.CustomFunctionBuilder;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.s3.Bucket;

public class AccessToBucketLambdaDecorator implements ILambdaFunctionDecorator {
    private final Bucket bucket;

    public AccessToBucketLambdaDecorator(Bucket bucket) {
        super();
        this.bucket = bucket;
    }

    @Override
    public void decorateDuringBuild(CustomFunctionBuilder builder) {
        builder.addEnvironmentVariable("BUCKET", bucket.getBucketName());

    }

    @Override
    public void decorateAfterBuild(Function lambda) {
        bucket.grantReadWrite(lambda);
    }

}
