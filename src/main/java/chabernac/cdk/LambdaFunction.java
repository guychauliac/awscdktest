package chabernac.cdk;

public class LambdaFunction {
    private final String handler;
    private final String relativeEndPoint;

    public LambdaFunction(String handler, String relativeEndPoint) {
        super();
        this.handler = handler;
        this.relativeEndPoint = relativeEndPoint;
    }

    public String getHandler() {
        return handler;
    }

    public String getRelativeEndPoint() {
        return relativeEndPoint;
    }


}
