package herald.company.cleanapp.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable


abstract class BaseFragment : Fragment() {

    @LayoutRes
    protected abstract fun layoutId(): Int

    protected open fun beforeLayout(savedInstanceState: Bundle?) = Unit
    protected open fun afterLayout(savedInstanceState: Bundle?) = Unit
    protected open fun onViewsBound() = Unit
    protected open fun onViewModelBound() = Unit
    protected open fun onInitializeListener() = Unit

    val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        beforeLayout(savedInstanceState)
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterLayout(savedInstanceState)
        onViewModelBound()
        onViewsBound()
        onInitializeListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
        disposables.dispose()
    }

}