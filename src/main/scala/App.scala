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
package example

import com.cronutils.model.CronType
import com.cronutils.model.definition.CronDefinitionBuilder
import com.cronutils.parser.CronParser
import com.cronutils.sisyphus.model.{CronTask, Scheduler}
import org.joda.time.DateTime

object App {
  def main(args: Array[String]) {
    val cronparser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ))
    val scheduler = new Scheduler()
    val crontask = new CronTask("timeprinter") {
      override def execute(): Unit = println(s"time is ${DateTime.now()}")
    }

    var cron = cronparser.parse("*/5 * * * * ? *")

    scheduler.schedule(cron, crontask)
  }
}
