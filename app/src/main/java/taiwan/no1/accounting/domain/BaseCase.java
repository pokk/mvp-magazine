package taiwan.no1.accounting.domain;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;
import taiwan.no1.accounting.domain.executor.PostExecutionThread;
import taiwan.no1.accounting.domain.executor.ThreadExecutor;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public abstract class BaseCase<R extends BaseCase.RequestValues> {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();
    protected R requestValues = null;

    protected BaseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link BaseCase}.
     */
    protected abstract Observable buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable()}.
     */
    public void execute(Subscriber useCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                                .subscribeOn(getSubscribeScheduler())
                                .observeOn(getObserveScheduler()).subscribe(useCaseSubscriber);
    }

    /**
     * Executes the current use case with request parameters.
     *
     * @param request Send the data to data layer with request parameters.
     * @param useCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable()}.
     */
    public void execute(R request, Subscriber useCaseSubscriber) {
        this.requestValues = request;
        this.execute(useCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.ThingThing
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    protected Scheduler getObserveScheduler() {
        return postExecutionThread.getScheduler();
    }

    protected Scheduler getSubscribeScheduler() {
        return Schedulers.from(threadExecutor);
    }

    /**
     * Data passed to a request.
     */
    public interface RequestValues {}
}
