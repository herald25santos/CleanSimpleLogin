package herald.company.cleanapp.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun clearDisposables() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

    override fun onCleared() {
        clearDisposables()
    }
}