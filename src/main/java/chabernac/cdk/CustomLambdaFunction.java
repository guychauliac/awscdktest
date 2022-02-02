package chabernac.cdk;

import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.jsii.JsiiObjectRef;
import software.constructs.Construct;

public class CustomLambdaFunction extends Function{

    public CustomLambdaFunction(@NotNull Construct scope, @NotNull String id, @NotNull FunctionProps props) {
        super(scope, id, props);
        // TODO Auto-generated constructor stub
    }

    public CustomLambdaFunction(InitializationMode initializationMode) {
        super(initializationMode);
        // TODO Auto-generated constructor stub
    }

    public CustomLambdaFunction(JsiiObjectRef objRef) {
        super(objRef);
        // TODO Auto-generated constructor stub
    }
    

}
