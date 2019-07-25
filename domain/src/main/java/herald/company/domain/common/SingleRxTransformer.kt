package herald.company.domain.common

import io.reactivex.SingleTransformer

abstract class SingleRxTransformer<T> : SingleTransformer<T, T>