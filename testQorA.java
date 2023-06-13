
/**
 * TestQorA is a small program that puts a QorA implementation though its paces
 *
 * @author Bill Viggers
 * @version 2-June 2020
 */
public class testQorA
{
    QorA root; // put here so we can inspect the object in BlueJ
  
    public testQorA()
    {
        QorA question, animal1, animal2;
        question=new QorA("Does it live on land?",false);
        animal1=new QorA("Elephant",true);
        animal2=new QorA("Whale",false);
        root=question;
        
        question.setBranch(animal1,true);
        question.setBranch(animal2,false);
        
        System.out.println("Initial setup:");
        System.out.println(root.whoAmI()+" (yes) "+root.getBranch(true).whoAmI());
        System.out.println(root.whoAmI()+" (no) "+root.getBranch(false).whoAmI());
        System.out.println("Is "+root.whoAmI()+" an animal?" + root.isAnAnimal());
        System.out.println("Is "+root.getBranch(true).whoAmI()+" an animal?" +root.getBranch(true).isAnAnimal());
        System.out.println("Is "+root.getBranch(false).whoAmI()+" an animal?" +root.getBranch(false).isAnAnimal());
        
        System.out.println("Adding complexity...");
        // we add in another option for things that don't live on land.
        question = new QorA("Does it breathe air?",false);
        animal1 = new QorA("Hammerhead shark",true);
        animal2 = root.getBranch(false);
        question.setBranch(animal2,true);
        question.setBranch(animal1,false);
        root.setBranch(question,false);
        System.out.println(root.whoAmI()+" (yes) "+root.getBranch(true).whoAmI());
        System.out.println(root.whoAmI()+" (no) "+root.getBranch(false).whoAmI());
        System.out.println("Is "+root.whoAmI()+" an animal?" + root.isAnAnimal());
        System.out.println("Is "+root.getBranch(true).whoAmI()+" an animal?" +root.getBranch(true).isAnAnimal());
        System.out.println("Is "+root.getBranch(false).whoAmI()+" an animal?" +root.getBranch(false).isAnAnimal());
        
  
    }


}
