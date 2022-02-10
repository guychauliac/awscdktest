package chabernac.cdk.builder.stack;

import chabernac.cdk.builder.CustomFunctionBuilder;
import chabernac.cdk.builder.decorator.ILambdaFunctionDecorator;
import software.amazon.awscdk.services.lambda.IFunction;

public class NoLambdaDecoreator implements ILambdaFunctionDecorator {

    @Override
    public void decorateDuringBuild(CustomFunctionBuilder builder) {}

    @Override
    public void decorateAfterBuild(IFunction lambda) {}

}
