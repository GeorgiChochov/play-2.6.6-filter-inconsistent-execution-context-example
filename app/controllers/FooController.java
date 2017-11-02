package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class FooController extends Controller {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Result index() {
        /*
         * Log cases chooses between a) and b) seemingly at random:
         *  a) AkkaHttp on Dev: ACTION: play-dev-mode-akka.actor.default-dispatcher-10 - class play.runsupport.NamedURLClassLoader
         *  b) AkkaHttp on Dev: ACTION: application-akka.actor.default-dispatcher-6 - class play.runsupport.DelegatedResourcesClassLoader
         *
         *  AkkaHttp on Prod: ACTION: application-akka.actor.default-dispatcher-8 - class sun.misc.Launcher$AppClassLoader
         */
        log.error("ACTION: " + Thread.currentThread().getName() + " - " + Thread.currentThread().getContextClassLoader().getClass().toString());
        return ok(index.render("Your new application is ready."));
    }

}
