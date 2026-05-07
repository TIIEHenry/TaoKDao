package tiiehenry.taokdao.data.dex

import dalvik.system.DexClassLoader

class DynamicDexClassLoader(dexPath: String, odexDir: String, nativeDir: String, parent: ClassLoader)
    : DexClassLoader(dexPath, odexDir, nativeDir, parent) {
    private val classCache = HashMap<String, Class<*>>()


    @Throws(ClassNotFoundException::class)
    override fun findClass(name: String): Class<*>? {
        var cls = classCache[name]
        if (cls == null) {
            cls = super.findClass(name)
            classCache[name] = cls
        }
        return cls
    }

}