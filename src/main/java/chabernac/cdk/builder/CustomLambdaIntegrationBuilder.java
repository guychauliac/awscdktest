package chabernac.cdk.builder;

import java.util.List;
import java.util.Map;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.services.apigateway.ConnectionType;
import software.amazon.awscdk.services.apigateway.ContentHandling;
import software.amazon.awscdk.services.apigateway.IVpcLink;
import software.amazon.awscdk.services.apigateway.IntegrationResponse;
import software.amazon.awscdk.services.apigateway.LambdaIntegration;
import software.amazon.awscdk.services.apigateway.PassthroughBehavior;
import software.amazon.awscdk.services.iam.IRole;
import software.amazon.awscdk.services.lambda.IFunction;

public class CustomLambdaIntegrationBuilder {
    private final LambdaIntegration.Builder builder;

    public CustomLambdaIntegrationBuilder( IFunction handler ) {
        builder = LambdaIntegration.Builder.create( handler );
    }
    
    public static CustomLambdaIntegrationBuilder defaultIntegration(IFunction handler) {
        return new CustomLambdaIntegrationBuilder( handler ).requestTemplates( new MappingTemplate() );
    }

    public int hashCode() {
        return builder.hashCode();
    }

    public CustomLambdaIntegrationBuilder cacheKeyParameters( List<String> cacheKeyParameters ) {
        builder.cacheKeyParameters( cacheKeyParameters );
        return this;
    }

    public boolean equals( Object obj ) {
        return builder.equals( obj );
    }

    public CustomLambdaIntegrationBuilder cacheNamespace( String cacheNamespace ) {
        builder.cacheNamespace( cacheNamespace );
        return this;
    }

    public CustomLambdaIntegrationBuilder connectionType( ConnectionType connectionType ) {
        builder.connectionType( connectionType );
        return this;
    }

    public CustomLambdaIntegrationBuilder contentHandling( ContentHandling contentHandling ) {
        builder.contentHandling( contentHandling );
        return this;
    }

    public CustomLambdaIntegrationBuilder credentialsPassthrough( Boolean credentialsPassthrough ) {
        builder.credentialsPassthrough( credentialsPassthrough );
        return this;
    }

    public CustomLambdaIntegrationBuilder credentialsRole( IRole credentialsRole ) {
        builder.credentialsRole( credentialsRole );
        return this;
    }

    public CustomLambdaIntegrationBuilder integrationResponses( List<? extends IntegrationResponse> integrationResponses ) {
        builder.integrationResponses( integrationResponses );
        return this;
    }

    public CustomLambdaIntegrationBuilder passthroughBehavior( PassthroughBehavior passthroughBehavior ) {
        builder.passthroughBehavior( passthroughBehavior );
        return this;
    }

    public CustomLambdaIntegrationBuilder requestParameters( Map<String, String> requestParameters ) {
        builder.requestParameters( requestParameters );
        return this;
    }

    public String toString() {
        return builder.toString();
    }

    public CustomLambdaIntegrationBuilder requestTemplates( Map<String, String> requestTemplates ) {
        builder.requestTemplates( requestTemplates );
        return this;
    }

    public CustomLambdaIntegrationBuilder timeout( Duration timeout ) {
        builder.timeout( timeout );
        return this;
    }

    public CustomLambdaIntegrationBuilder vpcLink( IVpcLink vpcLink ) {
        builder.vpcLink( vpcLink );
        return this;
    }

    public CustomLambdaIntegrationBuilder allowTestInvoke( Boolean allowTestInvoke ) {
        builder.allowTestInvoke( allowTestInvoke );
        return this;
    }

    public CustomLambdaIntegrationBuilder proxy( Boolean proxy ) {
        builder.proxy( proxy );
        return this;
    }

    public LambdaIntegration build() {
        return builder.build();
    }

}
