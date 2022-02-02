package chabernac.cdk.builder.stack;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public interface IStackBuilder {
    public Stack build( Construct scope, StackProps props );
}
