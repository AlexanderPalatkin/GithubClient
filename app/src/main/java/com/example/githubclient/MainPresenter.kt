package com.example.githubclient

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun counterClick(id: Int){
        when(id){
            FIRST_ITEM -> {
                val nextValue = model.next(0)
                view.setButtonText(0, nextValue.toString())
            }
            SECOND_ITEM -> {
                val nextValue = model.next(1)
                view.setButtonText(1, nextValue.toString())
            }
            THIRD_ITEM -> {
                val nextValue = model.next(2)
                view.setButtonText(2, nextValue.toString())
            }
        }
    }

    companion object {
        const val FIRST_ITEM = 0
        const val SECOND_ITEM = 1
        const val THIRD_ITEM = 2
    }

}