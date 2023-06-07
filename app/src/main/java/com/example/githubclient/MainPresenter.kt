package com.example.githubclient

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    fun counterClick(id: Int) {
        when (id) {
            FIRST_ITEM -> {
                setCounterValue(id)
            }
            SECOND_ITEM -> {
                setCounterValue(id)
            }
            THIRD_ITEM -> {
                setCounterValue(id)
            }
        }
    }

    private fun setCounterValue(id: Int) {
        val nextValue = model.next(id)
        view.setButtonText(id, nextValue.toString())
    }

    companion object {
        const val FIRST_ITEM = 0
        const val SECOND_ITEM = 1
        const val THIRD_ITEM = 2
    }
}