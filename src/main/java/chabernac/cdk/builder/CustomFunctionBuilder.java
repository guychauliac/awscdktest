package chabernac.cdk.builder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.services.codeguruprofiler.IProfilingGroup;
import software.amazon.awscdk.services.ec2.ISecurityGroup;
import software.amazon.awscdk.services.ec2.IVpc;
import software.amazon.awscdk.services.ec2.SubnetSelection;
import software.amazon.awscdk.services.iam.IRole;
import software.amazon.awscdk.services.iam.PolicyStatement;
import software.amazon.awscdk.services.kms.IKey;
import software.amazon.awscdk.services.lambda.Architecture;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.FileSystem;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.ICodeSigningConfig;
import software.amazon.awscdk.services.lambda.IDestination;
import software.amazon.awscdk.services.lambda.IEventSource;
import software.amazon.awscdk.services.lambda.ILayerVersion;
import software.amazon.awscdk.services.lambda.LambdaInsightsVersion;
import software.amazon.awscdk.services.lambda.LayerVersion;
import software.amazon.awscdk.services.lambda.LogRetentionRetryOptions;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.lambda.Tracing;
import software.amazon.awscdk.services.lambda.VersionOptions;
import software.amazon.awscdk.services.logs.RetentionDays;
import software.amazon.awscdk.services.sqs.IQueue;
import software.constructs.Construct;

public class CustomFunctionBuilder {

    private final Function.Builder    builder;
    private final Construct           scope;
    private Runtime                   runtime;
    private final Map<String, String> environmentVariables = new HashMap<String, String>();

    public CustomFunctionBuilder( Construct scope, String id ) {
        this.scope = scope;
        builder = Function.Builder.create( scope, id );
    }

    public CustomFunctionBuilder jarInTargetFolder( String jar ) {
        if ( runtime == null ) {
            throw new IllegalArgumentException( "Need to set a runtime first" );
        }

        String jarLocation = "target/" + jar;

        builder.code( Code.fromAsset( jarLocation ) )
            .layers(
                Arrays.asList(
                    LayerVersion.Builder.create( scope, jar + "Layer" )
                        .code( Code.fromAsset( jarLocation ) )
                        .compatibleRuntimes( Arrays.asList( runtime ) )
                        .build() ) );
        return this;
    }

    public CustomFunctionBuilder addEnvironmentVariable( String Key, String value ) {
        builder.environment( environmentVariables );
        environmentVariables.put( Key, value );
        return this;
    }

    public int hashCode() {
        return builder.hashCode();
    }

    public boolean equals( Object obj ) {
        return builder.equals( obj );
    }

    public CustomFunctionBuilder allowAllOutbound( Boolean allowAllOutbound ) {
        builder.allowAllOutbound( allowAllOutbound );
        return this;
    }

    public CustomFunctionBuilder allowPublicSubnet( Boolean allowPublicSubnet ) {
        builder.allowPublicSubnet( allowPublicSubnet );
        return this;
    }

    public CustomFunctionBuilder architecture( Architecture architecture ) {
        builder.architecture( architecture );
        return this;
    }

    public CustomFunctionBuilder codeSigningConfig( ICodeSigningConfig codeSigningConfig ) {
        builder.codeSigningConfig( codeSigningConfig );
        return this;
    }

    public CustomFunctionBuilder currentVersionOptions( VersionOptions currentVersionOptions ) {
        builder.currentVersionOptions( currentVersionOptions );
        return this;
    }

    public CustomFunctionBuilder deadLetterQueueEnabled( Boolean deadLetterQueueEnabled ) {
        builder.deadLetterQueueEnabled( deadLetterQueueEnabled );
        return this;
    }

    public CustomFunctionBuilder description( String description ) {
        builder.description( description );
        return this;
    }

    public CustomFunctionBuilder environment( Map<String, String> environment ) {
        builder.environment( environment );
        return this;
    }

    public CustomFunctionBuilder environmentEncryption( IKey environmentEncryption ) {
        builder.environmentEncryption( environmentEncryption );
        return this;
    }

    public CustomFunctionBuilder events( List<? extends IEventSource> events ) {
        builder.events( events );
        return this;
    }

    public CustomFunctionBuilder filesystem( FileSystem filesystem ) {
        builder.filesystem( filesystem );
        return this;
    }

    public CustomFunctionBuilder functionName( String functionName ) {
        builder.functionName( functionName );
        return this;
    }

    public CustomFunctionBuilder initialPolicy( List<? extends PolicyStatement> initialPolicy ) {
        builder.initialPolicy( initialPolicy );
        return this;
    }

    public CustomFunctionBuilder insightsVersion( LambdaInsightsVersion insightsVersion ) {
        builder.insightsVersion( insightsVersion );
        return this;
    }

    public CustomFunctionBuilder code( Code code ) {
        builder.code( code );
        return this;
    }

    public CustomFunctionBuilder handler( String handler ) {
        builder.handler( handler );
        return this;
    }

    public Function build() {
        return builder.build();
    }

    public CustomFunctionBuilder deadLetterQueue( IQueue deadLetterQueue ) {
        builder.deadLetterQueue( deadLetterQueue );
        return this;
    }

    public String toString() {
        return builder.toString();
    }

    public CustomFunctionBuilder maxEventAge( Duration maxEventAge ) {
        builder.maxEventAge( maxEventAge );
        return this;
    }

    public CustomFunctionBuilder onFailure( IDestination onFailure ) {
        builder.onFailure( onFailure );
        return this;
    }

    public CustomFunctionBuilder onSuccess( IDestination onSuccess ) {
        builder.onSuccess( onSuccess );
        return this;
    }

    public CustomFunctionBuilder retryAttempts( Number retryAttempts ) {
        builder.retryAttempts( retryAttempts );
        return this;
    }

    public CustomFunctionBuilder layers( List<? extends ILayerVersion> layers ) {
        builder.layers( layers );
        return this;
    }

    public CustomFunctionBuilder logRetention( RetentionDays logRetention ) {
        builder.logRetention( logRetention );
        return this;
    }

    public CustomFunctionBuilder logRetentionRetryOptions( LogRetentionRetryOptions logRetentionRetryOptions ) {
        builder.logRetentionRetryOptions( logRetentionRetryOptions );
        return this;
    }

    public CustomFunctionBuilder logRetentionRole( IRole logRetentionRole ) {
        builder.logRetentionRole( logRetentionRole );
        return this;
    }

    public CustomFunctionBuilder memorySize( Number memorySize ) {
        builder.memorySize( memorySize );
        return this;
    }

    public CustomFunctionBuilder profiling( Boolean profiling ) {
        builder.profiling( profiling );
        return this; 
    }

    public CustomFunctionBuilder profilingGroup( IProfilingGroup profilingGroup ) {
        builder.profilingGroup( profilingGroup );
        return this;
    }

    public CustomFunctionBuilder reservedConcurrentExecutions( Number reservedConcurrentExecutions ) {
        builder.reservedConcurrentExecutions( reservedConcurrentExecutions );
        return this;
    }

    public CustomFunctionBuilder role( IRole role ) {
        builder.role( role );
        return this;
    }

    public CustomFunctionBuilder securityGroups( List<? extends ISecurityGroup> securityGroups ) {
        builder.securityGroups( securityGroups );
        return this;
    }

    public CustomFunctionBuilder timeout( Duration timeout ) {
        builder.timeout( timeout );
        return this;
    }

    public CustomFunctionBuilder tracing( Tracing tracing ) {
        builder.tracing( tracing );
        return this;
    }

    public CustomFunctionBuilder vpc( IVpc vpc ) {
        builder.vpc( vpc );
        return this;
    }

    public CustomFunctionBuilder vpcSubnets( SubnetSelection vpcSubnets ) {
        builder.vpcSubnets( vpcSubnets );
        return this;
    }

    public CustomFunctionBuilder runtime( Runtime runtime ) {
        this.runtime = runtime;
        builder.runtime( runtime );
        return this;
    }

}
