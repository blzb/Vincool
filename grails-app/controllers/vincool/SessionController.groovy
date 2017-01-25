package vincool

import grails.converters.JSON
import grails.web.mapping.LinkGenerator

class SessionController {

    static scaffold = Session

    static transient LinkGenerator grailsLinkGenerator

    def calendar() {
        def events = [].withDefault{ [:] }

        Session.all.eachWithIndex{ session, index ->
            events[index]["id"] = session.id
            events[index]["title"] = session.lesson.topic
            events[index]["start"] = session.date
            events[index]["url"] = grailsLinkGenerator.link(controller: "session", action: "detail", id: session.id, absolute: true)
            events[index]["allDay"] = false
        }

        [events: events as JSON, offices: Office.all]
    }

    def detail(Long id) {
        def session = Session.get(id)

        if (session == null) {
            redirect(action: "showCalendar")
        }

        [sessionDetails: session]
    }

}

