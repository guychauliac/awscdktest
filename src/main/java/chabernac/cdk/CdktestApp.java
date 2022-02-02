package chabernac.cdk;

import chabernac.cdk.builder.stack.LambdaFunction;
import chabernac.cdk.builder.stack.LambdaOnAPIGatewayStackBuilder;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class CdktestApp {
    public static void main(final String[] args) {
        App app = new App();

        new LambdaOnAPIGatewayStackBuilder("LambdaAPIGateway", "cdktest-jar-with-dependencies.jar")
                .setRestAPIDescription("API Description")
                .setRestAPIId("Rest API Id")
                .setRestAPIName("API Name")
                .addFunction(new LambdaFunction("chabernac.businesslogic.handler.Handler", "/handler/test", "GET"))
                .build(app, getStackPropertiesForAccount("829867751173"));

        app.synth();
    }

    private static StackProps getStackPropertiesForAccount(String string) {
        return StackProps.builder().env(
                Environment.builder()
                        .account("829867751173")
                        .region("eu-central-1")
                        .build())
                .build();
    }

}
