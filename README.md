# pipeline

Consider there is a a class called Operation and which will have the member variables


    class Operation{
      int id;
      void execute(){}
    
      List<Operation> upstreamDep;
      List<Operation> downstreamDep;
    }
    


This is a demo project which takes a list of operation POJOs with upstream and downstream dependencies of operations and returns an ordered list of operations that needs to be executed in order to complete the batch job pipeline. 
