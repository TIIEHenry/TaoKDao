package taokdao.content.openfiled

import android.view.View
import taokdao.api.data.bean.Properties
import taokdao.api.internal.InnerIdentifier
import taokdao.api.main.IMainContext
import taokdao.api.ui.content.wrapped.ContentFragment
import tiiehenry.ideditor.databinding.ContentsOpenErrorBinding
import java.io.File

class OpenFailedContent(
        val main: IMainContext,
        val message: String,
        path: String,
        val callback: View.OnClickListener,
        val binding:ContentsOpenErrorBinding = ContentsOpenErrorBinding.inflate(main.layoutInflater)
) : ContentFragment(
        Properties(InnerIdentifier.Content.OPEN_FAILED, "![${File(path).name}]"),
        path, binding.root, OpenFailedEditor()) {

    override fun initView(view: View) {
        binding.tvMessage.text = message
        binding.tvPath.text = path
        binding.btnAction.setOnClickListener(callback)
    }
}

