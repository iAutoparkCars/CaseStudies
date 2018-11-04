import java.util.*;

class Shelter{

    public static void main(String[] args){
        Shelter s = new Shelter();
        AnyAnimal animal = new AnyAnimal("argos", 1);
    }

    // separate queues to hold dogs and cats
    Queue<Dog> dogs;
    Queue<Cat> cats;

    public Shelter(){
        dogs = new LinkedList<Dog>();
        cats = new LinkedList<Cat>();
    }

    // four functions to implement
    public void enqueue(AnyAnimal animal){

    }

    public AnyAnimal dequeueAny(){
        if (dogs.peek().arrivedEarlierThan(cats.peek()))
            return dogs.poll();
        else return cats.poll();
    }

    public Dog dequeueDog(){
        return null;
    }

    public Cat dequeueCat(){
        return null;
    }

    class AnyAnimal{
        private String name;
        private int arrivalTime;

        public AnyAnimal(String name, int time) {
            this.name = name;
            this.arrivalTime = time;
        }

        public boolean arrivedEarlierThan(AnyAnimal animal){
            return this.arrivalTime < animal.arrivalTime;
        }
    }

    class Dog extends AnyAnimal{
        public Dog(String name, int time){ super(name, time); }
    }

    class Cat extends AnyAnimal{
        public Dog(String name, int time){ super(name, time); }
    }
}
