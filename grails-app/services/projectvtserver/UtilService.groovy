package projectvtserver

import grails.transaction.Transactional
import org.joda.time.DateTime

@Transactional
class UtilService {
    def convertPeriod(String period){
        DateTime today = DateTime.now().withTime(0, 0, 0, 0)
        def datePeriod = [:]

        switch(period){
            case "MTD":
                datePeriod = [start: today.minusMonths(1), end: today.plusDays(1)]
                break;
            case "YTD":
                datePeriod = [start: today.minusYears(1), end: today.plusDays(1)]
                break;
            case "WTD":
            default:
                datePeriod = [start: today.minusDays(7), end: today.plusDays(1)]
                break;
        }

        return datePeriod
    }
}
