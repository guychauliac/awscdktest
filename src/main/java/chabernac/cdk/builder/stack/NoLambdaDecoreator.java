package chabernac.cdk.builder.stack;

import chabernac.cdk.builder.CustomFunctionBuilder;
import software.amazon.awscdk.services.lambda.Function;

public class NoLambdaDecoreator implements ILambdaFunctionDecorator {

    @Override
    public void decorateDuringBuild(CustomFunctionBuilder builder) {}

    @Override
    public void decorateAfterBuild(Function lambda) {}

}
