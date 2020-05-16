package eu.posegga.template

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Replaces all Rx Schedulers with [Schedulers.trampoline]
 */
class JunitRxSchedulerAndroidRule : TestRule {

    private val instance = Schedulers.trampoline()

    private val function = { _: Scheduler -> instance }

    private val callableFunction = { _: Callable<Scheduler> -> instance }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                RxAndroidPlugins.reset()
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(callableFunction)

                RxJavaPlugins.reset()
                RxJavaPlugins.setIoSchedulerHandler(function)
                RxJavaPlugins.setNewThreadSchedulerHandler(function)
                RxJavaPlugins.setComputationSchedulerHandler(function)

                try {
                    base.evaluate()
                } finally {
                    RxAndroidPlugins.reset()
                    RxJavaPlugins.reset()
                }
            }
        }
    }
}
