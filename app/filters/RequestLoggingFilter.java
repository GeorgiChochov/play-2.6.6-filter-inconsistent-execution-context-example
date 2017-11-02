package filters;

import akka.stream.Materializer;
import play.Logger;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class RequestLoggingFilter extends Filter {

    @Inject
    public RequestLoggingFilter(Materializer mat) {
        super(mat);
    }

    @Override
    public CompletionStage<Result> apply(Function<Http.RequestHeader, CompletionStage<Result>> nextFilter, Http.RequestHeader requestHeader) {
        /*
         * Log cases:
         *  AkkaHttp on Dev : BEFORE: play-dev-mode-akka.actor.default-dispatcher-10 - class play.runsupport.NamedURLClassLoader
         *  AkkaHttp on Prod: BEFORE: application-akka.actor.default-dispatcher-8 - class sun.misc.Launcher$AppClassLoader
         */
        Logger.error("BEFORE: " + Thread.currentThread().getName() + " - " + Thread.currentThread().getContextClassLoader().getClass().toString());

        long startTime = System.currentTimeMillis();
        return nextFilter.apply(requestHeader).thenApply(result -> {
            /*
             * Log cases:
             *  AkkaHttp on Dev : AFTER: ForkJoinPool.commonPool-worker-1 - class sun.misc.Launcher$AppClassLoader
             *  AkkaHttp on Prod: AFTER: ForkJoinPool.commonPool-worker-1 - class sun.misc.Launcher$AppClassLoader
             */
            Logger.error("AFTER: " + Thread.currentThread().getName() + " - " + Thread.currentThread().getContextClassLoader().getClass().toString());

            long endTime = System.currentTimeMillis();
            long requestTime = endTime - startTime;

            return result.withHeader("Request-Time", "" + requestTime);
        });
    }
}
