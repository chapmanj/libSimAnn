/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SAExample;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import pSA.IEncoding;

/**
 *
 * @author chapmanj
 */
public class SHCB_ex implements IEncoding,Cloneable{

    //problem data structures
    double solution[];
    double ofv;
    
    //maximum distance of candidate from solution
    public double step;
    
    public SHCB_ex(){
        //initial solution
        solution = new double[]{0,0};
        ofv = evaluate();
        
        //default. should be specified externally
        step = 0.02;
    }
    
    //encapsulation for parameter ofv
    @Override
    public double get_ofv() {
        return ofv;
    }

    //calculates the objective function value, storing it in the parameter ofv
    //here, the ofv is determined by the following function that defines the 
    //six hump camel back solution space (minimization)
    @Override
    public double evaluate() {
        ofv = ((4d - 2.1d * Math.pow(solution[0],2) 
                + Math.pow(solution[0],4) / 3d)
                * Math.pow(solution[0], 2) 
                + solution[0] * solution[1] 
                + (-4d + 4d * Math.pow(solution[1], 2))
                *Math.pow(solution[1], 2) );
        
        return ofv;
    }

    //clone this
    @Override
    public Object clonesoln() {
        try {
            return this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SHCB_ex.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new double[]{0,0};
    }

    //from passed in interface/solution, take values
    @Override
    public void steal(Object boxed_encoding) {
        solution = ((SHCB_ex) boxed_encoding).solution;
        ofv = ((SHCB_ex) boxed_encoding).ofv;
    }

    //a move in a random x,y based on max distance defined by the parameter step
    //solution is kept within the boundaries of x=(-3,3) and y=(-2,2)
    @Override
    public void preturb(Random rng) {
        solution[0] += rng.nextDouble() * 2d * step - step;
        solution[1] += rng.nextDouble() * 2d * step - step;
            if (solution[0] < -3.0) {solution[0] = -3.0;}                     //handels x lower bound
            if (3.0 < solution[0])  {solution[0] = 3.0;}                      //handles x upper bound
            if (solution[1] < -2.0) {solution[1] = -2.0;}                     //handles y lower bound
            if (2.0 < solution[1])  {solution[1] = 2.0;}                      //handles y upper bound
    }

    //randomly select a point within ((-3,3),(-2,2))
    @Override
    public void randomize(Random rng) {
        solution = new double[] {rng.nextDouble() * 6 - 3, rng.nextDouble() * 4 - 2};
    }

    //displays solution and ofv
    @Override
    public String tostring() {
        String out = "[ " + solution[0] + ", " + solution[1] + "] : " + ofv + "\t";
        return out;
    }
    
}
