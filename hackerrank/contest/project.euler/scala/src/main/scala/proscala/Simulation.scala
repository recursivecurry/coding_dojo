package proscala

/**
  * Created by pi on 2016. 8. 7..
  */
abstract class Simulation {

  type Action = () => Unit

  case class WorkItem(time: Int, action: Action)

  private var curtime = 0

  def currentTime = curtime

  private var agenda: List[WorkItem] = List()

  private def insert(ag: List[WorkItem],
                     item: WorkItem): List[WorkItem] = ag match {
    case Nil => item :: ag
    case (a :: as) => if (item.time < a.time) item :: ag else a :: insert(as, item)
  }

  def afterDelay(delay: Int)(block: => Unit) = {
    val item = WorkItem(currentTime + delay, () => block)
    agenda = insert(agenda, item)
  }

  private def next() = {
    (agenda: @unchecked) match {
      case item :: rest => {
        agenda = rest
        curtime = item.time
        item.action()
      }
    }
  }

  def run() = {
    afterDelay(0)({
      println("*** simulation started, time = " +
      currentTime + " ***")
    })
    while (agenda.nonEmpty) next()
  }
}

object Simulation {
  def main(args: Array[String]): Unit = {
    val simu = new Simulation {}
    var a = 1
    simu.afterDelay(1)({
      println(a)
    })
    a = 2
    simu.run()
  }
}
