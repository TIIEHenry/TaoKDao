package taokdao.codeeditor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.charset.Charset;

import kotlin.text.Charsets;

public enum CharsetCollection {
//    US_ASCII是gb系列的编码方式
//    gb2312是对ASCII编码的中文扩展
//    gbk（对GB312编码的中文扩展）
//    gb18030（对GBK编码的扩展）增加了少数民族的字。
    UTF_8(Charsets.UTF_8),
    UTF_16(Charsets.UTF_16),
    UTF_16BE(Charsets.UTF_16BE),
    UTF_16LE(Charsets.UTF_16LE),
    US_ASCII(Charsets.US_ASCII),
    ISO_8859_1(Charsets.ISO_8859_1),
    UTF_32("UTF-32"),
    UTF_32BE("UTF-32BE"),
    UTF_32LE("UTF-32LE"),

//    中文编码
    GB18030("GB18030"),
    GBK("GBK"),//gbk 兼容gb2312
//    GB2312("GB2312"),
    ;

    @Nullable
    private String name;
    @Nullable
    private Charset charset;

    CharsetCollection(Charset charset) {
        this.charset = charset;
    }

    CharsetCollection(String name) {
        this.name = name;
    }

    public String getName() {
        if (name == null)
            name = charset.name();
        return name;
    }


    @NonNull
    public Charset getCharset() {
        if (charset == null)
            charset = Charset.forName(name);
        return charset;
    }
}
