package projectb;

import projecta.TypeA;
import projecta.extra.TypeB;

public class ProjectB {
    public static void main(String[] args) {
        new TypeA().doSomething();
        new TypeB().doSomething();
    }
}
