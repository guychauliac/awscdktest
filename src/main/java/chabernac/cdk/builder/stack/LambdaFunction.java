package chabernac.cdk.builder.stack;

import software.amazon.awscdk.services.lambda.Runtime;

public class LambdaFunction {
    private final String handler;
    private final String relativeEndPoint;
    private final String httpMethod;
    private Runtime runtime = Runtime.JAVA_8_CORRETTO;
    private ILambdaFunctionDecorator decorator = new NoLambdaDecoreator();

    public LambdaFunction(String handler, String relativeEndPoint, String httpMethod) {
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

    public LambdaFunction setRuntime(Runtime runtime) {
        this.runtime = runtime;
        return this;
    }

    public Runtime getRuntime() {
        return runtime;
    }

    public LambdaFunction setDecorator(ILambdaFunctionDecorator decorator) {
        this.decorator = decorator;
        return this;
    }

    public ILambdaFunctionDecorator getDecorator() {
        return decorator;
    }



}
