# Presentation-App
Presentation App
================
User
*********
register : User can register the user with provided details and check for the duplicate email id.
login : User can login to the account by his email and password.
-------------------------------------------
only active user can perform these actions:
-------------------------------------------
get : User can be able to fetch their details based on the id.
-------------------------------------------
(Admin)
-------------------------------------------
getAll : User can be able to get all the users.
updateStatus : User can be able to change the status from active to inactive and vice-versa, default.


Presentation
**************
assign : Admin can assign the presentation to the student with student id.
get : can fetch the presentation with id.
getAll : get all the presentation by student id(Student).
changeStatus : student can change the presentation status.
score : Admin can save total score of the presentation.

Rating
************
rate : admin can rate the presentation by student id and presentation id.
get : can get the rating of particular presentation.
getAll : overall presentation rating of particular student.
