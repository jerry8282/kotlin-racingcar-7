package racingcar.controller

import racingcar.view.ErrorView
import racingcar.view.InputView
import racingcar.model.Car
import racingcar.utils.RandomUtils

class RacingCarController {
    private val inputView = InputView
    private val errorView = ErrorView
    private val cars = mutableListOf<Car>()

    fun start() {
        try {
            val carNames = getCarNames()
            val numberOfAttempts = getNumberOfAttempts()

            initializeCars(carNames)
            playRace(numberOfAttempts)

        } catch (e: IllegalArgumentException) {
            errorView.errorMessage(e.message ?: "오류가 발생했습니다.")
        }
    }

    private fun getCarNames(): List<String> {
        val input = inputView.askForCarNames()

        val carNames = input.split(",").map { it.trim() }
        require(carNames.all { it.length <= 5 }) { "자동차 이름은 5자 이하만 가능합니다." }

        return carNames
    }

    private fun getNumberOfAttempts(): Int {
        val input = inputView.askForNumberOfAttempts()

        val numberOfAttempts = input.toIntOrNull() ?: throw IllegalArgumentException("숫자를 입력해주세요.")
        require(numberOfAttempts > 0) { "시도 횟수는 1회 이상이어야 합니다." }

        return numberOfAttempts
    }

    private fun initializeCars(carNames: List<String>) {
        carNames.forEach { name -> cars.add(Car(name)) }
    }

    private fun playRace(attempts: Int) {
        repeat(attempts) {
            cars.forEach { car -> car.move(RandomUtils.canMove())
            }
        }
    }
}