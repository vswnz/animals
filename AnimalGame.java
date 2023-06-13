
/**
 * Write a description of class AnimalGame here.
 *
 * @author  Bill Viggers
 * @version 1-June-2020
 * 
 * This game starts knowing about dogs.  It asks you to pick an
 * animal and then tries to guess what animal it is.
 * If it gets it wrong, it then finds out what you were thinking of, and then 
 * learns how to distinguish between them.
 * 
 */

import java.util.Scanner;

public class AnimalGame
{
    // instance variables - replace the example below with your own
    QorA tree;

    private String askYesOrNo(String question){
        String answer="";
        Scanner keyboard=new Scanner(System.in);
        boolean first=true;
        while (!answer.equals("yes") && !answer.equals("no")) 
        {// minimal error checking of input
            if (first) first=false;
            else System.out.println("Please just, 'yes', or 'no'");
            
            // Lets make sure there is a question mark at the end of the line
            char extraqm=' ';
            if (question.charAt(question.length()-1) !='?') extraqm='?';
            
            System.out.println(question+extraqm);
            answer=keyboard.nextLine();
        }
        return answer;
    }

    /**
     * main routine
     */
    public AnimalGame()
    {
        tree=new QorA("Does it have four legs?");  // we start off knowing about dogs & birds.
        QorA dog=new QorA("dog");
        QorA bird=new QorA("bird");

        tree.setBranch(dog,true);
        tree.setBranch(bird,false);
        String playAgain="yes";
        while (playAgain.equals("yes")){
            // First print out instructions.
            System.out.println("I need you to think of an animal.  I will try to guess it.");
            System.out.println("I may have some questions for you first though.");
            QorA currentQ=tree;
            QorA previous=tree;  // remember previous question so we can update what it points to.
            boolean previousYN=false; // Need to remember which branch we took too.

            while (!currentQ.isAnAnimal()) 
            { // we keep asking the question until we get to an animal to guess
                previous=currentQ;   // need to remember this for rebalancing the tree later on
                if (askYesOrNo(currentQ.whoAmI()).equals("yes")) 
                {
                    currentQ=currentQ.getBranch(true);
                    previousYN=true;
                }
                else 
                {
                    currentQ=currentQ.getBranch(false);
                    previousYN=false;
                }

            }
            // We are now at a leaf node.  So let us guess the animal.
            if (askYesOrNo("Are you thinking about a "+currentQ.whoAmI()+"?").equals("yes"))
                System.out.println("Yay!  I'm so good.");
            else {
                // Now we need to find a question and reorder the tree.
                System.out.println("Oh.... :-(");
                System.out.println("What were you thinking of?");
                Scanner keyboard=new Scanner(System.in);
                String animal= keyboard.nextLine();
                if (animal.charAt(animal.length()-1)=='?') // if it has a questionmark at the end, remove it.
                  animal=animal.substring(0,animal.length()-2);
                  
                QorA newAnimal= new QorA(animal);
                System.out.println("And what yes/no question could I ask to distinguish between a");
                System.out.println(currentQ.whoAmI() +" and a "+newAnimal.whoAmI()+"?");
                String question= keyboard.nextLine();
                if (question.charAt(question.length()-1)!='?') {// if it has a questionmark at the end, remove it.
                  //System.out.println("DEBUG: Adding question mark to end of question: "+question.charAt(question.length()-1) );
                  question=question+"?";
                  
                }
                
                QorA newQuestion=new QorA(question);
                String rightAnswer=askYesOrNo("and is the answer yes or no for a "+newAnimal.whoAmI());
                if (rightAnswer.equals("yes")){
                    newQuestion.setBranch(currentQ,false);
                    newQuestion.setBranch(newAnimal,true);
                } else {
                    newQuestion.setBranch(currentQ,true);
                    newQuestion.setBranch(newAnimal,false);
                }  // RightAnswer (else)
                // Now update the previous question to point to the new one we jsut made
                previous.setBranch(newQuestion,previousYN);
                  
            } // update new question

             playAgain=askYesOrNo("Want to play again?");
        } // Play again?
    }
    
    // recursive read of question file.
    QorA readFile(){
       // still have to write this!  Recursive read method in which each line is read and returned if it is 
     //an animal is probably the way to go.
     return null;
        
    }
    
}
