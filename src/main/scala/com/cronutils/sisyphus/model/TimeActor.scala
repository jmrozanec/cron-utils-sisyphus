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
import akka.actor._
import org.joda.time.DateTime

class TimeActor(actorsystem: ActorSystem) extends Actor {
  def receive = {
    case "newsecond" => {
      var now = DateTime.now()
      now = new DateTime(now.getYear, now.getMonthOfYear, now.getDayOfMonth, now.getHourOfDay, now.getMinuteOfHour, now.getSecondOfMinute)
      val event = new DateEvent(now)
      actorsystem.actorSelection("/user/task-*") ! event
    }
  }
}
