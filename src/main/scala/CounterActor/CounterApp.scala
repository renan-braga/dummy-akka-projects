package CounterActor

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}


object CounterApp {

  // Messages the actor can receive
  sealed trait CounterCommand
  case object Increment extends CounterCommand
  case object GetCount extends CounterCommand
  case class ShowCount(replyTo: akka.actor.typed.ActorRef[Int]) extends CounterCommand

  object Counter {
    def apply(): Behavior[CounterCommand] = counterBehavior(0)

    def counterBehavior(count: Int): Behavior[CounterCommand] = Behaviors.receive { (context, message) =>
      message match {
        case Increment =>
          context.log.info(s"Counter incremented by ${count + 1}")
          counterBehavior(count + 1)
        case ShowCount(replyTo) =>
          replyTo ! count
          Behaviors.same
        case GetCount =>
          context.log.info(s"O contador está em: $count")
          Behaviors.same
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem(Counter(), "MessageCounterSystem")

    system ! Increment
    system ! Increment
    system ! GetCount

    import system.executionContext

    import scala.concurrent.duration._

    val printer = ActorSystem[Int](
      Behaviors.receiveMessage[Int] { count =>
        println(s"Contador atual: $count")
        Behaviors.stopped
      },
      "Printer"
    )

    system ! ShowCount(printer)

    system.scheduler.scheduleOnce(3.seconds, new Runnable {
      override def run(): Unit = {
        system.terminate()
        printer.terminate()
      }
    })
  }

}
