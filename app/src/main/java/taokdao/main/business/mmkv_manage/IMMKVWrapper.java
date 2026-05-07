package taokdao.main.business.mmkv_manage;

import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.Set;

import taokdao.api.data.mmkv.IMMKV;

public class IMMKVWrapper implements IMMKV {
    private final MMKV mmkv;

    public IMMKVWrapper(MMKV mmkv) {
        this.mmkv = mmkv;
    }

    @Override
    public boolean encode(String key, boolean value) {
        return mmkv.encode(key, value);
    }

    @Override
    public boolean decodeBool(String key) {
        return mmkv.decodeBool(key);
    }

    @Override
    public boolean decodeBool(String key, boolean defaultValue) {
        return mmkv.decodeBool(key, defaultValue);
    }

    @Override
    public boolean encode(String key, int value) {
        return mmkv.encode(key, value);
    }

    @Override
    public int decodeInt(String key) {
        return mmkv.decodeInt(key);
    }

    @Override
    public int decodeInt(String key, int defaultValue) {
        return mmkv.decodeInt(key, defaultValue);
    }

    @Override
    public boolean encode(String key, long value) {
        return mmkv.encode(key, value);
    }

    @Override
    public long decodeLong(String key) {
        return mmkv.decodeLong(key);
    }

    @Override
    public long decodeLong(String key, long defaultValue) {
        return mmkv.decodeLong(key, defaultValue);
    }

    @Override
    public boolean encode(String key, float value) {
        return mmkv.encode(key, value);
    }

    @Override
    public float decodeFloat(String key) {
        return mmkv.decodeFloat(key);
    }

    @Override
    public float decodeFloat(String key, float defaultValue) {
        return mmkv.decodeFloat(key, defaultValue);
    }

    @Override
    public boolean encode(String key, double value) {
        return mmkv.encode(key, value);
    }

    @Override
    public double decodeDouble(String key) {
        return mmkv.decodeDouble(key);
    }

    @Override
    public double decodeDouble(String key, double defaultValue) {
        return mmkv.decodeDouble(key, defaultValue);
    }

    @Override
    public boolean encode(String key, String value) {
        return mmkv.encode(key, value);
    }

    @Override
    public String decodeString(String key) {
        return mmkv.decodeString(key);
    }

    @Override
    public String decodeString(String key, String defaultValue) {
        return mmkv.decodeString(key, defaultValue);
    }

    @Override
    public boolean encode(String key, Set<String> value) {
        return mmkv.encode(key, value);
    }

    @Override
    public Set<String> decodeStringSet(String key) {
        return mmkv.decodeStringSet(key);
    }

    @Override
    public Set<String> decodeStringSet(String key, Set<String> defaultValue) {
        return mmkv.decodeStringSet(key, defaultValue);
    }

    @Override
    public Set<String> decodeStringSet(String key, Set<String> defaultValue, Class<? extends Set> cls) {
        return mmkv.decodeStringSet(key, defaultValue, cls);
    }

    @Override
    public boolean encode(String key, byte[] value) {
        return mmkv.encode(key, value);
    }

    @Override
    public byte[] decodeBytes(String key) {
        return mmkv.decodeBytes(key);
    }

    @Override
    public byte[] decodeBytes(String key, byte[] defaultValue) {
        return mmkv.decodeBytes(key, defaultValue);
    }

    @Override
    public boolean encode(String key, Parcelable value) {
        return mmkv.encode(key, value);
    }

    @Override
    public <T extends Parcelable> T decodeParcelable(String key, Class<T> tClass) {
        return mmkv.decodeParcelable(key, tClass);
    }

    @Override
    public <T extends Parcelable> T decodeParcelable(String key, Class<T> tClass, T defaultValue) {
        return mmkv.decodeParcelable(key, tClass, defaultValue);
    }

    @Override
    public int getValueSize(String key) {
        return mmkv.getValueSize(key);
    }

    @Override
    public int getValueActualSize(String key) {
        return mmkv.getValueActualSize(key);
    }

    @Override
    public boolean containsKey(String key) {
        return mmkv.containsKey(key);
    }

    @Override
    public String[] allKeys() {
        return mmkv.allKeys();
    }

    @Override
    public long count() {
        return mmkv.count();
    }

    @Override
    public long totalSize() {
        return mmkv.totalSize();
    }

    @Override
    public void removeValueForKey(String key) {
        mmkv.removeValueForKey(key);
    }

    @Override
    public void clearAll() {
        mmkv.clearAll();
    }

    @Override
    public void trim() {
        mmkv.trim();
    }

    @Override
    public void close() {
        mmkv.close();
    }

    @Override
    public void clearMemoryCache() {
        mmkv.clearMemoryCache();
    }

    @Override
    public void sync() {
        mmkv.sync();
    }

    @Override
    public void async() {
        mmkv.async();
    }
}
