package chabernac.cdk.builder.stack;

import software.amazon.awscdk.Resource;
import software.amazon.awscdk.Stack;

public interface IInfraBuilder<T extends Resource> {

    /**
     * The infra builder can create multiple resources but should return the main resource that was created from the build method
     * 
     * @param stack
     * @return
     */
    public T build( Stack stack );
}
