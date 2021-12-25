package ren.imyan.kirby.common.ktx

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <VB : ViewBinding> Activity.binding(
    crossinline inflate: (LayoutInflater) -> VB
) = lazy {
    inflate(layoutInflater).apply {
        setContentView(root)
    }
}

inline fun <VB : ViewBinding> Fragment.binding(
    crossinline bind: (View) -> VB
) = FragmentViewBindingDelegate {
    bind(requireView())
}

class FragmentViewBindingDelegate<VB : ViewBinding>(private val block: () -> VB) :
    ReadOnlyProperty<Fragment, VB?> {
    private var binding: VB? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB? {
        if (binding == null) {
            try {
                thisRef.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
                    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                        if (event == Lifecycle.Event.ON_DESTROY) {
                            binding = null
                        }
                    }
                })
                binding = block()
            } catch (ignored: Exception) {
                //fragment.getViewLifecycleOwner()在为空时会抛出错误，因为这个时候不处于onCreateView-onDestroyView之间
                //因为无法安全判断viewLifecycleOwner是否为空,所以通过try-catch方式判断
                binding = null
            }
        }
        return binding
    }
}
