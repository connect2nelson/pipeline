package com.fun.pipeline.domain;

import java.util.List;

public class Operation {

    int id;
    List<Operation> upstreamDep;
    List<Operation> downstreamDep;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Operation> getUpstreamDep() {
        return upstreamDep;
    }

    public void setUpstreamDep(List<Operation> upstreamDep) {
        this.upstreamDep = upstreamDep;
    }

    public List<Operation> getDownstreamDep() {
        return downstreamDep;
    }

    public void setDownstreamDep(List<Operation> downstreamDep) {
        this.downstreamDep = downstreamDep;
    }

    public Operation(int id, List<Operation> upstreamDep, List<Operation> downstreamDep) {
        this.id = id;
        this.upstreamDep = upstreamDep;
        this.downstreamDep = downstreamDep;
    }

    String execute(){
        return "Executing operation " + id;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", upstreamDep=" + upstreamDep +
                ", downstreamDep=" + downstreamDep +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        if (id != operation.id) return false;
        return (upstreamDep != null ? upstreamDep.equals(operation.upstreamDep) : operation.upstreamDep == null) && (downstreamDep != null ? downstreamDep.equals(operation.downstreamDep) : operation.downstreamDep == null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (upstreamDep != null ? upstreamDep.hashCode() : 0);
        result = 31 * result + (downstreamDep != null ? downstreamDep.hashCode() : 0);
        return result;
    }
}
