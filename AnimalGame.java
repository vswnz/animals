
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
        while (!answer.equals("yes") && !answer.equals("no")) 
        {// minimal error checking of input
            System.out.println("Please just, 'yes', or 'no'");
            System.out.println(question);
            answer=keyboard.nextLine();
        }
        return answer;
    }

    /**
     * main routine
     */
    public AnimalGame()
    {
        tree=new QorA("Do it have four legs?",false);  // we start off knowing about dogs & birds.
        QorA dog=new QorA("dog",true);
        QorA bird=new QorA("bird",true);
        tree.setAnimal(dog,true);
        tree.setAnimal(bird,false);
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
                    currentQ=currentQ.getCorrectQorA(true);
                    previousYN=true;
                }
                else 
                {
                    currentQ=currentQ.getCorrectQorA(false);
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
                QorA newAnimal= new QorA(keyboard.nextLine(),true);
                System.out.println("And what yes/no question could I ask to distinguish between a");
                System.out.println(currentQ.whoAmI() +" and a "+newAnimal.whoAmI()+"?");
                QorA newQuestion=new QorA(keyboard.nextLine(),false);
                String rightAnswer=askYesOrNo("and is the answer yes or no for a "+newAnimal.whoAmI());
                if (rightAnswer.equals("yes")){
                    newQuestion.setAnimal(currentQ,false);
                    newQuestion.setAnimal(newAnimal,true);
                } else {
                    newQuestion.setAnimal(currentQ,true);
                    newQuestion.setAnimal(newAnimal,false);
                }  // RightAnswer (else)
                // Now update the previous question to point to the new one we jsut made
                previous.setAnimal(newQuestion,previousYN);
                  
            } // update new question

             playAgain=askYesOrNo("Want to play again?");
        } // Play again?
    }
}
