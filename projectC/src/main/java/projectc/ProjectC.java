package projectc;

import projecta.TypeA;

public class ProjectC {
    public static void main(String[] args) {
        new TypeA().doSomething();
        // compiles without TypeB
    }
}
