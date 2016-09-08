package proscala

/**
  * Created by pi on 2016. 8. 7..
  */
abstract class BasicCurcitSimulation extends Simulation {

  var InverterDelay: Int = _
  var AndGateDelay: Int = _
  var OrGateDelay: Int = _

  class Wire {
    private var sigVal: Boolean = false
    private var actions: List[Action] = List()

    def getSignal = sigVal

    def setSignal(s: Boolean): Unit = {
      if (s != sigVal) {
        sigVal = s
        actions.foreach(_())
      }
    }

    def addAction(a: Action): Unit = {
      actions = a :: actions
      a()
    }
  }

  def inverter(input: Wire, output: Wire): Unit = {
    def invertAction() = {
      val inputSignal = input.getSignal
      afterDelay(InverterDelay) {
        output.setSignal(inputSignal)
      }
    }
    input.addAction(invertAction)
  }

  def andGate(a1: Wire, a2: Wire, output: Wire): Unit = {
    def andAction() = {
      val i1 = a1.getSignal
      val i2 = a2.getSignal
      afterDelay(AndGateDelay) {
        output.setSignal(i1 && i2)
      }
    }
    a1.addAction(andAction)
    a2.addAction(andAction)
  }

  def orGate(a1: Wire, a2: Wire, output: Wire): Unit = {
    def orAction() = {
      val i1 = a1.getSignal
      val i2 = a2.getSignal
      afterDelay(OrGateDelay) {
        output.setSignal(i1 || i2)
      }
    }
    a1.addAction(orAction)
    a2.addAction(orAction)
  }
}
