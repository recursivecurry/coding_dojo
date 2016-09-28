package coroutine

import scala.collection.mutable

class Channel[A]() {
  private val queue: mutable.Queue[A] = mutable.Queue.empty
  private val players: mutable.Queue[Player] = mutable.Queue.empty

  def isEmpty: Boolean = queue.isEmpty

  def :<- (input: A): Unit = {
    queue.enqueue(input)
  }

  def :-> : A = {
    queue.dequeue()
  }

//  def wait(p: Player): Unit = players.enqueue(p)

//  def next(): Option[Player] = if (players.isEmpty) None else Option(players.dequeue())
}

class Player(val name: String, val channel: Channel[Int]) {
  var priority: Int = 0

  def run(): Boolean = {
    if (channel.isEmpty) {
      true
    } else {
      val v: Int = channel.:->
      if (v != 0) {
        println("%s %d".format(name, v))
        Thread.sleep(100)
        channel :<- (v-1)
        true
      } else
        false
    }
  }
}

class Runtime() {

  var live: Boolean = true

  val players: mutable.Queue[Player] = mutable.Queue.empty

  def go(p: Player): Unit = this.synchronized {
    players.enqueue(p)
  }

  def stop(): Unit = {
    live = false
  }

  def start(): Unit = new Thread(new Runnable {
    override def run(): Unit = {

      while (live) {
        this.synchronized(
          if (players.nonEmpty) {
            val p = players.dequeue()
            if (p.run())
              players.enqueue(p)
          }
        )
        Thread.sleep(0)
      }
    }
  }).start()
}

object PingPong {

  val players: mutable.Queue[Player] = mutable.Queue.empty

  def go(p: Player): Unit = players.enqueue(p)

  def main(args: Array[String]): Unit = {
    val runtime: Runtime  = new Runtime()
    runtime.start()

    val table: Channel[Int] = new Channel()
    runtime.go(new Player("ping", table))
    runtime.go(new Player("pong", table))
    table :<- 10
//    table.push(10)

    Thread.sleep(1000)
    runtime.stop()
  }
}
