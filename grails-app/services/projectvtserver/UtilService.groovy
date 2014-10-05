package projectvtserver

import grails.transaction.Transactional
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

@Transactional
class UtilService {
    def convertPeriod(String period){
        def periodArray = period.split(" ")
        int number = periodArray[0] as Integer
        period = periodArray[1]

        DateTime today = DateTime.now().withTime(0, 0, 0, 0)
        def processDate = [:]

        switch(period){
            case "MONTH":
                //Day to minus
                int avgDatePeriod = 5
                if(number == 3){
                    avgDatePeriod = 15
                }else if(number == 6){
                    avgDatePeriod = 30
                }

                processDate = [startDate: today.minusMonths(number), avgDatePeriod: avgDatePeriod]
                break;
            case "WEEK":
            default:
                processDate = [startDate: today.minusDays(6), avgDatePeriod: 1]
                break;
        }

        return processDate
    }

    public static String jodaToString(def dateTime, String format = "yyyy-MM-dd"){
        DateTimeFormatter fmt = DateTimeFormat.forPattern(format)
        return fmt.print(dateTime)
    }
}
