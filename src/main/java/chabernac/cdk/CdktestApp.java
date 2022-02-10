package chabernac.cdk;

import chabernac.cdk.builder.decorator.AccessToBucketLambdaDecorator;
import chabernac.cdk.builder.decorator.LambdaProvisionedConcurrencyDecorator;
import chabernac.cdk.builder.stack.LambdaFunction;
import chabernac.cdk.builder.stack.LambdaOnAPIGatewayInfraBuilder;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.s3.Bucket;

public class CdktestApp {
    public static void main( final String[] args ) {
        App app = new App();

        Stack stack = Stack.Builder.create( app, "Lambda Api GTW Stack id" )
            .stackName( "LambdaAPIGateway" )
            .description( "Lambda on Api Gateway - description" )
            .env(
                Environment.builder()
                    .account( "829867751173" )
                    .region( "eu-central-1" )
                    .build() )
            .build();

        Bucket bucket = new Bucket( stack, "cdk.lambda.test" );

        new LambdaOnAPIGatewayInfraBuilder( "LambdaAPIGateway" )
            .setRestAPIDescription( "API Description" )
            .setRestAPIName( "API Name" )
            .setJarInTargetFolder( "cdktest-jar-with-dependencies.jar" )
            .addFunction( new LambdaFunction( "chabernac.businesslogic.handler.Handler", "/handler/test", "GET" )
                .addDecorator( new AccessToBucketLambdaDecorator( bucket ) )
                .addDecorator( new LambdaProvisionedConcurrencyDecorator( 1 ) ))
            .build( stack );

        app.synth();
    }
}
