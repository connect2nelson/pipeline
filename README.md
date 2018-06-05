# Pipeline generator for operations

#Build Status

[![Build Status](https://travis-ci.org/connect2nelson/tictactoe.svg?branch=master)](https://travis-ci.org/connect2nelson/pipeline)

Consider there is a a class called Operation and which will have the member variables


    class Operation{
      int id;
      void execute(){}
    
      List<Operation> upstreamDep;
      List<Operation> downstreamDep;
    }
    


This is a demo project which takes a list of operation POJOs with upstream and downstream dependencies of operations and returns an ordered list of operations that needs to be executed in order to complete the batch job pipeline. 


## Strategy :
Using a adjancency matrix to create a directed graph denoting all the upstream dependencies.
Since the problem statement says that we have a connected graph of operations.

We then use a DFS approach to start traversing list of operations with upstream dependencies and make a note of operations traversed in the process in  a stack datastructure. 

The stack datastructure then holds the ordered list of operations that needs to be executed in order for the pipeline to non-blocking and finished without any blocks/stalls. 

The stack datastructure hence represents a PIPELINE which this project aims to obtain as the output.
