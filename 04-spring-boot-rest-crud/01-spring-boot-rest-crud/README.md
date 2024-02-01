
# API Design Process

1. Review API requirements

Create REST API for the Employee Directory

REST clients should be able to
* Get a list of employees
* Get a single employee by id
* Add a new employee
* Update an employee
* Delete an employee

2. Identify main resource / entity

* To identify main resource / entity, look for the most prominent "noun"
* For our project, it is "employee"
* Convention is to use plural form of resource / entity: **employees**
  * /api/employees

    
3. Use HTTP methods to assign action on resource

* POST - create a new entity
* GET - Read a list of entitites or single entity
* PUT - Update an existing entity
* DELETE - Delete an existing entity