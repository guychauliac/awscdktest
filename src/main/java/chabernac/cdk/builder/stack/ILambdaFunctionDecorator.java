package chabernac.cdk.builder.stack;

import chabernac.cdk.builder.CustomFunctionBuilder;
import software.amazon.awscdk.services.lambda.Function;

public interface ILambdaFunctionDecorator {
    public void decorateDuringBuild(CustomFunctionBuilder builder);

    public void decorateAfterBuild(Function lambda);
}
