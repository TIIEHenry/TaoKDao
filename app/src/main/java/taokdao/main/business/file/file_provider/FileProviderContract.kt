package taokdao.main.business.file.file_provider

import taokdao.api.file.provider.IFileProvider
import taokdao.main.IMainView


interface FileProviderContract {
    interface V : IMainView {
        val fileProviderPresenter: FileProviderPresenter

    }

    interface P : IFileProvider

    interface M
}