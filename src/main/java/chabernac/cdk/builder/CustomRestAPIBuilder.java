package chabernac.cdk.builder;

import java.util.List;
import java.util.Map;

import software.amazon.awscdk.services.apigateway.ApiKeySourceType;
import software.amazon.awscdk.services.apigateway.CorsOptions;
import software.amazon.awscdk.services.apigateway.DomainNameOptions;
import software.amazon.awscdk.services.apigateway.EndpointConfiguration;
import software.amazon.awscdk.services.apigateway.EndpointType;
import software.amazon.awscdk.services.apigateway.IRestApi;
import software.amazon.awscdk.services.apigateway.Integration;
import software.amazon.awscdk.services.apigateway.MethodOptions;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.apigateway.StageOptions;
import software.amazon.awscdk.services.iam.PolicyDocument;
import software.constructs.Construct;

public class CustomRestAPIBuilder {
    private RestApi.Builder builder;

    public CustomRestAPIBuilder( Construct scope, String id ) {
        builder = RestApi.Builder.create( scope, id );
    }

    public int hashCode() {
        return builder.hashCode();
    }

    public boolean equals( Object obj ) {
        return builder.equals( obj );
    }

    public CustomRestAPIBuilder defaultCorsPreflightOptions( CorsOptions defaultCorsPreflightOptions ) {
        builder.defaultCorsPreflightOptions( defaultCorsPreflightOptions );
        return this;
    }

    public String toString() {
        return builder.toString();
    }

    public CustomRestAPIBuilder defaultIntegration( Integration defaultIntegration ) {
        builder.defaultIntegration( defaultIntegration );
        return this;
    }

    public CustomRestAPIBuilder defaultMethodOptions( MethodOptions defaultMethodOptions ) {
        builder.defaultMethodOptions( defaultMethodOptions );
        return this;
    }

    public CustomRestAPIBuilder cloudWatchRole( Boolean cloudWatchRole ) {
        builder.cloudWatchRole( cloudWatchRole );
        return this;
    }

    public CustomRestAPIBuilder deploy( Boolean deploy ) {
        builder.deploy( deploy );
        return this;
    }

    public CustomRestAPIBuilder deployOptions( StageOptions deployOptions ) {
        builder.deployOptions( deployOptions );
        return this;
    }

    public CustomRestAPIBuilder disableExecuteApiEndpoint( Boolean disableExecuteApiEndpoint ) {
        builder.disableExecuteApiEndpoint( disableExecuteApiEndpoint );
        return this;
    }

    public CustomRestAPIBuilder domainName( DomainNameOptions domainName ) {
        builder.domainName( domainName );
        return this;
    }

    public CustomRestAPIBuilder endpointExportName( String endpointExportName ) {
        builder.endpointExportName( endpointExportName );
        return this;
    }

    public CustomRestAPIBuilder endpointTypes( List<? extends EndpointType> endpointTypes ) {
        builder.endpointTypes( endpointTypes );
        return this;
    }

    public CustomRestAPIBuilder failOnWarnings( Boolean failOnWarnings ) {
        builder.failOnWarnings( failOnWarnings );
        return this;
    }

    public CustomRestAPIBuilder parameters( Map<String, String> parameters ) {
        builder.parameters( parameters );
        return this;
    }

    public CustomRestAPIBuilder policy( PolicyDocument policy ) {
        builder.policy( policy );
        return this;
    }

    public CustomRestAPIBuilder restApiName( String restApiName ) {
        builder.restApiName( restApiName );
        return this;
    }

    public CustomRestAPIBuilder retainDeployments( Boolean retainDeployments ) {
        builder.retainDeployments( retainDeployments );
        return this;
    }

    public CustomRestAPIBuilder apiKeySourceType( ApiKeySourceType apiKeySourceType ) {
        builder.apiKeySourceType( apiKeySourceType );
        return this;
    }

    public CustomRestAPIBuilder binaryMediaTypes( List<String> binaryMediaTypes ) {
        builder.binaryMediaTypes( binaryMediaTypes );
        return this;
    }

    public CustomRestAPIBuilder cloneFrom( IRestApi cloneFrom ) {
        builder.cloneFrom( cloneFrom );
        return this;
    }

    public CustomRestAPIBuilder description( String description ) {
        builder.description( description );
        return this;
    }

    public CustomRestAPIBuilder endpointConfiguration( EndpointConfiguration endpointConfiguration ) {
        builder.endpointConfiguration( endpointConfiguration );
        return this;
    }

    public CustomRestAPIBuilder minimumCompressionSize( Number minimumCompressionSize ) {
        builder.minimumCompressionSize( minimumCompressionSize );
        return this;
    }

    public RestApi build() {
        return builder.build();
    }

}
