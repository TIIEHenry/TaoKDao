package taokdao.main.business.action_process

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import taokdao.api.main.action.MainAction


interface ActionProcessView : ActionProcessContract.V {
    override fun registerMainActionLifecycleObserver() {
        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun create() = MainAction.onCreate.runObservers(this@ActionProcessView)

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun start() = MainAction.onStart.runObservers(this@ActionProcessView)

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun resume() = MainAction.onResume.runObservers(this@ActionProcessView)

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun pause() = MainAction.onPause.runObservers(this@ActionProcessView)

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun stop() = MainAction.onStop.runObservers(this@ActionProcessView)

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun destroy() = MainAction.onDestroy.runObservers(this@ActionProcessView)

            @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
            fun any() = MainAction.onAny.runObservers(this@ActionProcessView)
        })
    }
}