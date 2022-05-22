package com.example.android.videochat.di.utils.holder

abstract class ComponentHolder<Component : DiComponent> {
    @Volatile
    private var component: Component? = null

    fun get(): Component {
        return component ?: synchronized(this) {
            component ?: build().also(::set)
        }
    }

    fun clear() {
        component = null
    }

    private fun set(component: Component) {
        this.component = component
    }

    protected abstract fun build(): Component
}
