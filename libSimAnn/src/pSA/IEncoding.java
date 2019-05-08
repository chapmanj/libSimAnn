package pSA;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chapmanj
 */
public interface IEncoding {
    
    //encapsulate obtaining objective function value parameter
    public double get_ofv();
    
    //calculates the objective function value for solution
    public double evaluate();
    
    //returns the cloned solution boxed as an Object
    public Object clonesoln();
    
    //obtains the solution and objective function value from passed in interface
    public void steal(Object boxed_encoding);
    
    //modifies the solution (a neighbor) (overwrites)
    public void preturb(java.util.Random rng);
    
    //randomizes the solution (overwrites)
    public void randomize(java.util.Random rng);
    
    //to display encoding the way you want it
    public String tostring();    
}
