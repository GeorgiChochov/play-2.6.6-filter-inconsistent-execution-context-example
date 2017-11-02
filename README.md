# play-2.6.6-filter-inconsistent-execution-context-example

In Play 2.6.6, on Dev, the execution context used inside the Controller's action is seemingly chosen at random. The two execution contexts have different ClassLoaders which can produce inconsistent behaviour.

This behaviour is only observed with an `play.mvc.Filter` such as the one described in the docs [here](https://www.playframework.com/documentation/2.6.x/JavaHttpFilters#where-do-filters-fit-in-).

Hitting the "/" route produces the following log messages:
```
[error] application - BEFORE: play-dev-mode-akka.actor.default-dispatcher-2 - class play.runsupport.NamedURLClassLoader
[error] c.FooController - ACTION: application-akka.actor.default-dispatcher-6 - class play.runsupport.DelegatedResourcesClassLoader
[error] application - AFTER: ForkJoinPool.commonPool-worker-2 - class sun.misc.Launcher$AppClassLoader
[error] application - BEFORE: play-dev-mode-akka.actor.default-dispatcher-4 - class play.runsupport.NamedURLClassLoader
[error] c.FooController - ACTION: play-dev-mode-akka.actor.default-dispatcher-4 - class play.runsupport.NamedURLClassLoader
[error] application - AFTER: ForkJoinPool.commonPool-worker-2 - class sun.misc.Launcher$AppClassLoader
```
