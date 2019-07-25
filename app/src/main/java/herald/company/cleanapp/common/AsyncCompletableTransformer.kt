package herald.company.cleanapp.common

import herald.company.domain.common.CompletableRxTransformer
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AsyncCompletableTransformer : CompletableRxTransformer() {
    override fun apply(upstream: Completable): CompletableSource {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}