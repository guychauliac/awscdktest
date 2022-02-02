package chabernac.cdk.builder;

import java.util.HashMap;

public class MappingTemplate extends HashMap<String, String> {
    private static final long serialVersionUID = -5291996938703543845L;

    public MappingTemplate() {
        put( "application/json", "{ \"statusCode\": \"200\" }" );
    }

    public MappingTemplate addContextVariableMappedToValueInRequest( String keyInRequest, String contextVariable ) {
        put( keyInRequest, contextVariable );
        return this;
    }

}
