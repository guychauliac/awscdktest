package chabernac.cdk.builder.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chabernac.cdk.builder.decorator.ILambdaFunctionDecorator;
import software.amazon.awscdk.services.lambda.Runtime;

public class LambdaFunction {
    private final String                         handler;
    private final String                         relativeEndPoint;
    private final String                         httpMethod;
    private Runtime                              runtime    = Runtime.JAVA_8_CORRETTO;
    private final List<ILambdaFunctionDecorator> decorators = new ArrayList<>();
    private Number                               memoryInMB = 256;

    public LambdaFunction( String handler, String relativeEndPoint, String httpMethod ) {
        super();
        this.handler = handler;
        this.relativeEndPoint = relativeEndPoint;
        this.httpMethod = httpMethod;
    }

    public String getHandler() {
        return handler;
    }

    public String getRelativeEndPoint() {
        return relativeEndPoint;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public LambdaFunction setRuntime( Runtime runtime ) {
        this.runtime = runtime;
        return this;
    }

    public Runtime getRuntime() {
        return runtime;
    }

    public LambdaFunction addDecorator( ILambdaFunctionDecorator decorator ) {
        this.decorators.add( decorator );
        return this;
    }

    public List<ILambdaFunctionDecorator> getDecorators() {
        return Collections.unmodifiableList( decorators );
    }

    public LambdaFunction setMemoryInMB( Number memoryInMB ) {
        this.memoryInMB = memoryInMB;
        return this;
    }

    public Number getMemoryInMB() {
        return memoryInMB;
    }

}
