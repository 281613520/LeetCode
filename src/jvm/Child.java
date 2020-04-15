package jvm;

class Child extends Father{
    public void doSomething(Eat arg){
        System.out.println("儿子在吃饭");
    }
    public void doSomething(Drink arg){
        System.out.println("儿子在喝水");
    }
}