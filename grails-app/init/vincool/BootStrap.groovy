package vincool

import vincool.auth.SecRole
import vincool.auth.SecUser
import vincool.auth.SecUserSecRole
import org.joda.time.LocalDate
import org.joda.time.LocalTime

class BootStrap {

    def init = { servletContext ->
        new SecRole("ROLE_STUDENT").save()
        new SecRole("ROLE_INSTRUCTOR").save()
        def secRole = new SecRole("ROLE_ADMIN").save()
        def secUser = new SecUser("PedroAdmin", "12345abcd" ).save()
        SecUserSecRole.create (secUser, secRole)

        def attendee1 = new Attendee(username: "luisdaniel", password: "pass", email: "lalducin@nearsoft.com", name: "Luis Daniel", age: 22, school: "ESCOM", currentCompany: "Nearsoft").save()
        def attendee2 = new Attendee(username: "erikueen", password: "pass", email: "ereyna@nearsoft.com", name: "Erick Reyna", age: 23, school: "UPVT", currentCompany: "Nearsoft").save()
        new Attendee(username: "jlozano", password: "pass",  email: "jlozano@nearsoft.com", name: "Javier Lozano", age: 21, school: "UPIITA", currentCompany: "Nearsoft").save()
        new Attendee(username: "aibarra", password: "pass", email: "aibarra@nearsoft.com", name: "Antonio Ibarra", age: 19, school: "ESCOM", currentCompany: "Nearsoft").save()

        def cdmx = new Office(officeCode: "CDMX", name: "Ciudad de México", location: "San Luis Potosí 196 int. 401 Col. Roma Norte Del. Cuauhtémoc Ciudad de México 06700", phoneNumber:  "+52 (55) 6388-6650").save()
        def hmo = new Office(officeCode: "HMO", name: "Hermosillo, Sonora", location: "Blvd. Antonio Quiroga 21 Col. El Llano Hermosillo, Sonora, México 83210", phoneNumber:  "+52 (662) 216-7401").save()

        def instructor1 = new Instructor(username: "igomez", password: "pass", email: "igomez@nearsoft.com", name: "Israel Gómez").save()

        def eventCategory1 = new EventCategory(category: EventCategory.Category.APPRENTICE, subCategory: "Introductory Topics", color: "orange").save()
        def eventCategory2 = new EventCategory(category: EventCategory.Category.APPRENTICE, subCategory: "Data Structures and Big O Notation I", color: "orange").save()
        def eventCategory3 = new EventCategory(category: EventCategory.Category.APPRENTICE, subCategory: "SOLID I", color: "orange").save()
        def eventCategory4 = new EventCategory(category: EventCategory.Category.APPRENTICE, subCategory: "Data Structures and Big O Notation II", color: "orange").save()

        def apprenticeCDMX_2016B = new Batch(edition: "Apprentice CDMX 2016 B", isActive: false).save()
        def event1 = new Event(type: "Practical", date: LocalDate.parse("2016-11-10"), time: LocalTime.now(), instructor: instructor1, office: cdmx, eventCategory: eventCategory1,  description: "VCS and C, C++, Java", batch: apprenticeCDMX_2016B).save()
        new Event(type: "Practical", date: LocalDate.parse("2016-11-17"), time: LocalTime.now(), instructor: instructor1, office: cdmx, eventCategory: eventCategory2, description: "Arrays, lists and sets", batch: apprenticeCDMX_2016B).save()
        new Event(type: "Theoretical", date: LocalDate.parse("2016-11-24"), time: LocalTime.now(), instructor: instructor1, office: cdmx, eventCategory: eventCategory3, description: "Single Responsibility Principle", batch: apprenticeCDMX_2016B).save()

        def apprenticeCDMX_2017A = new Batch(edition: "Apprentice CDMX 2017 A", isActive: true).save()
        new Event(type: "Practical", date: LocalDate.parse("2017-01-7"), time: LocalTime.now(), instructor: instructor1, office: cdmx, eventCategory: eventCategory1, description: "VCS and Python, Java, Javascript", batch: apprenticeCDMX_2017A).save()
        new Event(type: "Practical", date: LocalDate.parse("2017-01-14"), time: LocalTime.now(), instructor: instructor1, office: cdmx, eventCategory: eventCategory2, description: "Arrays and Lists", batch: apprenticeCDMX_2017A).save()
        new Event(type: "Theoretical", date: LocalDate.parse("2017-01-21"), time: LocalTime.now(), instructor: instructor1, office: cdmx, eventCategory: eventCategory4, description: "Sets and Hash Tables", batch: apprenticeCDMX_2017A).save()

        def apprenticeHMO_2017A = new Batch(edition: "Apprentice HMO 2017 A", isActive: true).save()
        new Event(type: "Practical", date: LocalDate.parse("2017-01-5"), time: LocalTime.now(), instructor: instructor1, office: hmo, eventCategory: eventCategory1, description: "VCS and Python, Java, Javascript", batch: apprenticeHMO_2017A).save()
        new Event(type: "Practical", date: LocalDate.parse("2017-01-12"), time: LocalTime.now(), instructor: instructor1, office: hmo, eventCategory: eventCategory2, description: "Arrays and Lists", batch: apprenticeHMO_2017A).save()
        new Event(type: "Theoretical", date: LocalDate.parse("2017-01-19"), time: LocalTime.now(), instructor: instructor1, office: hmo, eventCategory: eventCategory4, description: "Sets and Hash Tables", batch: apprenticeHMO_2017A).save()

        new Resource(name:"Slides", url: "http://www.slides.com", description: "Slides about Arrays and Lists", event: event1).save()
        new Resource(name: "Repo", url: "http://www.repo.com", description: "Repo with exercises about Arrays and Lists", event: event1).save()

        new Enrollment(event: event1, attendee: attendee1, attendance: false).save()
        new Enrollment(event: event1, attendee: attendee2, attendance: false).save()

    }

    def destroy = {
    }
}
