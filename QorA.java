
/**
 *QorA
 *Object that contains either the name of an animal, or a question that 
 *can distinguish between two animals.
 *
 * @author Bill Viggers
 * @version 1st June 2020
 * 13-Jun-2023 - update to auto identify if a question by presence of a question mark at end.
 */
public class QorA
{
    // instance variables 
    private String whatAmI; // this is either the name of an animal, or a question that will distinguish between two animals
    private QorA answerYes; // next step if the question is answered yes.
    private QorA answerNo; // next step if the question is answered no.

    /**
     * Constructor:
     * 
     */
    public  QorA(String  text)
    {
        this.whatAmI=text;
        this.answerYes=null;
        this.answerNo=null;
    }

    /**
     * Returns true if this is an animal, or false if it is a question
     */
    public boolean isAnAnimal(){
        if (this.whatAmI.length()==0) return true;
        //System.out.println("Debug, checking..."+this.whatAmI.charAt(this.whatAmI.length()-1));
        //System.out.println("about to report this isAnAnimal as "+(this.whatAmI.charAt(this.whatAmI.length()-1) !='?'));
        return (this.whatAmI.charAt(this.whatAmI.length()-1) !='?');   
    }

    /**
     * Returns either the question or the name of the animal, depending on what this node is.
     */
    public String whoAmI(){
        return this.whatAmI;
    }   

    /**
     * Returns either the yes or no branch of the question, depending on if it is passed a 
     * true (yes) or false (no) value.
     */
    public QorA getBranch(boolean yes){
        if (yes) return this.answerYes;
        else return this.answerNo;
    }
    
     public void setBranch(QorA node, boolean yes){
        if (yes) this.answerYes=node;
        else  this.answerNo=node;
    }
}
