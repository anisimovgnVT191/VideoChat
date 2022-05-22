package com.example.android.videochat.di.utils.holder

abstract class LazyComponentHolder<Component : DiComponent> {

    @Volatile
    private var component: Component? = null
    private var componentProvider: () -> Component =
        { error("${javaClass.simpleName} - component provider not found") }

    fun get(): Component {
        return component ?: synchronized(this) {
            component ?: componentProvider().also { set { it } }
        }
    }

    fun clear() {
        this.component = null
    }

    fun set(provider: () -> Component) {
        componentProvider = provider
    }
}
