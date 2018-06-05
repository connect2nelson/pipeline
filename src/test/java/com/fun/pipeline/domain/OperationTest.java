package com.fun.pipeline.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class OperationTest {


    @Test
    public void shouldExecuteTheOperationAndReturnAString() throws Exception {

        List<Operation> upstreamDep = Arrays.asList(
                new Operation(2, Collections.emptyList(), Collections.EMPTY_LIST),
                new Operation(3, Collections.emptyList(), Collections.EMPTY_LIST)
        );
        Operation operation1 = new Operation(1, upstreamDep, Collections.EMPTY_LIST);

        String execute = operation1.execute();

        assertThat(execute).isEqualTo("Executing operation " + "1");

    }
}