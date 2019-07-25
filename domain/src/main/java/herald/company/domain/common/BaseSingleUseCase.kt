package herald.company.domain.common

import io.reactivex.Single

abstract class BaseSingleUseCase<T>(private val transformer: SingleRxTransformer<T>) {

    abstract fun createSingle(data: Map<String, Any>? = null): Single<T>

    fun single(withData: Map<String, Any>? = null): Single<T> {
        return createSingle(withData).compose(transformer)
    }
}