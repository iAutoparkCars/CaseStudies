import java.util.*;

class Shelter{

	// global fields
    int arrivalTime = 0;
    final String DEQUEUE_ERR_DOG = "Warning: No more dogs left to dequeue";
    final String DEQUEUE_ERR_CAT = "Warning: No more cats left to dequeue";
    final String ENQUEUE_ERR = "Tried to enqueue invalid animal.";

    public static void main(String[] args){
        Shelter s = new Shelter();
        s.test1(s);
    }
    
    public void test1(Shelter s){
    	/*
    	 * most testing done with dequeueAny, esp in cases if dogs or cats q is empty
    	 */
    	System.out.println(s.dequeueAny());
    	 Dog d1 = new Dog("d1");
         Cat c1 = new Cat("c1");
         Cat c2 = new Cat("c2");
         Cat c3 = new Cat("c3");
         Dog d2 = new Dog("d2");

         s.enqueue(c1);
         s.enqueue(c2);
         s.enqueue(c3);
         //s.enqueue(d1);
         //s.enqueue(d2);
         
         System.out.println(s.dequeueAny());
         
         System.out.println("d: " + dogs);
         System.out.println("c: " + cats);
         System.out.println("");
    }

    // separate queues to hold dogs and cats
    static Queue<Dog> dogs;
    static Queue<Cat> cats;

    public Shelter(){
        dogs = new LinkedList<Dog>();
        cats = new LinkedList<Cat>();
    }

    public void enqueue(AnyAnimal animal){
    	animal.setArrivalTime(arrivalTime++);
    	
    	if (Cat.class.isInstance(animal))
    		cats.offer((Cat)animal);
    	else if (Dog.class.isInstance(animal))
    		dogs.offer((Dog)animal);
    	else
    		System.err.println(ENQUEUE_ERR);
    }

    public AnyAnimal dequeueAny(){
    	// if either queue is empty, return the other
    	if (dogs.isEmpty()) return dequeueCat();
    	if (cats.isEmpty()) return dequeueDog();

    	if (dogs.peek().arrivedEarlierThan(cats.peek()))
            return dequeueDog();
        else return dequeueCat();
    }

    public Dog dequeueDog(){
        if (dogs.size() == 0) System.err.println(DEQUEUE_ERR_DOG);
        return dogs.poll();
    }

    public Cat dequeueCat(){
        if (cats.size() == 0) System.err.println(DEQUEUE_ERR_CAT);
        return cats.poll();
    }
}

/* ------------------------ ANIMAL CLASSES --------------------------*/
class AnyAnimal{
    private String name;
    private int arrivalTime;

    public AnyAnimal(String name) { this.name = name; }
    
    public void setArrivalTime(int time) { this.arrivalTime = time; }
    
    public boolean arrivedEarlierThan(AnyAnimal animal){
    	if (animal == null) return true;
        return this.arrivalTime < animal.arrivalTime;
    }
    
    @Override
    public String toString() { return name + " " + arrivalTime; }
}

class Dog extends AnyAnimal{
    public Dog(String name){ super(name); }
}

class Cat extends AnyAnimal{
    public Cat(String name){ super(name); }
}
/* ---------------------- END ANIMAL CLASSES -------------------------*/