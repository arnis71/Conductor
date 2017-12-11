package com.arnis.konductor

import android.app.Activity
import android.os.Bundle
import android.support.annotation.UiThread
import android.view.ViewGroup

import com.arnis.konductor.internal.LifecycleHandler
import com.arnis.konductor.internal.ThreadUtils

/**
 * Point of initial interaction with Konductor. Used to attach a [Router] to your Activity.
 */
object Konductor {

    /**
     * Konductor will create a [Router] that has been initialized for your Activity and containing ViewGroup.
     * If an existing [Router] is already associated with this Activity/ViewGroup pair, either in memory
     * or in the savedInstanceState, that router will be used and rebound instead of creating a new one with
     * an empty backstack.
     *
     * @param activity The Activity that will host the [Router] being attached.
     * @param container The ViewGroup in which the [Router]'s [Controller] views will be hosted
     * @param savedInstanceState The savedInstanceState passed into the hosting Activity's onCreate method. Used
     * for restoring the Router's state if possible.
     * @return A fully configured [Router] instance for use with this Activity/ViewGroup pair.
     */
    @UiThread
    @Deprecated("")
    fun attachRouter(activity: Activity, container: ViewGroup, savedInstanceState: Bundle?): Router {
        val lifecycleHandler = LifecycleHandler.install(activity)

        val router = lifecycleHandler.getRouter(container)
        LifecycleHandler.restoreRouterState(router, savedInstanceState)
        router.rebindIfNeeded()

        return router
    }
}


fun Activity.attachRouter(container: ViewGroup): Router {
    val lifecycleHandler = LifecycleHandler.install(this)

    val router = lifecycleHandler.getRouter(container)
    router.rebindIfNeeded()

    return router
}
