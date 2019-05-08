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
public interface ISimAnn {
    
    //defines the random number generator based on the passed in seed.
    public void setRNG(long seed);
    
    //starts search with inital, final, and change in temperature
    public void run(IEncoding solution, double Temp_i, double Temp_f, double alpha);

}
