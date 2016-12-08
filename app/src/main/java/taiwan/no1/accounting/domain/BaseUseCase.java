package taiwan.no1.accounting.domain;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadPoolExecutor;

import dagger.internal.Preconditions;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;
import taiwan.no1.accounting.domain.executor.PostExecutionThread;
import taiwan.no1.accounting.domain.executor.ThreadExecutor;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case in the
 * application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a {@link rx.Subscriber} that will
 * execute its job in a background thread and will post the result in the UI thread.
 * <p>
 * For passing a request parameters {@link RequestValues} to data layer that set a generic type for wrapping
 * vary data.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public abstract class BaseUseCase<R extends BaseUseCase.RequestValues> {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();
    R requestValues = null;

    BaseUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link BaseUseCase}.
     */
    @NonNull
    protected abstract Observable buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build with
     *                          {@link #buildUseCaseObservable()}.
     */
    public void execute(@NonNull final Subscriber useCaseSubscriber) {
        Preconditions.checkNotNull(useCaseSubscriber);

        this.subscription = this.buildUseCaseObservable()
                                .subscribeOn(getSubscribeScheduler())
                                .observeOn(getObserveScheduler())
                                .subscribe(useCaseSubscriber);
    }

    /**
     * Executes the current use case with request parameters.
     *
     * @param request           Send the data to data layer with request parameters.
     * @param useCaseSubscriber The guy who will be listen to the observable build with
     *                          {@link #buildUseCaseObservable()}.
     */
    public void execute(@NonNull final R request, @NonNull final Subscriber useCaseSubscriber) {
        Preconditions.checkNotNull(request);
        Preconditions.checkNotNull(useCaseSubscriber);

        this.requestValues = request;
        this.execute(useCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    /**
     * Obtain a thread for while {@link Observable} is doing their tasks.
     *
     * @return {@link Scheduler} implement from {@link PostExecutionThread}.
     */
    @NonNull
    protected Scheduler getObserveScheduler() {
        return postExecutionThread.getScheduler();
    }

    /**
     * Obtain a thread from {@link ThreadPoolExecutor} for while {@link Scheduler} is doing their tasks.
     *
     * @return {@link Scheduler} implement from {@link ThreadExecutor}.
     */
    @NonNull
    protected Scheduler getSubscribeScheduler() {
        return Schedulers.from(threadExecutor);
    }

    /**
     * Interface for wrap a data for passing to a request.
     */
    interface RequestValues {}
}
