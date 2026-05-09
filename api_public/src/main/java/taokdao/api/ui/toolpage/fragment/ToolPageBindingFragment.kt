package taokdao.api.ui.toolpage.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import taokdao.api.data.bean.Properties
import taokdao.api.ui.base.IPanelProp
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable

abstract class ToolPageBindingFragment<VB : ViewBinding>(prop: IPanelProp) : ToolPageFragment(
    Properties("", prop.label, prop.description),
    prop.icon,
    0
) {
    protected lateinit var binding: VB
        private set

    abstract fun initView(binding: VB)

    @Suppress("UNCHECKED_CAST")
    override fun getView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun initView(view: View) {
        initView(binding)
    }

    @Suppress("UNCHECKED_CAST")
    private fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB {
        val bindingClass = resolveBindingClass()
        val inflateMethod = bindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.javaPrimitiveType
        )
        return inflateMethod.invoke(null, inflater, container, false) as VB
    }

    @Suppress("UNCHECKED_CAST")
    private fun resolveBindingClass(): Class<VB> {
        val typeMap = mutableMapOf<String, Type>()
        var currentClass: Class<*> = javaClass

        while (currentClass != Any::class.java) {
            val genericSuper = currentClass.genericSuperclass
            if (genericSuper is ParameterizedType) {
                val rawClass = genericSuper.rawType as Class<*>
                val typeParams = rawClass.typeParameters
                for (i in typeParams.indices) {
                    if (i < genericSuper.actualTypeArguments.size) {
                        val arg = genericSuper.actualTypeArguments[i]
                        typeMap[typeParams[i].name] = if (arg is TypeVariable<*>) {
                            typeMap[arg.name] ?: arg
                        } else {
                            arg
                        }
                    }
                }
                if (rawClass == ToolPageBindingFragment::class.java) {
                    val bindingType = genericSuper.actualTypeArguments[0]
                    val resolved = if (bindingType is TypeVariable<*>) {
                        typeMap[bindingType.name] ?: bindingType
                    } else {
                        bindingType
                    }
                    return resolved as Class<VB>
                }
            }
            currentClass = currentClass.superclass ?: break
        }
        throw IllegalStateException("Could not resolve ViewBinding class for ${javaClass.name}")
    }
}
