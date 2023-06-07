package com.example.githubclient

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    fun counterClick(id: Int) {
        when (id) {
            FIRST_ITEM -> {
                setCounterValue(id, CountersModel.FIRST_ITEM)
            }
            SECOND_ITEM -> {
                setCounterValue(id, CountersModel.SECOND_ITEM)
            }
            THIRD_ITEM -> {
                setCounterValue(id, CountersModel.THIRD_ITEM)
            }
        }
    }

    private fun setCounterValue(id: Int, modelCounterId: Int) {
        val nextValue = model.next(modelCounterId)
        view.setButtonText(id, nextValue.toString())
    }

    companion object {
        const val FIRST_ITEM = 0
        const val SECOND_ITEM = 1
        const val THIRD_ITEM = 2
    }
}