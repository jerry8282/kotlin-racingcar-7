package racingcar.view

import camp.nextstep.edu.missionutils.Console

object InputView{
    fun askForCarNames(): String {
        println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
        return Console.readLine()
    }
}