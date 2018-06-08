package com.fun.pipeline.pipeline;

import com.fun.pipeline.domain.Operation;
import com.fun.pipeline.domain.UpstreamAdjacencyMatrix;
import org.assertj.core.util.Sets;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
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

    @Test
    public void shouldGenerateAPipelineForFourOperationsWithOneUpstreamDepAndNoDownstreamDep() throws Exception {

        Set<Operation> operations = Sets.newHashSet();

        Operation operation1 = new Operation(1, emptyList(), emptyList());
        Operation operation2 = new Operation(2, singletonList(operation1), emptyList());
        Operation operation3 = new Operation(3, singletonList(operation2), emptyList());
        Operation operation4 = new Operation(4, singletonList(operation3), emptyList());

        operations.add(operation4);
        operations.add(operation2);
        operations.add(operation1);
        operations.add(operation3);

        PipelineGenerator pipelineGenerator = new PipelineGenerator(operations);

        List<Operation> pipelineOfSortedUpstreamDependencies = pipelineGenerator.getPipelineOfSortedUpstreamDependencies();

        assertThat(pipelineOfSortedUpstreamDependencies).hasSize(4);
        assertThat(pipelineOfSortedUpstreamDependencies).containsSequence(operation4, operation3, operation2, operation1);

    }

    @Test
    public void shouldGenerateAPipelineForFourOperationsWithTwoUpstreamDepAndOneDownstreamDep() throws Exception {

        Set<Operation> operations = Sets.newHashSet();

        Operation operation1 = new Operation(1, emptyList(), emptyList());
        Operation operation2 = new Operation(2, singletonList(operation1), emptyList());
        Operation operation3 = new Operation(3, singletonList(operation2), emptyList());
        Operation operationRandom = new Operation( 5, emptyList(), emptyList());
        Operation operation4 = new Operation(4, Arrays.asList(operation3, operationRandom), emptyList());

        operations.add(operation4);
        operations.add(operation2);
        operations.add(operation1);
        operations.add(operationRandom);
        operations.add(operation3);

        PipelineGenerator pipelineGenerator = new PipelineGenerator(operations);

        List<Operation> pipelineOfSortedUpstreamDependencies = pipelineGenerator.getPipelineOfSortedUpstreamDependencies();

        assertThat(pipelineOfSortedUpstreamDependencies).hasSize(5);
        assertThat(pipelineOfSortedUpstreamDependencies).containsSequence(operationRandom, operation3, operation4, operation2, operation1);

    }

    @Test
    public void shouldCreateAUpstreamDepMatrix() throws Exception {

        Set<Operation> operations = Sets.newHashSet();

        Operation operation1 = new Operation(1, emptyList(), emptyList());
        Operation operation2 = new Operation(2, Arrays.asList(operation1), emptyList());
        Operation operation3 = new Operation(3, Arrays.asList(operation2, operation1), emptyList());
        operations.add(operation2);
        operations.add(operation1);
        operations.add(operation3);

        PipelineGenerator pipelineGenerator = new PipelineGenerator(operations);

        UpstreamAdjacencyMatrix upstreamAdjacencyMatrix = pipelineGenerator.updateUpstreamAdjacencyMatrix();

        //Upstream dependency -
        assertThat(upstreamAdjacencyMatrix.contains(2, 1));
        assertThat(upstreamAdjacencyMatrix.contains(3, 2));
        assertThat(upstreamAdjacencyMatrix.contains(3, 1));


    }
}