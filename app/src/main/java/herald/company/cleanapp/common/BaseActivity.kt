package herald.company.cleanapp.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by herald25santos on 2019-07-24
 */
abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    protected abstract fun layoutId(): Int

    protected open fun beforeLayout(savedInstanceState: Bundle?) = Unit
    protected open fun afterLayout(savedInstanceState: Bundle?) = Unit
    protected open fun onViewsBound() = Unit
    protected open fun onInitializeListener() = Unit
    protected open fun onViewModelBound() = Unit

    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeLayout(savedInstanceState)
        setContentView(layoutId())
        afterLayout(savedInstanceState)
        onViewModelBound()
        onViewsBound()
        onInitializeListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
        disposables.dispose()
    }

}