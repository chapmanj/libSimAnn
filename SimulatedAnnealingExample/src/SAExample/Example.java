/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SAExample;

//import java.util.Random;
import pSA.*;

/**
 *
 * @author chapmanj
 */
public class Example {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*  To use the Simulated Annealing class three things must be defined
            1.) an encoding class that implements IEncoding
                using the parameter random_ini=true will start the search
                with a ranomd solution. Otherwise, it will start with the
                solution defined in the encoding class
            2.) setting random number generator seed
            3.) calling the run() method with associated paramerters
        
            optionally, using the write_* parameters will provide more feedback
            from the search.
        */
        
        //define simulated annealing instance
        CSimAnn heuristic = new CSimAnn();
        
        //within the CSimAnn class, RNG=Random()
        //set the seed for repeatable rng streams.
        long rngseed = 7000;
        heuristic.setRNG(rngseed);
        
        //optional output
        heuristic.random_ini = true;
        heuristic.write_improvements = true;
        heuristic.write_update = false;
        heuristic.write_rate = 1000;
        
        //Example Problem: quadtratic assignment problem
        System.out.println("Run QAP.");
        //define encoding class for qap
        QAP_ex qap_ini_solution = new QAP_ex();
        //start search
        heuristic.run(qap_ini_solution, 20000, 0.0001, 0.9999);
        System.out.println("Global Best: 575");
        
        System.out.println("-----");
        
        //Example Problem: six hum camel back problem
        System.out.println("Run SHCB.");
        //do not allow random start
        heuristic.random_ini = false;
        //define encoding class for shcb
        SHCB_ex shcb_ini_solution = new SHCB_ex();
        //neighborhood search paramter defined in encoding class
        shcb_ini_solution.step = 0.0002;
        //start search
        heuristic.run(shcb_ini_solution, 200000000, 0.000001, 0.99999);
        System.out.println("Global Best: [-0.0898, 0.7126] : -1.0316");
    }    

}
