package com.fun.pipeline.pipeline;

import com.fun.pipeline.domain.Operation;
import org.assertj.core.util.Sets;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.Collections.*;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class PipelineGeneratorTest {

    @Test
    public void shouldGenerateAPipelineForAnOperationWithNoUpstreamDepAnDownstreamDep() throws Exception {

        Set<Operation> operations = Sets.newHashSet();

        Operation operation1 = new Operation(1, emptyList(), emptyList());
        operations.add(operation1);

        PipelineGenerator pipelineGenerator = new PipelineGenerator(operations);

        List<Operation> pipelineOfSortedUpstreamDependencies = pipelineGenerator.getPipelineOfSortedUpstreamDependencies();

        assertThat(pipelineOfSortedUpstreamDependencies).hasSize(1);
        assertThat(pipelineOfSortedUpstreamDependencies).contains(operation1);

    }

    @Test
    public void shouldGenerateAPipelineForTwoOperationWithNoUpstreamDepAnDownstreamDep() throws Exception {

        Set<Operation> operations = Sets.newHashSet();

        Operation operation1 = new Operation(1, emptyList(), emptyList());
        operations.add(operation1);
        Operation operation2 = new Operation(2, emptyList(), emptyList());
        operations.add(operation2);

        PipelineGenerator pipelineGenerator = new PipelineGenerator(operations);

        List<Operation> pipelineOfSortedUpstreamDependencies = pipelineGenerator.getPipelineOfSortedUpstreamDependencies();

        assertThat(pipelineOfSortedUpstreamDependencies).hasSize(2);
        assertThat(pipelineOfSortedUpstreamDependencies).contains(operation1, operation2);

    }
}