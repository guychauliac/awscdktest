package chabernac.cdk.builder.stack;

import java.util.HashSet;
import java.util.Set;

import chabernac.cdk.builder.CustomFunctionBuilder;
import chabernac.cdk.builder.CustomLambdaIntegrationBuilder;
import chabernac.cdk.builder.CustomRestAPIBuilder;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.lambda.IFunction;
import software.constructs.Construct;

public class LambdaOnAPIGatewayStackBuilder implements IStackBuilder {
    private final String              stackName;
    private final String              jarInTargetFolder;
    private String                    restAPIId;
    private String                    restAPIName;
    private String                    restAPIDescription;
    private final Set<LambdaFunction> functions = new HashSet<>();

    public LambdaOnAPIGatewayStackBuilder( String stackName, String jarInTargetFolder ) {
        super();
        this.stackName = stackName;
        this.jarInTargetFolder = jarInTargetFolder;
    }

    public LambdaOnAPIGatewayStackBuilder addFunction( LambdaFunction function ) {
        functions.add( function );
        return this;
    }

    @Override
    public Stack build( Construct scope, StackProps props ) {
        Stack stack = Stack.Builder.create( scope, stackName ).stackName( stackName ).build();
        RestApi api = new CustomRestAPIBuilder( stack, restAPIId ).restApiName( restAPIName ).description( restAPIDescription ).build();

        functions.stream().forEach( function -> createFunction( stack, api, function ) );

        return stack;
    }

    private void createFunction( Stack stack, RestApi api, LambdaFunction function ) {
        api.getRoot()
            .resourceForPath( function.getRelativeEndPoint() )
            .addMethod( function.getHttpMethod(), CustomLambdaIntegrationBuilder.defaultIntegration( buildFunction( stack, function ) ).build() );
    }

    private IFunction buildFunction( Stack stack, LambdaFunction function ) {
        return new CustomFunctionBuilder( stack, "Function" )
            .runtime( function.getRuntime() )
            .jarInTargetFolder( jarInTargetFolder )
            .handler( function.getHandler() )
            .build();
    }

    public LambdaOnAPIGatewayStackBuilder setRestAPIId( String restAPIId ) {
        this.restAPIId = restAPIId;
        return this;
    }

    public LambdaOnAPIGatewayStackBuilder setRestAPIName( String restAPIName ) {
        this.restAPIName = restAPIName;
        return this;
    }

    public LambdaOnAPIGatewayStackBuilder setRestAPIDescription( String restAPIDescription ) {
        this.restAPIDescription = restAPIDescription;
        return this;
    }

}
