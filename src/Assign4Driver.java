import java.util.List;

public class Assign4Driver
{
    public static void main(String[] args)
    {
        if (args.length != 1) {
	        System.err.print("Error: Incorrect number of command line arguments");
	        System.exit(-1);
        }
        // Create a word ladder solver object
        Assignment4Interface wordLadderSolver = new WordLadderSolver(args[0]);

        try 
        {
            List<String> result = wordLadderSolver.computeLadder("money", "yolky");
	        for (String s : result) {
		        System.out.println(s);
	        }
            boolean correct = wordLadderSolver.validateResult("money", "yolky", result);
	        if(correct) {
//		        for (String s : result) {
//			        System.out.println(s);
//		        }
		        System.out.println(result.size());
	        }
        } catch (NoSuchLadderException e) {
            e.printStackTrace();
        }
    }
}
