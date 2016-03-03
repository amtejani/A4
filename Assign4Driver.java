package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;



public class Assign4Driver
{
    public static void main(String[] args)
    {
        if (args.length != 2) {
	        System.err.print("Error: Incorrect number of command line arguments");
	        System.exit(-1);
        }
        // Create a word ladder solver object
        Assignment4Interface wordLadderSolver = new WordLadderSolver(args[0]);

        try {
			  FileReader freader = new FileReader(args[1]);
			  BufferedReader reader = new BufferedReader(freader);
			  for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			  {
				  try{
					  String[] input = s.split("\\s+");
					  
					  if(input[0].length() != 5 || input[1].length() != 5)
					  {
						  throw new ArrayIndexOutOfBoundsException();
						  
					  }
					  
					  List<String> result = wordLadderSolver.computeLadder(input[0], input[1]);
					  boolean correct = wordLadderSolver.validateResult(input[0], input[1], result);
				        if(correct) {
					        for (String ladder : result) {
						        System.out.println(ladder);
					        }
					        System.out.println();
				        }
					} 
					catch(ArrayIndexOutOfBoundsException a)
					{	
						System.out.println("INVALID INPUT\n");
					}
				  	catch (NoSuchLadderException e) {
			            System.out.println(e.getMessage()+ "\n");
			        }
				  
				}
			} 
			catch (FileNotFoundException e) 
			{
				System.err.println ("Error: File not found. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) 
			{
				System.err.println ("Error: IO exception. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			}
        
/*
        try 
        {
            List<String> result = wordLadderSolver.computeLadder("money", "honey");
	        for (String s : result) {
		        System.out.println(s);
	        }
            boolean correct = wordLadderSolver.validateResult("money", "honey", result);
	        if(correct) {
//		        for (String s : result) {
//			        System.out.println(s);
//		        }
		        System.out.println(result.size());
	        }
        } catch (NoSuchLadderException e) {
            e.printStackTrace();
        }
        */
    }
}
