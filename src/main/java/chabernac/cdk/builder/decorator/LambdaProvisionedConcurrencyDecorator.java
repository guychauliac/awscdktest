package chabernac.cdk.builder.decorator;

import chabernac.cdk.builder.CustomFunctionBuilder;
import software.amazon.awscdk.services.lambda.IFunction;

public class LambdaProvisionedConcurrencyDecorator implements ILambdaFunctionDecorator {
    private final int numberOfConcurrentInstances;

    public LambdaProvisionedConcurrencyDecorator( int numberOfConcurrentInstances ) {
        super();
        this.numberOfConcurrentInstances = numberOfConcurrentInstances;
    }

    @Override
    public void decorateDuringBuild( CustomFunctionBuilder builder ) {
        builder.getVersionPropsBuilder().provisionedConcurrentExecutions( numberOfConcurrentInstances );
    }

    @Override
    public void decorateAfterBuild( IFunction lambda ) {
    }

}
