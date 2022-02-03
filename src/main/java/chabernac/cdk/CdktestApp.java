package chabernac.cdk;

import chabernac.cdk.builder.stack.LambdaFunction;
import chabernac.cdk.builder.stack.LambdaOnAPIGatewayStackBuilder;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;

public class CdktestApp {
    public static void main(final String[] args) {
        App app = new App();

        new LambdaOnAPIGatewayStackBuilder("LambdaAPIGateway")
                .setStackDescription("Lambda on Api Gateway")
                .setRestAPIDescription("API Description")
                .setRestAPIName("API Name")
                .setJarInTargetFolder("cdktest-jar-with-dependencies.jar")
                .setEnvironment(Environment.builder()
                        .account("829867751173")
                        .region("eu-central-1")
                        .build())
                .addFunction(new LambdaFunction("chabernac.businesslogic.handler.Handler", "/handler/test", "GET"))
                .build(app);

        app.synth();
    }
}
