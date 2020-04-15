package jvm;

public class SingleDoublePai {
    public static void main(String[] args){
        Father father = new Father();
        Father child = new Child();
        father.doSomething(new Eat());
        child.doSomething(new Drink());
    }
}
