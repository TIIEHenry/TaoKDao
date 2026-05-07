package tiiehenry.android.dl.plugin

import android.annotation.SuppressLint
import android.content.*
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.*
import android.view.Display
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream

abstract class BaseContext(val host: Context) : Context() {

    @SuppressLint("MissingPermission")
    override fun removeStickyBroadcastAsUser(intent: Intent?, user: UserHandle?) {
        return host.removeStickyBroadcastAsUser(intent, user)
    }

    override fun checkCallingOrSelfPermission(permission: String): Int {
        return host.checkCallingOrSelfPermission(permission)
    }

    override fun checkCallingOrSelfUriPermission(uri: Uri?, modeFlags: Int): Int {
        return host.checkCallingOrSelfUriPermission(uri, modeFlags)
    }

    override fun checkUriPermission(uri: Uri?, pid: Int, uid: Int, modeFlags: Int): Int {
        return host.checkUriPermission(uri, pid, uid, modeFlags)
    }

    override fun checkUriPermission(uri: Uri?, readPermission: String?, writePermission: String?, pid: Int, uid: Int, modeFlags: Int): Int {
        return host.checkUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags)
    }

    override fun checkPermission(permission: String, pid: Int, uid: Int): Int {
        return host.checkPermission(permission, pid, uid)
    }

    override fun startIntentSender(intent: IntentSender?, fillInIntent: Intent?, flagsMask: Int, flagsValues: Int, extraFlags: Int) {
        return host.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags)
    }

    override fun startIntentSender(intent: IntentSender?, fillInIntent: Intent?, flagsMask: Int, flagsValues: Int, extraFlags: Int, options: Bundle?) {
        return host.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags, options)
    }

    @SuppressLint("MissingPermission")
    override fun sendStickyBroadcastAsUser(intent: Intent?, user: UserHandle?) {
        return host.sendStickyBroadcastAsUser(intent, user)
    }


    override fun setWallpaper(bitmap: Bitmap?) {
        return host.setWallpaper(bitmap)
    }

    override fun setWallpaper(data: InputStream?) {
        return host.setWallpaper(data)
    }

    override fun getWallpaper(): Drawable {
        return host.wallpaper
    }

    override fun clearWallpaper() {
        return host.clearWallpaper()
    }

    override fun peekWallpaper(): Drawable {
        return host.peekWallpaper()
    }

    override fun getWallpaperDesiredMinimumHeight(): Int {
        return host.wallpaperDesiredMinimumHeight
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun isDeviceProtectedStorage(): Boolean {
        return host.isDeviceProtectedStorage
    }

    @SuppressLint("MissingPermission")
    override fun sendBroadcastAsUser(intent: Intent?, user: UserHandle?) {
        return host.sendBroadcastAsUser(intent, user)
    }

    @SuppressLint("MissingPermission")
    override fun sendBroadcastAsUser(intent: Intent?, user: UserHandle?, receiverPermission: String?) {
        return host.sendBroadcastAsUser(intent, user, receiverPermission)
    }

    override fun getFileStreamPath(name: String?): File {
        return host.getFileStreamPath(name)
    }

    override fun stopService(service: Intent?): Boolean {
        return host.stopService(service)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun checkSelfPermission(permission: String): Int {
        return host.checkSelfPermission(permission)
    }

    override fun registerReceiver(receiver: BroadcastReceiver?, filter: IntentFilter?): Intent? {
        return host.registerReceiver(receiver, filter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun registerReceiver(receiver: BroadcastReceiver?, filter: IntentFilter?, flags: Int): Intent? {
        return host.registerReceiver(receiver, filter, flags)
    }

    override fun registerReceiver(receiver: BroadcastReceiver?, filter: IntentFilter?, broadcastPermission: String?, scheduler: Handler?): Intent? {
        return host.registerReceiver(receiver, filter, broadcastPermission, scheduler)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun registerReceiver(receiver: BroadcastReceiver?, filter: IntentFilter?, broadcastPermission: String?, scheduler: Handler?, flags: Int): Intent? {
        return host.registerReceiver(receiver, filter, broadcastPermission, scheduler, flags)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getSystemServiceName(serviceClass: Class<*>): String? {
        return host.getSystemServiceName(serviceClass)
    }

    override fun getMainLooper(): Looper {
        return host.mainLooper
    }

    override fun enforceCallingOrSelfPermission(permission: String, message: String?) {
        return host.enforceCallingOrSelfPermission(permission, message)
    }

    override fun checkCallingUriPermission(uri: Uri?, modeFlags: Int): Int {
        return host.checkCallingUriPermission(uri, modeFlags)
    }

    override fun getWallpaperDesiredMinimumWidth(): Int {
        return host.wallpaperDesiredMinimumWidth
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun createDeviceProtectedStorageContext(): Context {
        return host.createDeviceProtectedStorageContext()
    }

    override fun openFileInput(name: String?): FileInputStream {
        return host.openFileInput(name)
    }


    override fun bindService(service: Intent, conn: ServiceConnection, flags: Int): Boolean {
        return host.bindService(service, conn, flags)
    }


    override fun getNoBackupFilesDir(): File {
        return host.noBackupFilesDir
    }

    override fun startActivities(intents: Array<out Intent>?) {
        return host.startActivities(intents)
    }

    override fun startActivities(intents: Array<out Intent>?, options: Bundle?) {
        return host.startActivities(intents, options)
    }


    override fun fileList(): Array<String> {
        return host.fileList()
    }

    override fun setTheme(resid: Int) {
//        host.setTheme(resid)
    }

    override fun unregisterReceiver(receiver: BroadcastReceiver?) {
        return host.unregisterReceiver(receiver)
    }

    override fun enforcePermission(permission: String, pid: Int, uid: Int, message: String?) {
        return host.enforcePermission(permission, pid, uid, message)
    }

    override fun openFileOutput(name: String?, mode: Int): FileOutputStream {
        return host.openFileOutput(name, mode)
    }

    @SuppressLint("MissingPermission")
    override fun sendStickyOrderedBroadcast(intent: Intent?, resultReceiver: BroadcastReceiver?, scheduler: Handler?, initialCode: Int, initialData: String?, initialExtras: Bundle?) {
        return host.sendStickyOrderedBroadcast(intent, resultReceiver, scheduler, initialCode, initialData, initialExtras)
    }

    override fun createConfigurationContext(overrideConfiguration: Configuration): Context {
        return host.createConfigurationContext(overrideConfiguration)
    }


    override fun sendBroadcast(intent: Intent?) {
        return host.sendBroadcast(intent)
    }

    override fun sendBroadcast(intent: Intent?, receiverPermission: String?) {
        return host.sendBroadcast(intent, receiverPermission)
    }

    @SuppressLint("MissingPermission")
    override fun sendOrderedBroadcastAsUser(intent: Intent?, user: UserHandle?, receiverPermission: String?, resultReceiver: BroadcastReceiver?, scheduler: Handler?, initialCode: Int, initialData: String?, initialExtras: Bundle?) {
        return host.sendOrderedBroadcastAsUser(intent, user, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras)
    }

    override fun grantUriPermission(toPackage: String?, uri: Uri?, modeFlags: Int) {
        return host.grantUriPermission(toPackage, uri, modeFlags)
    }

    override fun enforceCallingUriPermission(uri: Uri?, modeFlags: Int, message: String?) {
        return host.enforceCallingUriPermission(uri, modeFlags, message)
    }


    @SuppressLint("MissingPermission")
    override fun sendStickyOrderedBroadcastAsUser(intent: Intent?, user: UserHandle?, resultReceiver: BroadcastReceiver?, scheduler: Handler?, initialCode: Int, initialData: String?, initialExtras: Bundle?) {
        return host.sendStickyOrderedBroadcastAsUser(intent, user, resultReceiver, scheduler, initialCode, initialData, initialExtras)
    }

    override fun startActivity(intent: Intent?) {
        return host.startActivity(intent)
    }

    override fun startActivity(intent: Intent?, options: Bundle?) {
        return host.startActivity(intent, options)
    }

    override fun getPackageManager(): PackageManager {
        return host.packageManager
    }

    override fun deleteFile(name: String?): Boolean {
        return host.deleteFile(name)
    }

    override fun startService(service: Intent?): ComponentName? {
        return host.startService(service)
    }

    override fun revokeUriPermission(uri: Uri?, modeFlags: Int) {
        return host.revokeUriPermission(uri, modeFlags)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun revokeUriPermission(toPackage: String?, uri: Uri?, modeFlags: Int) {
        return host.revokeUriPermission(toPackage, uri, modeFlags)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun moveDatabaseFrom(sourceContext: Context?, name: String?): Boolean {
        return host.moveDatabaseFrom(sourceContext, name)
    }

    override fun startInstrumentation(className: ComponentName, profileFile: String?, arguments: Bundle?): Boolean {
        return host.startInstrumentation(className, profileFile, arguments)
    }

    override fun sendOrderedBroadcast(intent: Intent?, receiverPermission: String?) {
        return host.sendOrderedBroadcast(intent, receiverPermission)
    }

    override fun sendOrderedBroadcast(intent: Intent, receiverPermission: String?, resultReceiver: BroadcastReceiver?, scheduler: Handler?, initialCode: Int, initialData: String?, initialExtras: Bundle?) {
        return host.sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras)
    }

    override fun unbindService(conn: ServiceConnection) {
        return host.unbindService(conn)
    }


    override fun createDisplayContext(display: Display): Context {
        return host.createDisplayContext(display)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createContextForSplit(splitName: String?): Context {
        return host.createContextForSplit(splitName)
    }


    override fun getContentResolver(): ContentResolver {
        return host.contentResolver
    }


    override fun enforceCallingOrSelfUriPermission(uri: Uri?, modeFlags: Int, message: String?) {
        return host.enforceCallingOrSelfUriPermission(uri, modeFlags, message)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun moveSharedPreferencesFrom(sourceContext: Context?, name: String?): Boolean {
        return host.moveSharedPreferencesFrom(sourceContext, name)
    }

    override fun checkCallingPermission(permission: String): Int {
        return host.checkCallingPermission(permission)
    }


    @SuppressLint("MissingPermission")
    override fun sendStickyBroadcast(intent: Intent?) {
        return host.sendStickyBroadcast(intent)
    }

    override fun enforceCallingPermission(permission: String, message: String?) {
        return host.enforceCallingPermission(permission, message)
    }

    override fun getSystemService(name: String): Any {
        return host.getSystemService(name)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun startForegroundService(service: Intent?): ComponentName? {
        return host.startForegroundService(service)
    }


    override fun databaseList(): Array<String> {
        return host.databaseList()
    }

    override fun createPackageContext(packageName: String?, flags: Int): Context {
        return host.createPackageContext(packageName, flags)
    }

    override fun enforceUriPermission(uri: Uri?, pid: Int, uid: Int, modeFlags: Int, message: String?) {
        return host.enforceUriPermission(uri, pid, uid, modeFlags, message)
    }

    override fun enforceUriPermission(uri: Uri?, readPermission: String?, writePermission: String?, pid: Int, uid: Int, modeFlags: Int, message: String?) {
        return host.enforceUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags, message)
    }

    @SuppressLint("MissingPermission")
    override fun removeStickyBroadcast(intent: Intent?) {
        return host.removeStickyBroadcast(intent)
    }
}