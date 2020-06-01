
/**
 *QorA
 *Object that contains either the name of an animal, or a question that 
 *can distinguish between two animals.
 *
 * @author Bill Viggers
 * @version 1st June 2020
 */
public class QorA
{
    // instance variables - replace the example below with your own
    private String whatAmI; // this is either the name of an animal, or a question that will distinguish between two animals
    private boolean isAnAnimal; // true if whatAmI is an animal
    private QorA answerYes; // animal if the question is answered yes.
    private QorA answerNo; // animal if the question is answered no.

    /**
     * Constructor:
     * If the boolean is true, then it is an animal, if false, a question.
     */
    public QorA(String text, boolean isAnimal)
    {
        this.whatAmI=text;
        this.isAnAnimal=isAnimal;
        this.answerYes=null;
        this.answerNo=null;
    }

    public void setAnimal(QorA animal, boolean yes){
        if (yes) this.answerYes=animal;
        else this.answerNo=animal;
    }

    public boolean isAnAnimal(){
        return isAnAnimal;   
    }

    public String whoAmI(){
        return this.whatAmI;
    }   

    public QorA getCorrectQorA(boolean yes){
        if (yes) return this.answerYes;
        else return this.answerNo;
    }
    
}
