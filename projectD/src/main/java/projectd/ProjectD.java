package projectd;

import projecta.TypeA;
import projecta.extra.TypeB;

public class ProjectD {
    public static void main(String[] args) {
        new TypeA().doSomething();
        // TypeB is not visible here at compilation :/
        new TypeB().doSomething();
    }
}
