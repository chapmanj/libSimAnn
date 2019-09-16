# libSimAnn
simulated annealing library with example problems

## Motivation 🤓
This library stems from learning about meta-heuristics, specifically simulated anealing, in an Adaptive Optimization course at Auburn University.
After becoming familar with the mechanics, I decided to generalize the framework to increase accessability.

## Useage 📖
An example use case NetBeans package is included named SimulateAnnealing-Example.
It presents the Quadratic Assignment Problem and the Six-hump Camel Back problems to illustrate differences in encodings used for this search type.

 0. Import the pSA package.

 1. Instance the `SimAnn` solver class and set the parameters for the psuedo-random number generator and for displaying search updates.
 
 2. Define your encoding.
 
    Generally, simulated annealing can be applied to both continuous and combinatorial problems. 
    In the example, the `QAP_ex` (and later the `SHCB_ex`) class was developed to model a solution.
    Your encoding class needs to implement the `IEncoding` interface which mandates creating functions:
    
     - `evaluate` : also termed objective function, which returns the value to optimize ( maximize / minimize )
       
     - `preturb` : a method to evaluate neighboring solutions (by altering the current solution)
     
     - `randomize` : a method to generate random starting solutions
 
  3. Run the search.
  
     The returned solution will be the best solution found during the search.

## Future 👨‍💻

 - Remove `steal`
   
   There were a few mechanics around passing values I was unable to figure out.
   Please review the example cases to see how and why I used them. 
