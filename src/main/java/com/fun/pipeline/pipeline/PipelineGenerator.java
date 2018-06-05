package com.fun.pipeline.pipeline;

import com.fun.pipeline.domain.Operation;
import com.fun.pipeline.domain.UpstreamAdjacencyMatrix;

import java.util.*;
import java.util.stream.Collectors;

public class PipelineGenerator {

    private UpstreamAdjacencyMatrix upstreamAdjacencyMatrix;

    private boolean[] visitedFlag;

    private Set<Operation> operations;


    public PipelineGenerator(Set<Operation> operations) {
        this.operations = operations;
        visitedFlag = new boolean[operations.size() + 1];
    }


    public UpstreamAdjacencyMatrix updateUpstreamAdjacencyMatrix() {

        upstreamAdjacencyMatrix = new UpstreamAdjacencyMatrix(operations.size() + 1, operations.size() + 1);

        operations.forEach(operation -> operation.getUpstreamDep().forEach(upstreamOperation -> {
            upstreamAdjacencyMatrix.addEdge(upstreamOperation.getId(), operation.getId());
        }));

        return upstreamAdjacencyMatrix;
    }

    public List<Operation> getPipelineOfSortedUpstreamDependencies() {

        updateUpstreamAdjacencyMatrix();

        Stack<Operation> operationsStack = new Stack<>();

        Stack<Operation> sortedOperationsBasedOnUpstreamDeps = new Stack<>();

        this.operations.forEach(operationToBeIterated -> {
            performDFSAndSaveOperationsInOrder(operationsStack, sortedOperationsBasedOnUpstreamDeps, operationToBeIterated);
        });
        List<Operation> pipeline = new ArrayList<>(sortedOperationsBasedOnUpstreamDeps);
        Collections.reverse(pipeline);

        return pipeline;
    }

    private void performDFSAndSaveOperationsInOrder(Stack<Operation> operationsStack, Stack<Operation> sortedOperationsBasedOnUpstreamDeps, Operation operationToBeIterated) {
        operationsStack.push(operationToBeIterated);

        pushOperationOnlyIfNotPresent(sortedOperationsBasedOnUpstreamDeps, operationToBeIterated);

        Iterator<Integer> iterator = upstreamAdjacencyMatrix.adj(operationsStack.pop().getId()).iterator();

        if (!visitedFlag[operationToBeIterated.getId()]) {
            traverseUpstreamDependentOperations(operationsStack, sortedOperationsBasedOnUpstreamDeps, iterator);
            visitedFlag[operationToBeIterated.getId()] = true;
        }
    }

    private void traverseUpstreamDependentOperations(Stack<Operation> operationsStack, Stack<Operation> sortedOperationsBasedOnUpstreamDeps, Iterator<Integer> iterator) {
        while (iterator.hasNext()) {
            Integer operationId = iterator.next();
            Operation operationWhoseUpstreamDepNeedsToBeCalculated = this.operations.stream()
                    .filter(operation -> operation.getId() == operationId).collect(Collectors.toList()).get(0);
            operationsStack.push(operationWhoseUpstreamDepNeedsToBeCalculated);

            pushOperationOnlyIfNotPresent(sortedOperationsBasedOnUpstreamDeps, operationWhoseUpstreamDepNeedsToBeCalculated);
        }
    }

    private void pushOperationOnlyIfNotPresent(Stack<Operation> sortedOperationsBasedOnUpstreamDeps, Operation operationToBeIterated) {
        if (sortedOperationsBasedOnUpstreamDeps.stream()
                .filter(operation -> operation.getId() == operationToBeIterated.getId())
                .collect(Collectors.toList()).size() == 0)
            sortedOperationsBasedOnUpstreamDeps.push(operationToBeIterated);
    }

}
