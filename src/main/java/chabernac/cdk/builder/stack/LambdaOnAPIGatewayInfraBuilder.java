package chabernac.cdk.builder.stack;

import java.util.HashSet;
import java.util.Set;
import chabernac.cdk.builder.CustomFunctionBuilder;
import chabernac.cdk.builder.CustomLambdaIntegrationBuilder;
import chabernac.cdk.builder.CustomRestAPIBuilder;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.lambda.IFunction;

public class LambdaOnAPIGatewayInfraBuilder implements IInfraBuilder<RestApi> {
    private final String              stackName;
    private String                    jarInTargetFolder;
    private String                    restAPIName;
    private String                    restAPIDescription;
    private final Set<LambdaFunction> functions = new HashSet<>();

    private Stack                     stack;
    private RestApi                   api;

    public Stack getStack() {
        return stack;
    }

    public RestApi getCreatedRestApi() {
        return api;
    }

    public Set<IFunction> getFunctions() {
        return new HashSet<>();
    }

    public LambdaOnAPIGatewayInfraBuilder( String stackName ) {
        super();
        this.stackName = stackName;
    }

    public LambdaOnAPIGatewayInfraBuilder setJarInTargetFolder( String jarInTargetFolder ) {
        this.jarInTargetFolder = jarInTargetFolder;
        return this;
    }

    public LambdaOnAPIGatewayInfraBuilder addFunction( LambdaFunction function ) {
        functions.add( function );
        return this;
    }

    @Override
    public RestApi build( Stack stack ) {
        RestApi api = new CustomRestAPIBuilder( stack, stackName )
            .restApiName( restAPIName )
            .description( restAPIDescription ).build();

        functions.stream().forEach( function -> createFunction( stack, api, function ) );

        return api;
    }

    private void createFunction( Stack stack, RestApi api, LambdaFunction function ) {
        api.getRoot()
            .resourceForPath( function.getRelativeEndPoint() )
            .addMethod( function.getHttpMethod(), CustomLambdaIntegrationBuilder.defaultIntegration( buildFunction( stack, function ) ).build() );
    }

    private IFunction buildFunction( Stack stack, LambdaFunction function ) {
        CustomFunctionBuilder builder = new CustomFunctionBuilder( stack, "Function" )
            .runtime( function.getRuntime() )
            .memorySize(function.getMemoryInMB())
            .jarInTargetFolder( jarInTargetFolder )
            .handler( function.getHandler() );
        function.getDecorators().stream().forEach( decorator -> decorator.decorateDuringBuild( builder ) );
        IFunction lambdaFunction = builder.build();
        function.getDecorators().stream().forEach( decorator -> decorator.decorateAfterBuild( lambdaFunction ) );

        return lambdaFunction;
    }

    public LambdaOnAPIGatewayInfraBuilder setRestAPIName( String restAPIName ) {
        this.restAPIName = restAPIName;
        return this;
    }

    public LambdaOnAPIGatewayInfraBuilder setRestAPIDescription( String restAPIDescription ) {
        this.restAPIDescription = restAPIDescription;
        return this;
    }
}
