package com.fun.pipeline.domain;

import java.util.List;

public class Operation {

    int id;
    List<Operation> upstreamDep;
    List<Operation> downstreamDep;

    public Operation(int id, List<Operation> upstreamDep, List<Operation> downstreamDep) {
        this.id = id;
        this.upstreamDep = upstreamDep;
        this.downstreamDep = downstreamDep;
    }

    String execute(){
        return "Executing operation " + id;
    }

}
