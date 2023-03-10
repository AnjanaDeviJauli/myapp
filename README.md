# myapp
Spring Boot Student Database Administration Project
The Spring Boot student database administration project is a web-based application designed to help manage student data in an educational institution.
The application is built using the Spring Boot framework, which provides a robust and scalable platform for developing enterprise-grade applications.

The front-end of the application is built using HTML, CSS, JavaScript, and Bootstrap. It provides a clean and intuitive user interface for administrators
and teachers to interact with the application's features.

Features 
•	Add, edit, or delete students, teachers, and courses.
•	Search student by their id or with their first name and last name.
•	View students by grade and find the number of girls, boys, and total count.
•	View or update course assigned to a student.
•	Add exam score for each course and generate perfomance report.
•	Assign students to enhancement or tution according to theier perfomance.
•	Assign teachers to courses. 
•	Teacher and course are in many to many relationship.
•	One teacher can be assigned to more than one course.
•	One course can be taken by more than one teachers.
•	Prevent deleting a teacher if the teacher is assigned to a different course.
•	Prevent deleting a course if the same course is assigned to a different teacher.
•	Prevent adding same course or teacher or student if already exist in database

Spring Security
The Spring Boot student database administration project uses Spring Security to provide secure authentication and authorization for two types of users: 
admin and teacher.

Roles and Permissions
1)Admin users can perform all CRUD operations on students, teachers, and courses, as well as create and delete teacher-course assignments.
2)Teacher users can view all students and update their scores, as well as view student performance reports. Based on student performance, teachers can recommend
students for specialized learning or assign them to tuition.

Technologies and Tools incorporated in implementaion:
Designed and created wireframe for the school administration webpages.
Created mock up web pages using HTML 5,Bootstrap CSS,JavaScript.
Front end validation using html and bootstrap.
Designed the database using an ER diagram.
Created Maven project using spring initializer.
Implementd CRUD operations through Spring Data JPA.
Wrote AssertJ test cases for repositories and services methods.
Persisted the data in the MySQL database.
Used GitHub as the code repository.
Used Lombok library for creating POJO classes and entities.
Used Thymeleaf for spring web mvc integration.
Used spring boot validation which ensured integrity and security of data.
Used Spring security for login authentication/authorization.
Used Slf4j for application logging.
Used Sonarlint for maintaining code quality.
Two Layers of Validation: Front end validation using html and bootstrap, used Spring Validation at back end.
By using two layers of validation, the application ensurse the integrity and security of the data.

Conclusion
In conclusion, the Spring Boot student database administration project is a web-based application designed to help educational institutions manage 
student data effectively. The project leverages the Spring Boot framework to provide a robust and scalable platform for developing enterprise-grade applications. 
It offers features such as adding, editing, or deleting students, teachers, and courses, searching for students by ID or name, viewing students by grade,
assigning courses to students, and generating performance reports.

The project also uses Spring Security for secure authentication and authorization, allowing two types of users, admin and teacher, 
with different roles and permissions. The application's front-end is built using HTML, CSS, JavaScript, and Bootstrap, 
providing a clean and intuitive user interface for administrators and teachers to interact with the features. 
The implementation of the project incorporates several technologies and tools, including Spring Data JPA, AssertJ, Maven, Lombok, Thymeleaf, and Sonarlint.

Moreover, the project implements two layers of validation to ensure the integrity and security of the data,
making it more reliable and user-friendly. Overall, the Spring Boot student database administration project is a 
robust and efficient solution that can help educational institutions streamline their operations and manage student data effectively.






