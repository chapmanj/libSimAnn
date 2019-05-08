/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SAExample;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import pSA.*;

/**
 *
 * @author chapmanj
 */
public class QAP_ex implements IEncoding,Cloneable {
       
    //problem the data structures
    int fromto[][] ;
    int flow[][];
    int solution[];
    double ofv;
    
    public QAP_ex(){
        //Given Data
        fromto = new int[][]{
            {	0,	1,	2,	3,	4,	1,	2,	3,	4,	5,	2,	3,	4,	5,	6	},
            {	1,	0,	1,	2,	3,	2,	1,	2,	3,	4,	3,	2,	3,	4,	5	},
            {	2,	1,	0,	1,	2,	3,	2,	1,	2,	3,	4,	3,	2,	3,	4	},
            {	3,	2,	1,	0,	1,	4,	3,	2,	1,	2,	5,	4,	3,	2,	3	},
            {	4,	3,	2,	1,	0,	5,	4,	3,	2,	1,	6,	5,	4,	3,	2	},
            {	1,	2,	3,	4,	5,	0,	1,	2,	3,	4,	1,	2,	3,	4,	5	},
            {	2,	1,	2,	3,	4,	1,	0,	1,	2,	3,	2,	1,	2,	3,	4	},
            {	3,	2,	1,	2,	3,	2,	1,	0,	1,	2,	3,	2,	1,	2,	3	},
            {	4,	3,	2,	1,	2,	3,	2,	1,	0,	1,	4,	3,	2,	1,	2	},
            {	5,	4,	3,	2,	1,	4,	3,	2,	1,	0,	5,	4,	3,	2,	1	},
            {	2,	3,	4,	5,	6,	1,	2,	3,	4,	5,	0,	1,	2,	3,	4	},
            {	3,	2,	3,	4,	5,	2,	1,	2,	3,	4,	1,	0,	1,	2,	3	},
            {	4,	3,	2,	3,	4,	3,	2,	1,	2,	3,	2,	1,	0,	1,	2	},
            {	5,	4,	3,	2,	3,	4,	3,	2,	1,	2,	3,	2,	1,	0,	1	},
            {	6,	5,	4,	3,	2,	5,	4,	3,	2,	1,	4,	3,	2,	1,	0	}
        };

        flow = new int [][]{
            {	0,	10,	0,	5,	1,	0,	1,	2,	2,	2,	2,	0,	4,	0,	0,	},
            {	10,	0,	1,	3,	2,	2,	2,	3,	2,	0,	2,	0,	10,	5,	0,	},
            {	0,	1,	0,	10,	2,	0,	2,	5,	4,	5,	2,	2,	5,	5,	5,	},
            {	5,	3,	10,	0,	1,	1,	5,	0,	0,	2,	1,	0,	2,	5,	0,	},
            {	1,	2,	2,	1,	0,	3,	5,	5,	5,	1,	0,	3,	0,	5,	5,	},
            {	0,	2,	0,	1,	3,	0,	2,	2,	1,	5,	0,	0,	2,	5,	10,	},
            {	1,	2,	2,	5,	5,	2,	0,	6,	0,	1,	5,	5,	5,	1,	0,	},
            {	2,	3,	5,	0,	5,	2,	6,	0,	5,	2,	10,	0,	5,	0,	0,	},
            {	2,	2,	4,	0,	5,	1,	0,	5,	0,	0,	10,	5,	10,	0,	2,	},
            {	2,	0,	5,	2,	1,	5,	1,	2,	0,	0,	0,	4,	0,	0,	5,	},
            {	2,	2,	2,	1,	0,	0,	5,	10,	10,	0,	0,	5,	0,	5,	0,	},
            {	0,	0,	2,	0,	3,	0,	5,	0,	5,	4,	5,	0,	3,	3,	0,	},
            {	4,	10,	5,	2,	0,	2,	5,	5,	10,	0,	0,	3,	0,	10,	2,	},
            {	0,	5,	5,	5,	5,	5,	1,	0,	0,	0,	5,	3,	10,	0,	4,	},
            {	0,	0,	5,	0,	5,	10,	0,	0,	2,	5,	0,	0,	2,	4,	0,	}
        };
    
        //a solution specifies adjacent placement of departments
        solution = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        ofv = evaluate();    
    }
    
    //calculates the objective function value, storing it in the parameter ofv
    //here, the ofv is the sumproduct of the flow matrx and the permuation of 
    //the fromto matrix (based on the solution)
    @Override
    public double evaluate() {
        ofv = 0;
               
        for (int i = 0; i < solution.length; i++) 
            for (int j = i + 1; j < solution.length; j++) 
                ofv += fromto[solution[i]-1][solution[j]-1] * flow[i][j];
                                 
        return ofv; 
    }

    //encapsulations for the parameter ofv
    @Override
    public double get_ofv(){
        return ofv;
    }
    
    //from passed in interface/solution, take values
    @Override
    public void steal(Object boxed_Encoding){
        //unbox and take values
        solution = ((QAP_ex)boxed_Encoding).solution;
        ofv = ((QAP_ex)boxed_Encoding).get_ofv();
    }
    
    //clone solution
    @Override
    public Object clonesoln()
    {   
        //manually determine what information should be passed.      
        //EncodingExample e = new EncodingExample();
        //...
        //return e;
        
        //use default clone function
        try {

            return this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(QAP_ex.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new QAP_ex();
    }
    
    //a pair-wise swap of departments in solution
    @Override
    public void preturb(Random rng) {
        int swap1 = rng.nextInt(solution.length);
        int swap2 = rng.nextInt(solution.length);
        
        int hold1 = solution[swap1];
        int hold2 = solution[swap2];
        
        solution[swap1] = hold2;
        solution[swap2] = hold1;        
    }
    
    //select a random sequence of deparments for solution
    @Override
    public void randomize(Random rng){
        int rsoln[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        
        //keep trying to assign departments to positions until all are assigned
        for (int i = 0; i < solution.length; i++) {
            int ri = rng.nextInt(solution.length);
            if(rsoln[ri] == 0)
                rsoln[ri] = solution[i];
            else
                i--;
        }
        solution = rsoln;
    }
    
    //feedback helper functions
    String liststring(int[] list) {
        String out = "[";
        
        for (int i = 0; i < list.length; i++) 
            out += list[i] + " ";
        
        out += "] ";
        
        return out;
    }
    
    String tablestring(int[][] table){
        String out = "[";
        
        for (var table1 : table) {
            out += liststring (table1);
        }
        out += "]\r\n";
        
        return out;
    }
   
    //writes the ofv and solution
    @Override
    public String tostring() {
        String out = "";
        out += "ofv: " + ofv + " ";       
        out += liststring(solution);
        
        return out;
    }
}
