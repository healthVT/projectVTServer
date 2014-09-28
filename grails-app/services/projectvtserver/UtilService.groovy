package projectvtserver

import grails.transaction.Transactional
import org.joda.time.DateTime

@Transactional
class UtilService {
    def convertPeriod(String period){
        DateTime today = DateTime.now().withTime(0, 0, 0, 0)
        def datePeriod = [:]

        switch(period){
            case "MONTH":
                datePeriod = [start: today.minusMonths(1), end: today.withTime(23, 59, 59, 0)]
                break;
            case "YEAR":
                datePeriod = [start: today.minusYears(1), end: today.plusDays(1)]
                break;
            case "WEEK":
            default:
                datePeriod = [start: today.minusDays(7), end: today.withTime(23, 59, 59, 59)]
                break;
        }

        return datePeriod
    }
}
