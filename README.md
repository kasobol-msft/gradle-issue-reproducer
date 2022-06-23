
# About the problem

## The sample project layout

### projectA

`projectA` is an example where final jar is composed of multiple source sets. 
Other projects attempt to take dependency on it.
A real world examples of that are (MRJARs, project with generated sources, JDK8 + module info builds)
I.e.
- the `src/main/java` is a standard source set with `TypeA.java` inside 
- the `src/extrasrc/java` is additional source set with `TypeB.java` inside
- the `projectA.jar` is configured to include both `main` and `extrasrc`, i.e. has both `TypeA.class` and `TypeB.class`

Assume that `projectA.jar` lands later on Maven central.

### projectB

`projectB` attempts to use `projectA` via `project()` dependency, i.e. `implementation(project(":projectA", "customConfiguration"))`
The `customConfiguration` has been created to make products of both `main` and `extrasrc` visible
to "main compile", otherwise compilation fails not seeing `TypeB` in the `ProjectB`

I.e. attempt to replace `implementation(project(":projectA", "customConfiguration"))`
with `implementation(project(":projectA"))` in `projectB/build.gradle.kts` to observe that.

### projectC

`projectC` has a class `ProjectB` that's only using `TypeA` from `main` source set of `projectA`
to illustrate that module substitutes kind of work partially. i.e.
- the `implementation("org.apache.commons:commons-lang3:3.0")` pretends to be `implementation("projectA:3.0")` (if projectA shipped to maven)
  (think about it as a project that wants to depend only on released version of `projectA` under normal
  circumstances)
- the `projectC/build.gradle.kts` attempts to substitute `org.apache.commons:commons-lang3:3.0`
  with `projectA`, this works (no usage for `TypeB` here).

### projectD

`projectD` is a copy of `projectC` but it additionally attempts to use `TypeB` from `projectA`.
The compilation fails and there's no good way of solving it.

## What fails

- compilation of `projectD` fails, it can't see `TypeB` from `projectA` when using substitutions.

## Desired behavior

1. compilation of `projectD` succeeds while using `TypeB` and substituting and artifact by `projectA` dependency.

## Author thoughts

It's odd that compilation of `projectB` fails if `customConfiguration` is removed given that `projectA`
clearly defines that artifact is composition of both source sets. Perhaps this is also an issue here.
But regardless of that, there are cases where project emits extra artifact (like test jar), so 
there should be a way to tell that I want to subsitute artifact with classifier with certain project configuration.
 