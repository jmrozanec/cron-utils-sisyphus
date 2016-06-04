/*
 * Copyright 2016 jmrozanec
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cronutils.sisyphus.model

import akka.actor.{ActorRef, Props, ActorSystem}
import com.cronutils.model.Cron
import com.cronutils.model.time.ExecutionTime
import scala.collection.mutable
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import scala.concurrent.duration._

/*
- http://alvinalexander.com/scala/simple-scala-akka-actor-examples-hello-world-actors
- http://doc.akka.io/docs/akka/snapshot/scala/scheduler.html
 */
class Scheduler {
  var actorsystem = ActorSystem("cronsystem")
  var timeactor = actorsystem.actorOf(Props(new TimeActor(actorsystem)), "time")
  val actors = Set()
  actorsystem.scheduler.schedule(0 milliseconds, 1 seconds, timeactor, "newsecond")


  def schedule(cron: Cron, task: CronTask): Unit ={
    println(timeactor.path)
    val actor = actorsystem.actorOf(Props(classOf[CronActor], ExecutionTime.forCron(cron), task), s"task-${task.name}")
    println(actor.path)
    actors.->(actor)
  }
}