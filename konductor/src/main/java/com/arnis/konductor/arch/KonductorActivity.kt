package com.arnis.konductor.arch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arnis.konductor.ChangeHandlerFrameLayout
import com.arnis.konductor.Konductor
import com.arnis.konductor.Router
import com.arnis.konductor.RouterTransaction
import com.arnis.konductor.attachRouter
import com.arnis.konductor.internal.LifecycleHandler

/** Created by arnis on 11/12/2017 */

abstract class KonductorActivity: AppCompatActivity() {
    private val router: Router by lazy { attachRouter(container) }
    val container by lazy { ChangeHandlerFrameLayout(this) }
    abstract val konductor: Konductor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(container)
        LifecycleHandler.restoreRouterState(router, savedInstanceState)
//        if (!router.hasRootController())
//            router.setRoot(RouterTransaction.with(konductor.route(HOME)))
    }

    override fun onBackPressed() {
        if (!router.handleBack())
            super.onBackPressed()
    }
}