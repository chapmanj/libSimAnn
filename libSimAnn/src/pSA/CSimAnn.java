package pSA;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chapmanj
 */
 public class CSimAnn implements ISimAnn, Runnable {
    //random number generator 
    long SEED;
    Random rng = new Random();

    //stores best found solution
    Object BEST_FOUND_SOLUTION = null;
    
    //counter (more intuitive than interpreting temperature change)
    int iterations;
    
    //public parameters for feedback
    public boolean random_ini;
    public boolean write_improvements;
    public boolean write_update;
    public int write_rate;
    
    public CSimAnn() {
        //initial values. can be modified externally
        SEED = 1000;
        random_ini = true;
        iterations = 0;
        write_improvements = true;
        write_update = false;
        write_rate = 1000;
    }
    
    /*
        Starts the simumlated annealing search with :
            the encoding interface for the user generated encoding class
            an initial temperature, Temp_i
            a final temperature, Temp_f
            a temperature change rate per iteration, alpha
    */
    @Override
    public void run(IEncoding solution, double Temp_i, double Temp_f, double alpha) {
        //randomize starting solution
        if(random_ini)
            solution.randomize(rng);
        
        //hold the initial solution as the best found so far (bc it is)
        BEST_FOUND_SOLUTION = solution;
        
        System.out.println("initial solution: " + solution.tostring());

        //annealing loop
        for (double Temp = Temp_i; Temp_f < Temp; Temp = Temp * alpha) {
            iterations++;
         
            //create and evaluatea a preturbation of the current solution
            IEncoding candidate = get_candidate(solution);
            candidate.preturb(rng);
            candidate.evaluate();
            
            //difference in ofv
            double EnergyDifference = candidate.get_ofv() - solution.get_ofv();
            

            //Accept Move or not
            //(for minimization) if the candidate solution has lower energy
            //move to candidate solution and update best found
            if (EnergyDifference < 0) {
                solution.steal(candidate.clonesoln());
                BEST_FOUND_SOLUTION = solution;
                if(write_improvements && iterations % write_rate == 0)
                    System.out.println("^\t" + iterations + "\t" + Temp + "\t" + solution.tostring());
            }
            //else, randomly, as a function of the temperature differnce, move
            //to the candidate solution
            else if (rng.nextDouble() <= 1/(Math.exp(EnergyDifference/Temp))) {
                solution.steal(candidate.clonesoln());
                
                if(write_improvements && iterations % write_rate == 0)
                    System.out.println("*\t" + iterations + "\t" + Temp + "\t" + solution.tostring());
            }
            
            //feedback
            if(write_update && iterations % write_rate == 0)
                System.out.println("\t" + iterations + "\t" + Temp + "\t" + solution.tostring());   
        }
        System.out.println("Best found solution: " + ((IEncoding)BEST_FOUND_SOLUTION).tostring() );
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //defines new random nunmber generator with specified seed
    @Override
    public void setRNG(long seed) {
        SEED = seed;
        rng = new Random(SEED);
    }

    //copies solution to create candidate solution
    IEncoding get_candidate(IEncoding soln){
        //get encoding class from solution interface
        Class<? extends IEncoding> aClass = soln.getClass();
        
        //clone solution (to Object)
        Object ob = soln.clonesoln();
        
        //cast rncoding class on 
        var b = aClass.cast(ob);

        //get required variables
        b.steal(soln.clonesoln());

        return b;
    }
}
