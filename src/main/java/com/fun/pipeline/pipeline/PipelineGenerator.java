package com.fun.pipeline.pipeline;

import com.fun.pipeline.domain.Operation;
import com.fun.pipeline.domain.UpstreamAdjacencyMatrix;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PipelineGenerator {

    private UpstreamAdjacencyMatrix upstreamAdjacencyMatrix;

    private Set<Operation> operations;


    public PipelineGenerator(Set<Operation> operations) {
        this.operations = operations;
    }


    public List<Operation> getPipelineOfSortedUpstreamDependencies() {

        return operations.stream().collect(Collectors.toList());
    }
}
