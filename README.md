Sisyphus
===========
A Scala scheduler that supports multiple cron notations. The project follows the [Semantic Versioning Convention](http://semver.org/) and uses Apache 2.0 license.

## How to use it?

    import com.cronutils.model.CronType
    import com.cronutils.model.definition.CronDefinitionBuilder
    import com.cronutils.parser.CronParser
    import com.cronutils.sisyphus.model.{CronTask, Scheduler}
    import org.joda.time.DateTime
    
    val scheduler = new Scheduler()
    
    //declare parsers and crons. We support parsing multiple cron formats!
    val quartzparser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ))
    val unixcronparser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX))
    var qzcron = quartzparser.parse("*/5 * * * * ? *")
    var nixcron = unixcronparser.parse("* * * * *")
    
    //define tasks to be executed!
    val qzcrontask = new CronTask("qztimeprinter") {
      override def execute(): Unit = println(s"We got a Quartz match! ${DateTime.now()}")
    }
    val nixcrontask = new CronTask("nixtimeprinter") {
      override def execute(): Unit = println(s"We got a Unix match! ${DateTime.now()}")
    }
    
    //schedule and enjoy!
    scheduler.schedule(qzcron, qzcrontask)
    scheduler.schedule(nixcron, nixcrontask)

Sisyphus leverages cron-utils library to parse cron expressions and find if a date matches it. You can use any cron-expression to schedule a task.

# Resources
 * [A list of papers published on this topic](http://stackoverflow.com/questions/6004978/what-is-a-calendar-queue)
 * [1977: An efficient data structure for the simulation event set](http://dl.acm.org/citation.cfm?id=359801)
 * [1979: A Simple and Robust Data Structure for the Simulation Event Set](http://technologists.com/sauer/RC8001.pdf)
 * [1988: Calendar Queues: A Fast O(1) Priority Queue Implementation for the Simulation Event Set Problem](http://pi4.informatik.uni-mannheim.de/pi4.data/content/courses/2004-ss/netsim/area51/Brown1988a.pdf)
 * [2000: Snoopy calendar queue](http://www.informs-sim.org/wsc00papers/068.PDF)
 * [Study of Optimised bucket widths in Calendar Queue for Discrete Event Simulator](http://pioneer.netserv.chula.ac.th/~achaodit/paper5.pdf)
 * [Ladder queue: An O(1) priority queue structure for large-scale discrete event simulation](http://dl.acm.org/citation.cfm?id=1103324)