package herald.company.domain.common

import io.reactivex.Completable

abstract class BaseCompletableUseCase(private val transformer: CompletableRxTransformer) {

    abstract fun createCompletable(data: Map<String, Any>? = null): Completable

    fun completable(withData: Map<String, Any>? = null): Completable {
        return createCompletable(withData).compose(transformer)
    }
}